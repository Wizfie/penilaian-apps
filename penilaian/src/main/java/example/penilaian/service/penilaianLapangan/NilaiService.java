package example.penilaian.service.penilaianLapangan;

import example.penilaian.entity.penilaianLapangan.MultipleChoice;
import example.penilaian.entity.penilaianLapangan.NilaiLapangan;
import example.penilaian.entity.penilaianLapangan.QuestionLapangan;
import example.penilaian.model.penilaianLapangan.NilaiByUser;
import example.penilaian.model.penilaianLapangan.NilaiResponse;
import example.penilaian.repository.penilaianLapangan.NilaiRepository;
import example.penilaian.repository.penilaianLapangan.QuestionRepository;
import jakarta.transaction.Transactional;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NilaiService {


    @Autowired
    private NilaiRepository nilaiRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Transactional
    public void saveNilai(List<NilaiLapangan> nilaiLapanganData) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String formattedDate = sdf.format(new java.util.Date());
        Date currentDate = Date.valueOf(formattedDate);
//        Date testDate = Date.valueOf("2023-11-15");

        for (NilaiLapangan nilaiLapangan : nilaiLapanganData) {
            // Perbarui nilaiLapangan timestamp hanya jika belum diisi
            if (nilaiLapangan.getTimestamp() == null) {
                nilaiLapangan.setTimestamp(currentDate);
//                nilaiLapangan.setTimestamp(testDate);
            }

            // Cek apakah sudah ada data dengan username, timestamp, dan questionId yang sama
            List<NilaiLapangan> existingNilaiListLapangan = nilaiRepository
                    .findByUsernameAndTimestampAndQuestionIdAndTeamName(
                            nilaiLapangan.getUsername(),
                            nilaiLapangan.getTimestamp(),
                            nilaiLapangan.getQuestionId(),
                            nilaiLapangan.getTeamName()
                    );

            // Jika data sudah ada, update nilaiLapangan sesuai kebutuhan
            if (!existingNilaiListLapangan.isEmpty()) {
                for (NilaiLapangan existingNilaiLapangan : existingNilaiListLapangan) {
                    // Hanya update jika teamName sama
                    if (existingNilaiLapangan.getTeamName().equals(nilaiLapangan.getTeamName())) {
                        existingNilaiLapangan.setNilai(nilaiLapangan.getNilai());
                        // Update nilaiLapangan-nilaiLapangan lainnya sesuai kebutuhan
                    }
                }
                // Simpan kembali ke repository
                nilaiRepository.saveAll(existingNilaiListLapangan);
            } else {
                // Jika tidak ada data dengan kombinasi yang sama, langsung simpan data baru
                nilaiRepository.save(nilaiLapangan);
            }
        }
    }



    public List<NilaiLapangan> updateNilai(List<NilaiLapangan> updatedNilaiListLapangan) {
        List<NilaiLapangan> updatedNilaiListResultLapangan = new ArrayList<>();

        for (NilaiLapangan updatedNilaiLapangan : updatedNilaiListLapangan) {
            Optional<NilaiLapangan> existingNilai = nilaiRepository.findById(updatedNilaiLapangan.getNilaiId());
            if (existingNilai.isPresent()) {
                // Update the nilai value
                existingNilai.get().setNilai(updatedNilaiLapangan.getNilai());
                updatedNilaiListResultLapangan.add(nilaiRepository.save(existingNilai.get()));
            }
        }

        return updatedNilaiListResultLapangan;
    }


    public List<NilaiByUser> getNilaiByUser(String username) {
        List<NilaiLapangan> nilaiLapanganList = nilaiRepository.findByUsername(username);
        List<NilaiByUser> nilaiByUserList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (NilaiLapangan nilaiLapangan : nilaiLapanganList) {
            Date timestamp = nilaiLapangan.getTimestamp();

            // Lepaskan error jika timestamp null
            if (timestamp == null) {
                throw new IllegalStateException("Timestamp cannot be null for nilaiId: " + nilaiLapangan.getNilaiId());
            }

            String formattedTimestamp = sdf.format(timestamp);
            QuestionLapangan questionLapangan = questionRepository.findById(nilaiLapangan.getQuestionId()).orElse(null);

            if (questionLapangan != null) {
                // Ambil nilaiLapangan maksimum dari kumpulan pilihan ganda
                List<MultipleChoice> choices = new ArrayList<>(questionLapangan.getChoices());
                double maxValue = choices.stream()
                        .mapToDouble(MultipleChoice::getChoiceValue)
                        .max()
                        .orElse(0.0);

                NilaiByUser nilaiByUser = NilaiByUser.builder()
                        .nilaiId(nilaiLapangan.getNilaiId())
                        .username(username)
                        .teamName(nilaiLapangan.getTeamName())
                        .nilai(nilaiLapangan.getNilai())
                        .maxValue(maxValue)
                        .questionId(nilaiLapangan.getQuestionId())
                        .questionText(questionLapangan.getQuestionText())
                        .formattedTimestamp(formattedTimestamp)
                        .build();
                nilaiByUserList.add(nilaiByUser);
            }
        }

        return nilaiByUserList;
    }



    public List<NilaiResponse>getAllNilai(){
        List<NilaiLapangan> nilaiLapanganList = nilaiRepository.findAll();
        return nilaiLapanganList.stream().map(this::nilaiResponse).collect(Collectors.toList());

    }
    private NilaiResponse nilaiResponse(NilaiLapangan nilaiLapangan){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedTimestamp = sdf.format(nilaiLapangan.getTimestamp());
        return NilaiResponse.builder()
                .nilaiId(nilaiLapangan.getNilaiId())
                .nilai(nilaiLapangan.getNilai())
                .teamName(nilaiLapangan.getTeamName())
                .username(nilaiLapangan.getUsername())
                .questionId(nilaiLapangan.getQuestionId())
                .questionText(getQuestionText(nilaiLapangan.getQuestionId()))
                .timestamp(formattedTimestamp)
                .build();
    }

    private String getQuestionText(int questionId){
        return questionRepository.findById(questionId).map(QuestionLapangan::getQuestionText).orElse(null);
    }



    @Transactional
    public void deleteNilai(String username, String teamName, java.sql.Date timestamp) throws ServiceException {
        try {
            nilaiRepository.deleteByTimestampAndUsernameAndTeamName(timestamp, username, teamName);
        } catch (EmptyResultDataAccessException e) {
            throw new ServiceException("Data tidak ditemukan");
        } catch (IllegalArgumentException e) {
            throw new ServiceException("Format timestamp tidak valid");
        }
    }






}



