package example.penilaian.controller;

import example.penilaian.entity.Nilai;
import example.penilaian.model.NilaiByUser;
import example.penilaian.model.NilaiResponse;
import example.penilaian.service.NilaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin("*")

@RequestMapping("/api")
public class NilaiController {

    @Autowired
    private NilaiService nilaiService;

    @PostMapping("/save-nilai")
    public String saveNilai(@RequestBody List<Nilai> nilaiData) {
        nilaiService.saveNilai(nilaiData);
        return "Data Berhasil masuk";
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
    public List<NilaiByUser> getCustomDataByUser(@RequestParam String username) {
        return nilaiService.getNilaiByUser(username);
    }

    @GetMapping("/nilai-getAll")
    public List<NilaiResponse>getAllNilai(){
        return nilaiService.getAllNilai();
    }

    @DeleteMapping("/{timestamp}")
    public ResponseEntity<String> deleteByTime(@PathVariable Timestamp timestamp) {
        System.out.println("Timestamp yang diterima: " + timestamp);

        try {
            nilaiService.deleteNilai(timestamp);
            return ResponseEntity.ok("Data dengan " + timestamp + " ini Berhasil di hapus");

        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }

    }

}
