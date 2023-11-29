package example.penilaian.repository.presentasi;

import example.penilaian.entity.presentasi.Points;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointsRepository extends JpaRepository<Points , Integer> {

}
