package example.penilaian.controller;


import example.penilaian.entity.Nilai;
import example.penilaian.service.NilaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin("http://192.168.43.176:5173/")
@CrossOrigin("*")

@RequestMapping("/api")
public class NilaiController {

    @Autowired
    private NilaiService nilaiService;

    @PostMapping("/save-nilai")
    public String saveNilai(@RequestBody List<Nilai> nilaiData){
        nilaiService.saveNilai(nilaiData);
        return  "Data Berhasil masuk";
    }


}
