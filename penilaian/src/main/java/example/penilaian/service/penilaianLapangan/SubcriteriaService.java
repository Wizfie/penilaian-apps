package example.penilaian.service.penilaianLapangan;

import example.penilaian.entity.penilaianLapangan.SubcriteriaLapangan;
import example.penilaian.repository.penilaianLapangan.SubcriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcriteriaService {

    @Autowired
    private SubcriteriaRepository subcriteriaRepository;

    public List<SubcriteriaLapangan> getAllSubcriteria(){
        return subcriteriaRepository.findAll();
    }
}
