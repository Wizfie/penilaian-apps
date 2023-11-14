package example.penilaian.service.penilaianLapangan;

import example.penilaian.entity.penilaianLapangan.Criteria;
import example.penilaian.repository.CriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriteriaService {

    @Autowired
    private CriteriaRepository criteriaRepository;

    public List<Criteria> getAllCriteria() {
        return criteriaRepository.findAll();
    }
}
