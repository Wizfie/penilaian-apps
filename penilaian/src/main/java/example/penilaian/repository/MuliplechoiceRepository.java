package example.penilaian.repository;

import example.penilaian.entity.MultipleChoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MuliplechoiceRepository  extends JpaRepository <MultipleChoice ,Long> {
}
