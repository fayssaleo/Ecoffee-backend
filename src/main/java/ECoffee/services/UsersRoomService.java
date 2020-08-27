package ECoffee.services;

import ECoffee.entities.UsersRoom;
import ECoffee.repositories.UsersRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UsersRoomService")
public class UsersRoomService {
    @Autowired
    private UsersRoomRepository repo;

    public List<UsersRoom> listAll() {
        return repo.findAll();
    }

    public void save(UsersRoom ur) {
        repo.save(ur);
    }

    public UsersRoom get(Integer id) {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
