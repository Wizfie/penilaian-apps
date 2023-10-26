package example.penilaian.controller;

import example.penilaian.model.CriteriaResponse;
import example.penilaian.model.WebResponse;
import example.penilaian.service.CriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/criteria/all")
public class CriteriaController {

    @Autowired
    private CriteriaService criteriaService;

    @GetMapping
    public ResponseEntity<WebResponse<List<CriteriaResponse>>> getAllCriteria() {
        List<CriteriaResponse> criteriaList = criteriaService.getAllCriteria();
        WebResponse<List<CriteriaResponse>> webResponse = new WebResponse<>();
        webResponse.setData(criteriaList);
        webResponse.setErrors(null);
        return new ResponseEntity<>(webResponse, HttpStatus.OK);
    }
}
