package example.penilaian.service.penilaianLapangan;

import example.penilaian.entity.penilaianLapangan.QuestionLapangan;
import example.penilaian.repository.penilaianLapangan.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;


    public List<QuestionLapangan> getALlQuestion(){
        return questionRepository.findAll();
    }

}
