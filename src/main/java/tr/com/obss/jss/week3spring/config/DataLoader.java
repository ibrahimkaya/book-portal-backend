package tr.com.obss.jss.week3spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import tr.com.obss.jss.week3spring.entity.Role;
import tr.com.obss.jss.week3spring.entity.User;
import tr.com.obss.jss.week3spring.repo.RoleRepository;
import tr.com.obss.jss.week3spring.repo.UserRepository;

import java.util.Arrays;
import java.util.Collections;

@Order(1)
@Component
public class DataLoader implements ApplicationRunner {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    @Autowired
    public DataLoader(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) {

        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        if (!roleRepository.existsByName("ROLE_USER")) {
            roleRepository.save(userRole);
        }

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        if (!roleRepository.existsByName("ROLE_ADMIN")) {
            roleRepository.save(adminRole);
        }

        User user = new User();
        user.setUsername("user@a.com");
        user.setPassword(new BCryptPasswordEncoder().encode("1"));
        user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER")));
        if (!userRepository.findByUsername("user@a.com").isPresent()) {
            userRepository.save(user);
        }

        User admin = new User();
        admin.setUsername("admin@a.com");
        admin.setPassword(new BCryptPasswordEncoder().encode("1"));
        admin.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_ADMIN")));
        if (!userRepository.findByUsername("admin@a.com").isPresent()) {
            userRepository.save(admin);
        }
    }
}
