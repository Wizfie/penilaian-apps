package example.penilaian.repository;

import example.penilaian.entity.Nilai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;


@Repository
public interface NilaiRepository extends JpaRepository<Nilai, Integer> {

   List<Nilai> findByUsername(String username);

   @Transactional
   @Modifying
   @Query("DELETE FROM Nilai n WHERE n.timestamp = ?1")
   void deleteByTimestamp(Timestamp timestamp);

}
