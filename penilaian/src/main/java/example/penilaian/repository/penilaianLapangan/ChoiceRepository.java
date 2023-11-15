package example.penilaian.repository.penilaianLapangan;

import example.penilaian.entity.penilaianLapangan.MultipleChoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoiceRepository extends JpaRepository <MultipleChoice ,Integer> {
}
