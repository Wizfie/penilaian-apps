package example.penilaian.service.penilaianLapangan;

import example.penilaian.entity.penilaianLapangan.CriteriaLapangan;
import example.penilaian.repository.penilaianLapangan.CriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriteriaService {

    @Autowired
    private CriteriaRepository criteriaRepository;

    public List<CriteriaLapangan> getAllCriteria() {
        return criteriaRepository.findAll();
    }
}
