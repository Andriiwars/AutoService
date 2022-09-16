package autoservice.dao;

import autoservice.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDao extends JpaRepository<Car, Long> {
}
