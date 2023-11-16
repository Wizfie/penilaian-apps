package example.penilaian.entity.penilaianYelyel;


import example.penilaian.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "point_yelyel")
public class PointsYelyel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pointId;

    private Double point;

    @ManyToOne
    @JoinColumn(name = "team_name")
    private TeamsYelyel teamsYelyel;

    @ManyToOne
    @JoinColumn(name = "subscriteria_name")
    private SubscriteriaYelyel subscriteriaYelyel;

    @ManyToOne
    @JoinColumn(name = "username")
    private Users users;

    @CreatedDate
    private LocalDateTime createAt;

}
