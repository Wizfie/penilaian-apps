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
@Table(name = "multiple_choice_lapangan")
public class MultipleChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int choiceId;
    private Double choiceValue;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;


}
