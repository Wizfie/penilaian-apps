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
@Table(name = "nilai" )
public class Nilai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nilaiId;

    private double nilai;
    private String teamName;
    private String username;
    private int questionId;



}
//    @ManyToOne
//    @JoinColumn(name = "users_id")
//    private Users users;
//    @ManyToOne
//    @JoinColumn(name = "team_id")
//    private Team team;

//    @ManyToOne
//    @JoinColumn(name = "question_id")
//    private Question question;
