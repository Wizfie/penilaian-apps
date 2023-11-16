package example.penilaian.service.penilaianYelyel;

import example.penilaian.entity.Users;
import example.penilaian.entity.penilaianYelyel.PointsYelyel;
import example.penilaian.entity.penilaianYelyel.SubscriteriaYelyel;
import example.penilaian.entity.penilaianYelyel.TeamsYelyel;
import example.penilaian.model.penilaianYelyel.PointsDTO;
import example.penilaian.repository.UserRepository;
import example.penilaian.repository.penilaianYelyel.PointRepository;
import example.penilaian.repository.penilaianYelyel.SubscriteriaRepository;
import example.penilaian.repository.penilaianYelyel.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository ;

    @Autowired
    private SubscriteriaRepository subscriteriaRepository;

    @Autowired
    private UserRepository  userRepository ;

    @Autowired
    private TeamsRepository teamsRepository ;


    public void savePointsYelyel(List<PointsDTO> pointsDTOList) {
        for (PointsDTO dto : pointsDTOList) {
            PointsYelyel pointsYelyel = new PointsYelyel();



            SubscriteriaYelyel subscriteria = subscriteriaRepository.findBysubscriteriaName(dto.getSubscriteriaName())
                    .orElseThrow(() -> new IllegalArgumentException("Subcriteria not found"));

            // Cek apakah nilai poin melebihi maxPoint dari subkriteria
            if (dto.getPoint() > subscriteria.getMaxPoint()) {
                throw new IllegalArgumentException("Nilai melebihi MaxPoint untuk kriteria ini.");
            }

            // Mengambil atau membuat objek TeamsYelyel berdasarkan nama tim dari DTO
            TeamsYelyel team = teamsRepository.findByTeamName(dto.getTeamName())
                    .orElseGet(() -> {
                        TeamsYelyel newTeam = new TeamsYelyel();
                        newTeam.setTeamName(dto.getTeamName());
                        return teamsRepository.save(newTeam);
                    });

            pointsYelyel.setTeamsYelyel(team);

            // Set objek SubscriteriaYelyel yang telah ditemukan
            pointsYelyel.setSubscriteriaYelyel(subscriteria);

            // Mengambil atau membuat objek Users berdasarkan ID pengguna dari DTO
            Users user = userRepository.findById(dto.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            pointsYelyel.setUsers(user);

            // Menyimpan ke database menggunakan repository
            pointRepository.save(pointsYelyel);
        }
    }

}
