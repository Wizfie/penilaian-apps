package example.penilaian.service;

import example.penilaian.entity.Subcriteria;
import example.penilaian.repository.SubcriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcriteriaService {

    @Autowired
    private SubcriteriaRepository subcriteriaRepository;

    public List<Subcriteria> getAllSubcriteria(){
        return subcriteriaRepository.findAll();
    }
}
