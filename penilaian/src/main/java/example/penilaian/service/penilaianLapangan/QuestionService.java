package example.penilaian.service.penilaianLapangan;

import example.penilaian.entity.penilaianLapangan.Question;
import example.penilaian.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;


    public List<Question> getALlQuestion(){
        return questionRepository.findAll();
    }

}
