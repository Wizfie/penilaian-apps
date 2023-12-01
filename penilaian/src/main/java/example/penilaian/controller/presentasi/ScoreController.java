package example.penilaian.controller.presentasi;

import example.penilaian.entity.presentasi.Score;
import example.penilaian.service.presentasi.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @PostMapping("/save-score")
    public ResponseEntity<String> saveScore(@RequestBody List<Score> evaluations) {
        try {
            scoreService.saveScore(evaluations);
            return ResponseEntity.ok("Scores saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while saving scores");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addScores(@RequestBody List<Score> scores) {
        try {
            scoreService.addScores(scores);
            return ResponseEntity.status(HttpStatus.CREATED).body("Score data added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add score data");
        }
    }
}
