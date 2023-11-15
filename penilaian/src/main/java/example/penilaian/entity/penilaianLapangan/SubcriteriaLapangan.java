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
@Table(name = "subcriteria_lapangan")
public class SubcriteriaLapangan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subcriteriaId;

    private String subcriteriaName;

    @ManyToOne
    @JoinColumn(name = "criteria_id")
    private CriteriaLapangan criteriaLapangan;

}
