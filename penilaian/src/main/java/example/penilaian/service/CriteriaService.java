package example.penilaian.service;

import example.penilaian.entity.Criteria;
import example.penilaian.model.CriteriaResponse;
import example.penilaian.repository.CriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CriteriaService {

    @Autowired
    private CriteriaRepository criteriaRepository;

    public List<CriteriaResponse> getAllCriteria() {
        List<Criteria> criteriaList = criteriaRepository.findAll();
        return criteriaList.stream()
                .map(this::convertToCriteriaResponse)
                .collect(Collectors.toList());
    }

    private CriteriaResponse convertToCriteriaResponse(Criteria criteria) {
        return CriteriaResponse.builder()
                .criteriaId(criteria.getCriteriaId())
                .criteriaName(criteria.getCriteriaName())
                .build();
    }
}
