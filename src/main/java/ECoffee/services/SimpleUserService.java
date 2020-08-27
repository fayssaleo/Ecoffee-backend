package ECoffee.services;

import ECoffee.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service("simpleUserService")
public class SimpleUserService extends UserService {
    public SimpleUserService(UserRepository userRepository) {
        super(userRepository);
    }
}
