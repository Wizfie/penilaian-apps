package example.penilaian.service;

import example.penilaian.entity.Nilai;
import example.penilaian.entity.Question;
import example.penilaian.model.CustomData;
import example.penilaian.repository.NilaiRepository;
import example.penilaian.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NilaiService {


    @Autowired
    private NilaiRepository nilaiRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Transactional
    public void saveNilai(List<Nilai> nilaiData){
        nilaiRepository.saveAll(nilaiData);
    }


    public List<CustomData> getNilaiByUser(String username) {
        List<Nilai> nilaiList = nilaiRepository.findByUsername(username);
        List<CustomData> customDataList = new ArrayList<>();

        for (Nilai nilai : nilaiList) {
            Question question = questionRepository.findById(nilai.getQuestionId()).orElse(null);

            if (question != null) {
                CustomData customData = new CustomData(username, nilai.getTeamName(), nilai.getNilai(), question.getQuestionText());
                customDataList.add(customData);
            }
        }

        return customDataList;
    }



}



