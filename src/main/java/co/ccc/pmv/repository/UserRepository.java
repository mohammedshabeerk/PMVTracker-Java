package co.ccc.pmv.repository;

import co.ccc.pmv.entity.User;
import co.ccc.pmv.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByRole(Role role);
}
