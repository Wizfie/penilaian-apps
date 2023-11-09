package example.penilaian.service;


import example.penilaian.entity.MultipleChoice;
import example.penilaian.repository.ChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoiceService {
    @Autowired
    private ChoiceRepository choiceRepository;

    public List<MultipleChoice> getAllChoice(){
        return choiceRepository.findAll();
    }
}
