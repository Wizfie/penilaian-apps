package example.penilaian.controller.penilaianYelyel;


import example.penilaian.entity.penilaianYelyel.TeamsYelyel;
import example.penilaian.service.penilaianYelyel.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/teams")
public class TeamsController {

    @Autowired
    private TeamsService teamsService;

    @GetMapping("/all")
    public List<TeamsYelyel> getAllTeam(){
        return teamsService.getAll();
    }
}
