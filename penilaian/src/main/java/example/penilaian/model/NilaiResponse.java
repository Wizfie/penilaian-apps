package example.penilaian.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NilaiResponse {
    private int nilaiId;
    private double nilai;
    private String teamName;
    private String username;
    private int questionId;
    private String  timestamp;
    private String questionText;
}
