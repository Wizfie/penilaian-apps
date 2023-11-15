package example.penilaian.model.penilaianYelyel;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CriteriaResponseDTO {

    private int criteriaId;
    private String criteriaName;
    List<SubscriteriaResponseDTO> subscriteria;
}
