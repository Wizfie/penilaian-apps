package example.penilaian.repository.penilaianLapangan;


import example.penilaian.entity.penilaianLapangan.QuestionLapangan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionLapangan, Integer> {
}
