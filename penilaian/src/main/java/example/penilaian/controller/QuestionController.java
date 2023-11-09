package example.penilaian.controller;


import example.penilaian.entity.Question;
import example.penilaian.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")

public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/questionAll")
    public List<Question> getAllQuestion(){
        return questionService.getALlQuestion();
    }
}
