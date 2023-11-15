package example.penilaian.model.penilaianYelyel;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriteriaResponseDTO {
    private int subsId;
    private String subsName;
    private Double maxPoint;
    private int criteriaId;
}
