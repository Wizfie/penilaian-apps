package example.penilaian.service.penilaianLapangan;

import example.penilaian.entity.penilaianLapangan.Subcriteria;
import example.penilaian.repository.SubcriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcriteriaService {

    @Autowired
    private SubcriteriaRepository subcriteriaRepository;

    public List<Subcriteria> getAllSubcriteria(){
        return subcriteriaRepository.findAll();
    }
}
