package ECoffee.repositories;


import ECoffee.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * User repository for CRUD operations.
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findById(Integer id);
    Optional<User>findByUsername(String username);
    User findByConfirmationToken(String confirmationToken);
}