package example.penilaian.service;


import example.penilaian.entity.MultipleChoice;
import example.penilaian.repository.MuliplechoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultiplechoiceService {
    @Autowired
    private MuliplechoiceRepository muliplechoiceRepository;

    public List<MultipleChoice> getAllChoice(){
        return muliplechoiceRepository.findAll();
    }
}
