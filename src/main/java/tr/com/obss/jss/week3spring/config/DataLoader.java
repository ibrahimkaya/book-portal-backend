package tr.com.obss.jss.week3spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import tr.com.obss.jss.week3spring.entity.Role;
import tr.com.obss.jss.week3spring.repo.RoleRepository;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        if(!roleRepository.existsByName("ROLE_USER")){
            roleRepository.save(userRole);
        }

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        if(!roleRepository.existsByName("ROLE_ADMIN")){
            roleRepository.save(adminRole);
        }


    }
}
