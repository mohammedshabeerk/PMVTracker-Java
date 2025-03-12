package co.ccc.pmv.controller;

import co.ccc.pmv.common.ApplicationResponse;
import co.ccc.pmv.entity.Vehicle;
import co.ccc.pmv.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }
    
    @GetMapping
    public ResponseEntity<ApplicationResponse<List<Vehicle>>> getAllVehicles() {
        ApplicationResponse<List<Vehicle>> apiResponse = vehicleService.getAllVehicles();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse<Vehicle>> getVehicleById(@PathVariable Long id) {
        ApplicationResponse<Vehicle> vehicle = vehicleService.getVehicle(id);
        return ResponseEntity.ok(vehicle);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApplicationResponse<Vehicle>> createVehicle(@RequestBody Vehicle vehicle) {
        ApplicationResponse<Vehicle> apiResponse = vehicleService.createVehicle(vehicle);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApplicationResponse<Vehicle>> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        ApplicationResponse<Vehicle> updatedVehicle = vehicleService.updateVehicle(id, vehicle);
        return ResponseEntity.ok(updatedVehicle);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApplicationResponse<Void>> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok().build();
    }
}
