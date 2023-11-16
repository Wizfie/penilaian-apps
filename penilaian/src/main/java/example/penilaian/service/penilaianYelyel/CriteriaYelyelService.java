package example.penilaian.service.penilaianYelyel;


import example.penilaian.entity.penilaianYelyel.CriteriaYelyel;
import example.penilaian.entity.penilaianYelyel.SubscriteriaYelyel;
import example.penilaian.model.penilaianYelyel.CriteriaResponseDTO;
import example.penilaian.model.penilaianYelyel.SubscriteriaResponseDTO;
import example.penilaian.repository.penilaianYelyel.CriteriaYelyelRepository;
import example.penilaian.repository.penilaianYelyel.SubscriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CriteriaYelyelService {
    @Autowired
    private CriteriaYelyelRepository criteriaRepository;

    @Autowired
    private SubscriteriaRepository subcriteriaRepository;

    public List<CriteriaResponseDTO> getAllQuestion() {
        List<CriteriaYelyel> criteriaList = criteriaRepository.findAll();
        return criteriaList.stream().map(criteria -> {
            List<SubscriteriaYelyel> subscriteriaList = subcriteriaRepository.findByCriteriaYelyel(criteria);
            List<SubscriteriaResponseDTO> subscriteriaDTOList = subscriteriaList.stream().map(subscriteria -> SubscriteriaResponseDTO.builder()
                            .subsId(subscriteria.getSubscriteriaId())
                            .subsName(subscriteria.getSubscriteriaName())
                            .maxPoint(subscriteria.getMaxPoint())
                            .criteriaId(subscriteria.getCriteriaYelyel().getCriteriaId())
                            .build())
                    .collect(Collectors.toList());

            return CriteriaResponseDTO.builder()
                    .criteriaId(criteria.getCriteriaId())
                    .criteriaName(criteria.getCriteriaName())
                    .subscriteria(subscriteriaDTOList)
                    .build();
        }).collect(Collectors.toList());
    }
}
