package example.penilaian.service;

import example.penilaian.entity.Nilai;
import example.penilaian.repository.NilaiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NilaiService {


    @Autowired
    private NilaiRepository nilaiRepository;

    @Transactional
    public void saveNilai(List<Nilai> nilaiData){
        nilaiRepository.saveAll(nilaiData);
    }
}
