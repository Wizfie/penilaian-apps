package example.penilaian.entity.penilaianYelyel;


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
@Table(name = "subscriteria_yelyel")
public class SubscriteriaYelyel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SubscriteriaId;

    private String SubscriteriaName;
    private Double MaxPoint;

    @ManyToOne
    @JoinColumn(name = "criteria_id")
    private CriteriaYelyel criteriaYelyel;

}
