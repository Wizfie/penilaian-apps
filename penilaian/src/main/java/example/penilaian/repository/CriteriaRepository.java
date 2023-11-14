package example.penilaian.repository;


import example.penilaian.entity.penilaianLapangan.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriteriaRepository extends JpaRepository<Criteria , Integer> {

}
