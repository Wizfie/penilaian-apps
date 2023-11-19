package example.penilaian.controller.penilaianYelyel;

import example.penilaian.entity.penilaianYelyel.PointsYelyel;
import example.penilaian.service.penilaianYelyel.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PointController {

    @Autowired
    private PointService pointService;

    @PostMapping("/savePoint")
    public ResponseEntity<String> savePoint(@RequestBody List<PointsYelyel> pointData){
        try {
            pointService.SavePoint(pointData);
            return ResponseEntity.ok("Data Saved");

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed save data" + e);
        }
    }


    @GetMapping("/point/all")
    public List<PointsYelyel> getALlPoint(){
        return pointService.getALlPoint();
    }
}
