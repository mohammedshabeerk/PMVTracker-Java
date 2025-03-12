package co.ccc.pmv.service;

import co.ccc.pmv.common.ApplicationResponse;
import co.ccc.pmv.dto.MonthlyServiceReportDTO;
import co.ccc.pmv.entity.ServiceTicket;
import co.ccc.pmv.exception.ResourceNotFoundException;
import co.ccc.pmv.repository.ServiceTicketRepository;
import co.ccc.pmv.repository.UserRepository;
import co.ccc.pmv.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ServiceTicketService {

    private final ServiceTicketRepository serviceTicketRepository;

    public ServiceTicketService(ServiceTicketRepository serviceTicketRepository){
        this.serviceTicketRepository = serviceTicketRepository;
    }

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    public ApplicationResponse<List<ServiceTicket>> getAllServiceTickets() {
        List<ServiceTicket> serviceTickets = serviceTicketRepository.findAll();
        return new ApplicationResponse<>(200,"Data Retrieved Successfully",serviceTickets);
    }

    public ApplicationResponse<ServiceTicket> getServiceTicket(Long id) {
        ServiceTicket serviceTicket = this.findById(id);
        return new ApplicationResponse<>(200,"Data Retrieved Successfully",serviceTicket);
    }

    public ApplicationResponse<ServiceTicket> createServiceTicket(ServiceTicket serviceTicket) {
        ServiceTicket responseServiceTicket = serviceTicketRepository.save(serviceTicket);
        return new ApplicationResponse<>(200,"Created Successfully",responseServiceTicket);
    }

    public ApplicationResponse<ServiceTicket> updateServiceTicket(Long id, ServiceTicket serviceTicketDetails) {
        ServiceTicket serviceTicket = this.findById(id);

        serviceTicket.setLastServiceMileage(serviceTicketDetails.getLastServiceMileage());
        serviceTicket.setLastServiceDate(serviceTicketDetails.getLastServiceDate());
        serviceTicket.setServiceDescription(serviceTicketDetails.getServiceDescription());
        serviceTicket.setServiceCost(serviceTicketDetails.getServiceCost());
        serviceTicket.setUpcomingServiceDate(serviceTicketDetails.getUpcomingServiceDate());
        serviceTicket.setServiceEngineerName(serviceTicketDetails.getServiceEngineerName());
        serviceTicket.setVehicle(serviceTicketDetails.getVehicle());

        ServiceTicket responseServiceTicket = serviceTicketRepository.save(serviceTicket);
        return new ApplicationResponse<>(200,"Updated Successfully",responseServiceTicket);
    }

    public ApplicationResponse<List<MonthlyServiceReportDTO>> getMonthlyReport() {
        LocalDateTime lastMonth = LocalDateTime.now().minus(1, ChronoUnit.MONTHS);
        List<MonthlyServiceReportDTO> monthlyServiceReport = serviceTicketRepository.getMonthlyServiceCost(lastMonth);
        return new ApplicationResponse<>(200,"Created Successfully",monthlyServiceReport);
    }

    public void deleteServiceTicket(Long id) {
        ServiceTicket serviceTicket = this.findById(id);
        serviceTicketRepository.delete(serviceTicket);
    }

    public ServiceTicket findById(Long id){
        return serviceTicketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service ticket not found with id: " + id));
    }
}
