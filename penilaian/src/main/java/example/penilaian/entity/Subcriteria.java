package example.penilaian.entity;


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
@Table(name = "subcriteria")
public class Subcriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subcriteriaId;

    private String subcriteriaName;

    @ManyToOne
    @JoinColumn(name = "criteria_id")
    private Criteria criteria;

}
