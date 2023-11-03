package example.penilaian.controller;


import example.penilaian.entity.Nilai;
import example.penilaian.model.CustomData;
import example.penilaian.model.WebResponse;
import example.penilaian.service.NilaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin("http://192.168.42.86:5173/")
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

    @GetMapping("/nilai-list")
    public List<CustomData> getCustomDataByUser(@RequestParam String username) {
        return nilaiService.getNilaiByUser(username);
    }


}
