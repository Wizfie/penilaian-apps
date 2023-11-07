package example.penilaian.entity;


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
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    private String questionText;

    @OneToMany(mappedBy = "question") // Sesuaikan dengan nama field yang sesuai
    private Set<MultipleChoice> choices;

    @ManyToOne
    @JoinColumn(name = "subcriteria_id")
    private Subcriteria subcriteria;


}
