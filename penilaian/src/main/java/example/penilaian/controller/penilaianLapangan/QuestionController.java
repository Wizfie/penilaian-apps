package example.penilaian.controller.penilaianLapangan;


import example.penilaian.entity.penilaianLapangan.QuestionLapangan;
import example.penilaian.service.penilaianLapangan.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/questionAll")
    public List<QuestionLapangan> getAllQuestion(){
        return questionService.getALlQuestion();
    }
}
