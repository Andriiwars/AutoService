package autoservice.dao;

import autoservice.model.Repairman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairmanDao extends JpaRepository<Repairman, Long> {

}
