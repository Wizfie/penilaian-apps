package example.penilaian.repository.penilaianYelyel;

import example.penilaian.entity.penilaianYelyel.PointsYelyel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository <PointsYelyel , Integer> {
}
