package example.penilaian.service;

import example.penilaian.entity.Question;
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
