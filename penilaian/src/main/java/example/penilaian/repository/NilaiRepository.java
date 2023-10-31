package example.penilaian.repository;

import example.penilaian.entity.Nilai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NilaiRepository extends JpaRepository<Nilai, Long> {
}
