package example.penilaian.service;

import example.penilaian.entity.Criteria;
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
