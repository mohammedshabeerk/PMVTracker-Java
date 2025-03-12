package co.ccc.pmv.controller;

import co.ccc.pmv.common.ApplicationResponse;
import co.ccc.pmv.dto.MonthlyServiceReportDTO;
import co.ccc.pmv.entity.ServiceTicket;
import co.ccc.pmv.service.ServiceTicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/service-tickets")
public class ServiceTicketController {

    private final ServiceTicketService serviceTicketService;

    @Autowired
    public ServiceTicketController(ServiceTicketService serviceTicketService){
        this.serviceTicketService = serviceTicketService;
    }

    @GetMapping
    public ResponseEntity<ApplicationResponse<List<ServiceTicket>>> getAllServiceTickets() {
        ApplicationResponse<List<ServiceTicket>> apiResponse = serviceTicketService.getAllServiceTickets();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse<ServiceTicket>> getServiceTicketById(@PathVariable Long id) {
        ApplicationResponse<ServiceTicket> apiResponse = serviceTicketService.getServiceTicket(id);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    @PreAuthorize("hasRole('SERVICE_ENGINEER')")
    public ResponseEntity<ApplicationResponse<ServiceTicket>> createServiceTicket(@RequestBody ServiceTicket serviceTicket) {
        ApplicationResponse<ServiceTicket> apiResponse = serviceTicketService.createServiceTicket(serviceTicket);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SERVICE_ENGINEER')")
    public ResponseEntity<ApplicationResponse<ServiceTicket>> updateServiceTicket(
            @PathVariable Long id,
            @RequestBody ServiceTicket serviceTicket) {

        ApplicationResponse<ServiceTicket> apiResponse = serviceTicketService.updateServiceTicket(id, serviceTicket);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/monthlyReport")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ApplicationResponse<List<MonthlyServiceReportDTO>>> getMonthlyReport() {
        ApplicationResponse<List<MonthlyServiceReportDTO>> apiResponse = serviceTicketService.getMonthlyReport();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SERVICE_ENGINEER')")
    public ResponseEntity<ApplicationResponse<Void>> deleteServiceTicket(@PathVariable Long id) {
        serviceTicketService.deleteServiceTicket(id);
        return ResponseEntity.ok().build();
    }
}
