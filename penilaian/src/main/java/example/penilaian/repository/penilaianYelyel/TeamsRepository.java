package example.penilaian.repository.penilaianYelyel;

import example.penilaian.entity.penilaianYelyel.TeamsYelyel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamsRepository  extends JpaRepository <TeamsYelyel , Integer> {
    Optional<TeamsYelyel> findByTeamName(String teamName);
}
