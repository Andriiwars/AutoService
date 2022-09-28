package autoservice.dao;

import autoservice.model.Favor;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FavorDao extends JpaRepository<Favor, Long> {
    List<Favor> getFavorsByRepairmanIdAndOrderId(Long repairmanId, Long orderId);

    @Transactional
    @Modifying
    @Query("UPDATE Favor SET status = ?2 WHERE id = ?1")
    void updateFavorStatus(Long id, Favor.Status status);
}
