package example.penilaian.service.presentasi;

import example.penilaian.entity.presentasi.Score;
import example.penilaian.repository.presentasi.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Transactional
    public void saveScore(List<Score> evaluations) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(new java.util.Date());
            java.sql.Date currentDate = java.sql.Date.valueOf(formattedDate);

            for (Score eval : evaluations) {
                // Check if createdAt is null, set it to the current date
                if (eval.getCreatedAt() == null) {
                    eval.setCreatedAt(currentDate);
                }

                // Retrieve existing data based on username, teamName, and createdAt from the current eval
                List<Score> existingScores = scoreRepository.findByUsernameAndTeamNameAndCreatedAt(eval.getUsername(), eval.getTeamName(), eval.getCreatedAt());

                if (!existingScores.isEmpty()) {
                    // Replace existing data
                    for (Score existingScore : existingScores) {
                        existingScore.setUsername(eval.getUsername());
                        existingScore.setTeamName(eval.getTeamName());
                        existingScore.setTitle(eval.getTitle());
                        existingScore.setScore(eval.getScore());
                        existingScore.setCreatedAt(eval.getCreatedAt());
                        scoreRepository.save(existingScore);
                    }
                } else {
                    // Create new record
                    scoreRepository.save(eval);
                }
            }
        } catch (Exception e) {
            // Handle error/exception here (log the error, throw custom exception, etc.)
            e.printStackTrace(); // Example: Printing stack trace
            // You can throw a custom exception or handle the error accordingly
            // For example: throw new CustomException("Error occurred while saving scores");
        }
    }
}
