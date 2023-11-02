package example.penilaian.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomData {

    private String username;
    private String teamName;
    private double nilai;
    private String questionText;
}
