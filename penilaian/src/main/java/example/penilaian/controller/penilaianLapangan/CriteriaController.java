package example.penilaian.controller.penilaianLapangan;

import example.penilaian.entity.penilaianLapangan.CriteriaLapangan;
import example.penilaian.service.penilaianLapangan.CriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CriteriaController {

    @Autowired
    private CriteriaService criteriaService;


    @GetMapping("/criteriaAll")
    public List<CriteriaLapangan> getAllCriteria() {
        return criteriaService.getAllCriteria();
    }
}
