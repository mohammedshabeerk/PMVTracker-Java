package co.ccc.pmv.service;

import co.ccc.pmv.common.ApplicationResponse;
import co.ccc.pmv.entity.User;
import co.ccc.pmv.entity.Vehicle;
import co.ccc.pmv.enums.Role;
import co.ccc.pmv.exception.ResourceNotFoundException;
import co.ccc.pmv.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final EmailService emailService;
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository, EmailService emailService, UserDetailsServiceImpl userDetailsService){

        this.vehicleRepository = vehicleRepository;
        this.emailService = emailService;
        this.userDetailsService = userDetailsService;
    }

    public ApplicationResponse<List<Vehicle>> getAllVehicles() {

        List<Vehicle> vehicles = vehicleRepository.findAll();
        return new ApplicationResponse<>(200,"Data Retrieved Successfully",vehicles);
    }

    public ApplicationResponse<Vehicle> createVehicle(Vehicle vehicle) {

        Vehicle vehicleResponse = vehicleRepository.save(vehicle);

        //send mail to service engineers.
        User user = userDetailsService.getByRole(Role.SERVICE_ENGINEER);
        String mailSubject = "New Vehicle For Service";
        String mailBody = "Dear "+ user.getName()+" New vehicle added for service. Please prepare service ticket...";
        emailService.sendNotification(user.getEmail(),mailSubject,mailBody);

        return new ApplicationResponse<>(200,"Created Successfully",vehicleResponse);
    }

    private Vehicle findById(Long id){
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));
    }

    public ApplicationResponse<Vehicle> getVehicle(Long id) {

        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));
        return new ApplicationResponse<>(200,"Data Retrieved Successfully",vehicle);
    }

    public ApplicationResponse<Vehicle> updateVehicle(Long id, Vehicle vehicleDetails) {

        Vehicle vehicle = this.findById(id);
        vehicle.setPlateNo(vehicleDetails.getPlateNo());
        vehicle.setVehicleModel(vehicleDetails.getVehicleModel());
        vehicle.setManufacturingYear(vehicleDetails.getManufacturingYear());
        vehicle.setUpcomingServiceDate(vehicleDetails.getUpcomingServiceDate());
        vehicle.setDriverName(vehicleDetails.getDriverName());

        Vehicle vehicleResponse = vehicleRepository.save(vehicle);
        return new ApplicationResponse<>(200,"Updated Successfully",vehicleResponse);
    }

    public void deleteVehicle(Long id) {
        Vehicle vehicle = this.findById(id);
        vehicleRepository.delete(vehicle);
    }
}
