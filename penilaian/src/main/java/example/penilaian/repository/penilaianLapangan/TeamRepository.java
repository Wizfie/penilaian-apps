package example.penilaian.repository.penilaianLapangan;


import example.penilaian.entity.penilaianLapangan.TeamsLapangan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamsLapangan, Integer> {
}
