package example.penilaian.controller.presentasi;

import example.penilaian.entity.presentasi.Items;
import example.penilaian.service.presentasi.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class itemsController {
    @Autowired
    private ItemsService itemsService;


    @GetMapping("/items-presentasi")
    public List<Items> getAll(){
        return itemsService.getAll();
    }
}
