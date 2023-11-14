package example.penilaian.service.penilaianLapangan;

import example.penilaian.entity.penilaianLapangan.MultipleChoice;
import example.penilaian.entity.penilaianLapangan.Nilai;
import example.penilaian.entity.penilaianLapangan.Question;
import example.penilaian.model.penilaianLapangan.NilaiByUser;
import example.penilaian.model.penilaianLapangan.NilaiResponse;
import example.penilaian.repository.NilaiRepository;
import example.penilaian.repository.QuestionRepository;
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
    public void saveNilai(List<Nilai> nilaiData) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Set timestamp outside the loop since it's the same for all Nilai objects
        String formattedDate = sdf.format(new java.util.Date());
        Date currentDate = Date.valueOf(formattedDate);
//        Date testDate = Date.valueOf("2023-11-15");

        for (Nilai nilai : nilaiData) {
            // Perbarui nilai timestamp hanya jika belum diisi
            if (nilai.getTimestamp() == null) {
                nilai.setTimestamp(currentDate);
//                nilai.setTimestamp(testDate);
            }

            // Cek apakah sudah ada data dengan username, timestamp, dan questionId yang sama
            List<Nilai> existingNilaiList = nilaiRepository
                    .findByUsernameAndTimestampAndQuestionIdAndTeamName(
                            nilai.getUsername(),
                            nilai.getTimestamp(),
                            nilai.getQuestionId(),
                            nilai.getTeamName()
                    );

            // Jika data sudah ada, update nilai sesuai kebutuhan
            if (!existingNilaiList.isEmpty()) {
                for (Nilai existingNilai : existingNilaiList) {
                    // Hanya update jika teamName sama
                    if (existingNilai.getTeamName().equals(nilai.getTeamName())) {
                        existingNilai.setNilai(nilai.getNilai());
                        // Update nilai-nilai lainnya sesuai kebutuhan
                    }
                }
                // Simpan kembali ke repository
                nilaiRepository.saveAll(existingNilaiList);
            } else {
                // Jika tidak ada data dengan kombinasi yang sama, langsung simpan data baru
                nilaiRepository.save(nilai);
            }
        }
    }



    public List<Nilai> updateNilai(List<Nilai> updatedNilaiList) {
        List<Nilai> updatedNilaiListResult = new ArrayList<>();

        for (Nilai updatedNilai : updatedNilaiList) {
            Optional<Nilai> existingNilai = nilaiRepository.findById(updatedNilai.getNilaiId());
            if (existingNilai.isPresent()) {
                // Update the nilai value
                existingNilai.get().setNilai(updatedNilai.getNilai());
                updatedNilaiListResult.add(nilaiRepository.save(existingNilai.get()));
            }
        }

        return updatedNilaiListResult;
    }


    public List<NilaiByUser> getNilaiByUser(String username) {
        List<Nilai> nilaiList = nilaiRepository.findByUsername(username);
        List<NilaiByUser> nilaiByUserList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Nilai nilai : nilaiList) {
            Date timestamp = nilai.getTimestamp();

            // Lepaskan error jika timestamp null
            if (timestamp == null) {
                throw new IllegalStateException("Timestamp cannot be null for nilaiId: " + nilai.getNilaiId());
            }

            String formattedTimestamp = sdf.format(timestamp);
            Question question = questionRepository.findById(nilai.getQuestionId()).orElse(null);

            if (question != null) {
                // Ambil nilai maksimum dari kumpulan pilihan ganda
                List<MultipleChoice> choices = new ArrayList<>(question.getChoices());
                double maxValue = choices.stream()
                        .mapToDouble(MultipleChoice::getChoiceValue)
                        .max()
                        .orElse(0.0);

                NilaiByUser nilaiByUser = NilaiByUser.builder()
                        .nilaiId(nilai.getNilaiId())
                        .username(username)
                        .teamName(nilai.getTeamName())
                        .nilai(nilai.getNilai())
                        .maxValue(maxValue)
                        .questionId(nilai.getQuestionId())
                        .questionText(question.getQuestionText())
                        .formattedTimestamp(formattedTimestamp)
                        .build();
                nilaiByUserList.add(nilaiByUser);
            }
        }

        return nilaiByUserList;
    }



    public List<NilaiResponse>getAllNilai(){
        List<Nilai> nilaiList = nilaiRepository.findAll();
        return nilaiList.stream().map(this::nilaiResponse).collect(Collectors.toList());

    }
    private NilaiResponse nilaiResponse(Nilai nilai){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedTimestamp = sdf.format(nilai.getTimestamp());
        return NilaiResponse.builder()
                .nilaiId(nilai.getNilaiId())
                .nilai(nilai.getNilai())
                .teamName(nilai.getTeamName())
                .username(nilai.getUsername())
                .questionId(nilai.getQuestionId())
                .questionText(getQuestionText(nilai.getQuestionId()))
                .timestamp(formattedTimestamp)
                .build();
    }

    private String getQuestionText(int questionId){
        return questionRepository.findById(questionId).map(Question::getQuestionText).orElse(null);
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



