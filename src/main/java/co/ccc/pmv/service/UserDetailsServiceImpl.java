package co.ccc.pmv.service;

import co.ccc.pmv.common.ApplicationResponse;
import co.ccc.pmv.entity.User;
import co.ccc.pmv.enums.Role;
import co.ccc.pmv.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl {

    private final UserRepository userRepository;

    public User findByUsername(String username) {

        Optional<User> user = userRepository.findByUsername(username);
        return user.get();
    }

    public ApplicationResponse<List<User>> allUsers() {

        List<User> users = userRepository.findAll();
        return new ApplicationResponse<>(200,"Data Retrieved Successfully",users);
    }

    public User getByRole(Role role){

        Optional<User> user = userRepository.findByRole(role);
        return user.get();
    }
}
