package example.penilaian.repository;

import example.penilaian.entity.MultipleChoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoiceRepository extends JpaRepository <MultipleChoice ,Integer> {
}
