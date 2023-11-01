package example.penilaian.repository;

import example.penilaian.entity.MultipleChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MuliplechoiceRepository  extends JpaRepository <MultipleChoice ,Long> {
}
