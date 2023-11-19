package example.penilaian.service.penilaianYelyel;

import example.penilaian.entity.penilaianYelyel.PointsYelyel;
import example.penilaian.repository.penilaianYelyel.PointRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;

    @Transactional
    public void SavePoint(List<PointsYelyel> PointData){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String formattedDate = sdf.format(new java.util.Date());
        java.sql.Date currentDate = java.sql.Date.valueOf(formattedDate);
        for (PointsYelyel pointsYelyel : PointData) {
            // Menggunakan tanggal saat ini jika properti createAt belum diatur
            if (pointsYelyel.getCreateAt() == null) {
                pointsYelyel.setCreateAt(currentDate);
            }
        }
        pointRepository.saveAll(PointData);
    }

    public List<PointsYelyel> getALlPoint(){
        return pointRepository.findAll();
    }

}
