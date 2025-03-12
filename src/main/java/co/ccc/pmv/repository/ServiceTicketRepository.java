package co.ccc.pmv.repository;

import co.ccc.pmv.dto.MonthlyServiceReportDTO;
import co.ccc.pmv.entity.ServiceTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ServiceTicketRepository extends JpaRepository<ServiceTicket, Long> {

    @Query("SELECT s FROM ServiceTicket s WHERE s.lastServiceDate BETWEEN :startDate AND :endDate")
    List<ServiceTicket> findAllInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT new co.ccc.pmv.dto.MonthlyServiceReportDTO(v.plateNo AS plateNo, " +
            "COUNT(s.id) AS numServicesLastMonth, " +
            "SUM(s.serviceCost) AS totalCostLastMonth, " +
            "MAX(s.lastServiceMileage) AS lastServiceMileage, " +
            "MAX(s.lastServiceDate) AS lastServiceDate) " +
            "FROM ServiceTicket s JOIN s.vehicle v " +
            "WHERE s.lastServiceDate >= :lastMonth " +
            "GROUP BY v.plateNo " +
            "ORDER BY COUNT(s.id) DESC")
    List<MonthlyServiceReportDTO> getMonthlyServiceCost(@Param("lastMonth") LocalDateTime lastMonth);

}
