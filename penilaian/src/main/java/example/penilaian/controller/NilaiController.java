package example.penilaian.controller;

import example.penilaian.entity.Nilai;
import example.penilaian.model.NilaiByUser;
import example.penilaian.model.NilaiResponse;
import example.penilaian.service.NilaiService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public List<NilaiResponse> getAllNilai() {
        return nilaiService.getAllNilai();
    }

    @DeleteMapping("/{username}/{teamName}/{timestamp}")
    public ResponseEntity<String> deleteNilai(
            @PathVariable String username,
            @PathVariable String teamName,
            @PathVariable String timestamp) {

//        System.out.println("Username yang diterima: " + username);
//        System.out.println("Team Name yang diterima: " + teamName);
//        System.out.println("Timestamp yang diterima: " + timestamp);

        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date date = java.sql.Date.valueOf(timestamp);
            nilaiService.deleteNilai(username, teamName, date);
            return ResponseEntity.ok("Data dengan " + username +  " - " +  teamName +" - "+ timestamp + " ini Berhasil dihapus");
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }
}
