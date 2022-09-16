package autoservice.dao;

import autoservice.model.Favor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavorDao extends JpaRepository<Favor, Long> {
    List<Favor> getFavorsByRepairmanIdAndOrderId(Long repairmanId, Long orderId);
}
