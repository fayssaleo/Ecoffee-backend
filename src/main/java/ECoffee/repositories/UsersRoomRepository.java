package ECoffee.repositories;

import ECoffee.entities.UsersRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRoomRepository extends JpaRepository<UsersRoom,Integer> {
}
