package co.ccc.pmv.repository;

import co.ccc.pmv.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByUpcomingServiceDateBefore(LocalDate date);

    @Query("SELECT v FROM Vehicle v ORDER BY v.upcomingServiceDate ASC")
    List<Vehicle> findAllOrderByUpcomingServiceDate();
}
