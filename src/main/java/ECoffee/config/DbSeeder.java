package ECoffee.config;

import ECoffee.entities.Role;
import ECoffee.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

@Component
public class DbSeeder implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;
    @Override
    public void run(String... args) throws Exception {

        if( roleRepository.count() == 0 ){
            List<Role> roles = asList("admin","user")
                    .stream()
                    .map(Role::new)
                    .collect(toList());

            roleRepository.saveAll(roles);
        }

    }
}
