package example.penilaian.controller;


import example.penilaian.entity.Nilai;
import example.penilaian.model.CustomData;
import example.penilaian.model.WebResponse;
import example.penilaian.service.NilaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("/update-nilai")
    public ResponseEntity<List<Nilai>> updateNilai(@RequestBody List<Nilai> updatedNilaiList) {
        List<Nilai> updatedNilaiListResult = nilaiService.updateNilai(updatedNilaiList);

        if (!updatedNilaiListResult.isEmpty()) {
            return new ResponseEntity<>(updatedNilaiListResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/nilai-list")
    public List<CustomData> getCustomDataByUser(@RequestParam String username) {
        return nilaiService.getNilaiByUser(username);
    }


}
