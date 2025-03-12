package co.ccc.pmv.datainitializer;

import co.ccc.pmv.entity.User;
import co.ccc.pmv.enums.Role;
import co.ccc.pmv.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            userRepository.saveAll(List.of(
                    new User(null, "admin","Shabeer", "admin123","mail4shabeerklr@gmail.com",123456789, Role.ADMIN),
                    new User(null, "engineer", "Adil","engineer123", "mail4shabeerklr@gmail.com", 123456789, Role.SERVICE_ENGINEER),
                    new User(null, "driver", "Rasheed","driver123", "mail4shabeerklr@gmail.com", 123456789, Role.DRIVER),
                    new User(null, "manager", "Mohammed","manager123", "mail4shabeerklr@gmail.com", 123456789, Role.MANAGER)
            ));
        }
    }
}
