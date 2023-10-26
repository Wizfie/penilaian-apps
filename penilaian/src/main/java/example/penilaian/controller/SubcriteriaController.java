package example.penilaian.controller;


import example.penilaian.entity.Subcriteria;
import example.penilaian.service.SubcriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/subcriteria")

public class SubcriteriaController {

    @Autowired
    private SubcriteriaService subcriteriaService;

    @GetMapping("/all")
    public List<Subcriteria> getAllSubcriteria(){
        return subcriteriaService.getAllSubcriteria();
    }
}
