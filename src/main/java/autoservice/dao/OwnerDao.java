package autoservice.dao;

import autoservice.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerDao extends JpaRepository<Owner, Long> {
}
