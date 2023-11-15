package example.penilaian.entity.penilaianLapangan;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "questions_lapangan")
public class QuestionLapangan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    private String questionText;


    @ManyToOne
    @JoinColumn(name = "subcriteria_id")
    private SubcriteriaLapangan subcriteriaLapangan;


    @JsonIgnore
    @OneToMany(mappedBy = "questionLapangan") // Sesuaikan dengan nama field yang sesuai
    private Set<MultipleChoice> choices;

}
