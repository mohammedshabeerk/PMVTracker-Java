package co.ccc.pmv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {

    private Long id;
    private String plateNo;
    private String carModel;
    private Integer manufacturingYear;
    private LocalDate upcomingServiceDate;
    private Long driverId;
    private String driverName;
    private String driverContactNumber;
}
