package example.penilaian.controller.penilaianLapangan;


import example.penilaian.entity.penilaianLapangan.SubcriteriaLapangan;
import example.penilaian.service.penilaianLapangan.SubcriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")

public class SubcriteriaController {

    @Autowired
    private SubcriteriaService subcriteriaService;

    @GetMapping("/subcriteriaAll")
    public List<SubcriteriaLapangan> getAllSubcriteria(){
        return subcriteriaService.getAllSubcriteria();
    }
}
