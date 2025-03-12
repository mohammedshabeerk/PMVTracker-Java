package co.ccc.pmv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyServiceReportDTO {

    private String plateNo;
    private Long numServicesLastMonth;
    private BigDecimal totalCostLastMonth;
    private Integer lastServiceMileage;
    private Timestamp lastServiceDate;

//    public MonthlyServiceReportDTO(String plateNo, Long numServicesLastMonth, BigDecimal totalCostLastMonth, Integer lastServiceMileage, Timestamp lastServiceDate){
//        this.plateNo = plateNo;
//        this.numServicesLastMonth = numServicesLastMonth;
//        this.totalCostLastMonth = totalCostLastMonth;
//        this.lastServiceDate = lastServiceDate;
//        this.lastServiceMileage = lastServiceMileage;
//    }
}
