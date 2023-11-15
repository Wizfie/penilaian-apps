package example.penilaian.entity.penilaianLapangan;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teams_lapangan")
public class TeamsLapangan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int teamId;

    private String teamName;


}
