package example.penilaian.model.penilaianYelyel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointsDTO {
    String username;
    String teamName;
    String subscriteriaName;
    Double point;

}
