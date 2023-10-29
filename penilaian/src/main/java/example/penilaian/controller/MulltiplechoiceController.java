package example.penilaian.controller;

import example.penilaian.entity.MultipleChoice;
import example.penilaian.service.MultiplechoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class MulltiplechoiceController {

    @Autowired
    private MultiplechoiceService multiplechoiceService;

    @GetMapping("/choiceAll")
    public List<MultipleChoice> getAllChoice(){
        return multiplechoiceService.getAllChoice();
    }


}
