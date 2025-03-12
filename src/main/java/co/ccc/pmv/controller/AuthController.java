package co.ccc.pmv.controller;

import co.ccc.pmv.common.ApplicationResponse;
import co.ccc.pmv.entity.User;
import co.ccc.pmv.enums.Role;
import co.ccc.pmv.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    public AuthController(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginRequest) {

        try {

            String username = loginRequest.get("username");
            String password = loginRequest.get("password");
            log.debug("username {}:",username);
            log.debug("password {}:",password);

            Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
            User user = userDetailsServiceImpl.findByUsername(username);
            log.debug("user {}:",user);
            if (user != null && password.equals(user.getPassword())) {
                Role role = user.getRole();
                String token = Jwts.builder()
                        .setSubject(user.getUsername())
                        .claim("role", role.toString())
                        .signWith(key)
                        .compact();

                Map<String, Object> response = new HashMap<>();
                response.put("id", user.getId());
                response.put("name", user.getName());
                response.put("email", user.getEmail());
                response.put("username", user.getUsername());
                response.put("role", role);
                response.put("token", token);

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<ApplicationResponse<List<User>>> getCurrentUser(Authentication authentication) {
        ApplicationResponse<List<User>> users = userDetailsServiceImpl.allUsers();
        return ResponseEntity.ok(users);
    }
}
