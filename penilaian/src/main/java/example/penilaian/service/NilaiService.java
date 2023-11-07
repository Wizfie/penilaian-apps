package example.penilaian.service;

import example.penilaian.entity.MultipleChoice;
import example.penilaian.entity.Nilai;
import example.penilaian.entity.Question;
import example.penilaian.model.CustomData;
import example.penilaian.repository.NilaiRepository;
import example.penilaian.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class NilaiService {


    @Autowired
    private NilaiRepository nilaiRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Transactional
    public void saveNilai(List<Nilai> nilaiData){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        for (Nilai nilai : nilaiData) {
            nilai.setTimestamp(timestamp);
        }

        nilaiRepository.saveAll(nilaiData);
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


    public List<CustomData> getNilaiByUser(String username) {
        List<Nilai> nilaiList = nilaiRepository.findByUsername(username);
        List<CustomData> customDataList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Nilai nilai : nilaiList) {
            Question question = questionRepository.findById(nilai.getQuestionId()).orElse(null);
            Timestamp timestamp = nilai.getTimestamp();
            String formattedTimestamp = sdf.format(timestamp);

            if (question != null) {


                // Ambil nilai maksimum dari kumpulan pilihan ganda
                List<MultipleChoice> choices = new ArrayList<>(question.getChoices());                double maxValue = choices.stream()
                        .mapToDouble(MultipleChoice::getChoiceValue)
                        .max()
                        .orElse(0.0);

                CustomData customData = CustomData.builder()
                        .nilaiId(nilai.getNilaiId()) // Include nilai_id in the response
                        .username(username)
                        .teamName(nilai.getTeamName())
                        .nilai(nilai.getNilai())
                        .maxValue(maxValue)
                        .questionId(nilai.getQuestionId())
                        .questionText(question.getQuestionText())
                        .formattedTimestamp(formattedTimestamp)
                        .build();
                customDataList.add(customData);
            }
        }

        return customDataList;
    }




}



