package example.penilaian.entity.presentasi;

import example.penilaian.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "score_presentasi")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Double score;

    @ManyToOne
    @JoinColumn(name = "points_id")
    private Points points;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

}
