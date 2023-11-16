package example.penilaian.controller.penilaianYelyel;

import example.penilaian.model.penilaianYelyel.PointsDTO;
import example.penilaian.service.penilaianYelyel.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind .annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class PointController {

    @Autowired
    private PointService pointService;

    @PostMapping("/save-point")
    public ResponseEntity<String> savePoint(@RequestBody List<PointsDTO> pointsDTOList){
        try {
            pointService.savePointsYelyel(pointsDTOList);
            return ResponseEntity.ok("Data Saved");
        } catch (Exception e ){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error save data" + e.getMessage());

        }

    }

}
