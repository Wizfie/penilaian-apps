package example.penilaian.repository.presentasi;

import example.penilaian.entity.presentasi.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score , Integer> {


    @Query("SELECT s FROM Score s WHERE s.username = ?1 AND s.teamName = ?2 AND s.createdAt = ?3")
    List<Score> findByUsernameAndTeamNameAndCreatedAt(String username, String teamName, Date createdAt);}
