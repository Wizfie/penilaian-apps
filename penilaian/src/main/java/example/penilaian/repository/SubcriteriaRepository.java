package example.penilaian.repository;

import example.penilaian.entity.Subcriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubcriteriaRepository  extends JpaRepository<Subcriteria, Long> {
}