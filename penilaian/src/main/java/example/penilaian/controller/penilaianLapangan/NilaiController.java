package example.penilaian.controller.penilaianLapangan;

import example.penilaian.entity.penilaianLapangan.Nilai;
import example.penilaian.model.penilaianLapangan.NilaiByUser;
import example.penilaian.model.penilaianLapangan.NilaiResponse;
import example.penilaian.service.penilaianLapangan.NilaiService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")

@RequestMapping("/api")
public class NilaiController {

    @Autowired
    private NilaiService nilaiService;

    @PostMapping("/save-nilai")
    public ResponseEntity<String> saveNilai(@RequestBody List<Nilai> nilaiData) {
        System.out.println("Received request with data: " + nilaiData);
        try {
            nilaiService.saveNilai(nilaiData);
            return ResponseEntity.ok("Data berhasil masuk");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Gagal menyimpan data: " + e.getMessage());
        }
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
