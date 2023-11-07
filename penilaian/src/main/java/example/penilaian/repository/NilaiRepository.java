package example.penilaian.repository;

import example.penilaian.entity.Nilai;
import example.penilaian.model.CustomData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface NilaiRepository extends JpaRepository<Nilai, Long> {

   List<Nilai> findByUsername(String username);

}
