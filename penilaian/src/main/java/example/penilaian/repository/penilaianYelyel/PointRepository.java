package example.penilaian.repository.penilaianYelyel;

import example.penilaian.entity.penilaianYelyel.PointsYelyel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PointRepository extends JpaRepository <PointsYelyel , Integer> {

    List<PointsYelyel> findByUsernameAndTeamNameAndCreateAt(String username, String teamName, Date createAt);
}
