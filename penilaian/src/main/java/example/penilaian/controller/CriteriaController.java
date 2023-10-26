package example.penilaian.controller;

import example.penilaian.entity.Criteria;
import example.penilaian.model.WebResponse;
import example.penilaian.service.CriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/criteria")
public class CriteriaController {

    @Autowired
    private CriteriaService criteriaService;


    @GetMapping("/all")
    public List<Criteria> getAllCriteria() {
        return criteriaService.getAllCriteria();
    }
}
