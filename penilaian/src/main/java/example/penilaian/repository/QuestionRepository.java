package example.penilaian.repository;


import example.penilaian.entity.penilaianLapangan.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question , Integer> {
}
