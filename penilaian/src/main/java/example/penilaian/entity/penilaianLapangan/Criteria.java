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
@Table(name = "criteria_lapangan")

public class Criteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int criteriaId;

    private String criteriaName;

}
