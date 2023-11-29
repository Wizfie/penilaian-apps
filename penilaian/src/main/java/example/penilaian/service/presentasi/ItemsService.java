package example.penilaian.service.presentasi;

import example.penilaian.entity.presentasi.Items;
import example.penilaian.repository.presentasi.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsService {

    @Autowired
    private ItemsRepository itemsRepository;

    public List<Items> getAll(){
      return  itemsRepository.findAll();
    }
}

