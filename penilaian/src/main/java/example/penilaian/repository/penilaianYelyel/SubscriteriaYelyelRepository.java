package example.penilaian.repository.penilaianYelyel;

import example.penilaian.entity.penilaianYelyel.CriteriaYelyel;
import example.penilaian.entity.penilaianYelyel.SubscriteriaYelyel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SubscriteriaYelyelRepository extends JpaRepository<SubscriteriaYelyel , Integer> {
    List<SubscriteriaYelyel> findByCriteriaYelyel(CriteriaYelyel criteria);
}
