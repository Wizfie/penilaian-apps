package example.penilaian.repository;

import example.penilaian.entity.penilaianLapangan.Nilai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;


@Repository
public interface NilaiRepository extends JpaRepository<Nilai, Integer> {

   List<Nilai> findByUsername(String username);
   List<Nilai> findByUsernameAndTimestampAndQuestionIdAndTeamName(
           String username,
           Date timestamp,
           int questionId,
           String teamName
   );

   void deleteByTimestampAndUsernameAndTeamName(Date timestamp, String username, String teamName);


//   @Transactional
//   @Modifying
//   @Query("DELETE FROM Nilai n WHERE n.timestamp = ?1")
//   void deleteByTimestamp(Date timestamp);
//
//   void deleteByTimestampAndUsernameAndNamaTeam(Date timestamp, String username, String namaTeam);

}
