package co.ccc.pmv.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "service_tickets")
public class ServiceTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Column(name = "service_engineer_name", nullable = false)
    private String serviceEngineerName;

    @Column(name = "last_service_mileage", nullable = false)
    private Integer lastServiceMileage;

    @Column(name = "last_service_date", nullable = false)
    private Date lastServiceDate;

    @Column(name = "service_description", nullable = false)
    private String serviceDescription;

    @Column(name = "service_cost", nullable = false)
    private BigDecimal serviceCost;

    @Column(name = "upcoming_service_date", nullable = false)
    private LocalDate upcomingServiceDate;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate = LocalDateTime.now();
}
