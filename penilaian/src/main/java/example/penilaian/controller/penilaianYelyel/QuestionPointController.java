package example.penilaian.controller.penilaianYelyel;

import example.penilaian.model.penilaianYelyel.CriteriaResponseDTO;
import example.penilaian.service.penilaianYelyel.CriteriaYelyelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/question")
@CrossOrigin("*")
public class QuestionPointController {

    @Autowired
    private CriteriaYelyelService criteriaYelyelService;

    @GetMapping("/all")
    public ResponseEntity<List<CriteriaResponseDTO>> getAllQuestion(){
        List<CriteriaResponseDTO> response = criteriaYelyelService.getAllQuestion();
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

}
