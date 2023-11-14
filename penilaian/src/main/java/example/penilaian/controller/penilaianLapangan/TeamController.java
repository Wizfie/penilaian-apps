package example.penilaian.controller.penilaianLapangan;


import example.penilaian.entity.penilaianLapangan.Team;
import example.penilaian.service.penilaianLapangan.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@CrossOrigin("http://192.168.43.176:5173/")
@CrossOrigin("*")

@RequestMapping("/api")
public class    TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/getAllTeam")
    public List<Team> getAllTeam(){
        return teamService.getAllTeam();
    }
}
