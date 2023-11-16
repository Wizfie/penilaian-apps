package example.penilaian.service.penilaianYelyel;

import example.penilaian.entity.penilaianYelyel.TeamsYelyel;
import example.penilaian.repository.penilaianYelyel.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamsService
{
    @Autowired
    private TeamsRepository teamsRepository;

    public List<TeamsYelyel> getAll(){
        return teamsRepository.findAll();
    }
}
