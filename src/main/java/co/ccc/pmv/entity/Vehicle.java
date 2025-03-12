package co.ccc.pmv.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plate_no", nullable = false)
    private String plateNo;

    @Column(name = "vehicle_model", nullable = false)
    private String vehicleModel;

    @Column(name = "manufacturing_year", nullable = false)
    private Integer manufacturingYear;

    @Column(name = "upcoming_service_date", nullable = false)
    private LocalDate upcomingServiceDate;

    @Column(name = "driver_name", nullable = false)
    private String driverName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
}
