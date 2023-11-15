package example.penilaian.repository.penilaianLapangan;


import example.penilaian.entity.penilaianLapangan.CriteriaLapangan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriteriaRepository extends JpaRepository<CriteriaLapangan, Integer> {

}
