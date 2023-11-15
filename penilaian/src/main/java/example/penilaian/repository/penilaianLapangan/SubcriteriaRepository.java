package example.penilaian.repository.penilaianLapangan;

import example.penilaian.entity.penilaianLapangan.SubcriteriaLapangan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubcriteriaRepository  extends JpaRepository<SubcriteriaLapangan, Integer> {
}
