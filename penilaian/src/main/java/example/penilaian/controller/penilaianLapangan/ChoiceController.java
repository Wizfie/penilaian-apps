package example.penilaian.controller.penilaianLapangan;

import example.penilaian.entity.penilaianLapangan.MultipleChoice;
import example.penilaian.service.penilaianLapangan.ChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChoiceController {

    @Autowired
    private ChoiceService choiceService;

    @GetMapping("/choiceAll")
    public List<MultipleChoice> getAllChoice(){
        return choiceService.getAllChoice();
    }


}
