package example.penilaian.service;


import example.penilaian.entity.Team;
import example.penilaian.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {


    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getAllTeam(){
        return teamRepository.findAll();
    }
}
