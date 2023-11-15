package example.penilaian.service.penilaianLapangan;


import example.penilaian.entity.penilaianLapangan.TeamsLapangan;
import example.penilaian.repository.penilaianLapangan.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {


    @Autowired
    private TeamRepository teamRepository;

    public List<TeamsLapangan> getAllTeam(){
        return teamRepository.findAll();
    }
}
