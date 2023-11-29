package example.penilaian.entity.presentasi;

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
@Table(name = "points_presentasi")
public class Points {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;

    private Double maxValue;

    @ManyToOne
    @JoinColumn(name = "items_id")
    private Items items;
}
