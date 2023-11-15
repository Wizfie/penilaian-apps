package example.penilaian.repository.penilaianYelyel;

import example.penilaian.entity.penilaianYelyel.CriteriaYelyel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriteriaYelyelRepository extends JpaRepository <CriteriaYelyel , Integer> {
}
