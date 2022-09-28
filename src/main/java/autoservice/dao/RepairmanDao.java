package autoservice.dao;

import autoservice.model.Order;
import autoservice.model.Repairman;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairmanDao extends JpaRepository<Repairman, Long> {
    @Query("SELECT o FROM Order o "
            + "JOIN FETCH o.favors f "
            + "JOIN FETCH f.repairman r "
            + "WHERE r.id = f.repairman.id "
            + "AND r.id = ?1")
    List<Order> getOrders(Long id);

    @Query("SELECT repairman FROM Repairman repairman WHERE repairman.id in "
            + "(SELECT r.id FROM Order o "
            + "JOIN o.favors f "
            + "JOIN f.repairman r "
            + "WHERE o.id = ?1)")
    List<Repairman> getRepairmans(Long id);
}
