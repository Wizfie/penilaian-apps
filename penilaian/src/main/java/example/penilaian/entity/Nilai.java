    package example.penilaian.entity;


    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.sql.Date;
    import java.sql.Timestamp;

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

        @Column(name = "timestamp")
        private Date timestamp;



    }

