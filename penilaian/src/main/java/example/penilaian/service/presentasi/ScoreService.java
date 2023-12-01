package example.penilaian.service.presentasi;

import example.penilaian.entity.presentasi.Score;
import example.penilaian.repository.presentasi.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Transactional
    public void saveScore(List<Score> evaluations) {
        try {
            java.util.Date currentDate = new java.util.Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            for (Score eval : evaluations) {

                // Cek dan set nilai createdAt jika null
                if (eval.getCreatedAt() == null) {
                    String formattedDate = sdf.format(currentDate);
                    eval.setCreatedAt(Date.valueOf(formattedDate));
                }

                // Validasi tanggal createdAt
                if (eval.getCreatedAt().after(new Date(currentDate.getTime()))) {
                    throw new IllegalArgumentException("Invalid createdAt date for " + eval.getTitle() +
                            ". Please enter a valid createdAt date.");
                }

                Optional<Score> existingScore = scoreRepository.findByTitleAndUsernameAndTeamNameAndCreatedAt(
                        eval.getTitle(), eval.getUsername(), eval.getTeamName(), eval.getCreatedAt());

                if (existingScore.isPresent()) {
                    Score existing = existingScore.get();
                    existing.setScore(eval.getScore());
                    scoreRepository.save(existing);
                } else {
                    scoreRepository.save(eval);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while saving scores");
        }
    }


    public void addScores(List<Score> scores) {
        scoreRepository.saveAll(scores);
    }
}
