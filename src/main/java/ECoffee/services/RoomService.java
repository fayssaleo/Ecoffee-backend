package ECoffee.services;

import ECoffee.entities.Room;
import ECoffee.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RoomService")
public class RoomService {
    @Autowired
    private RoomRepository repo;

    public List<Room> listAll() {
        return repo.findAll();
    }

    public void save(Room room) {
        repo.save(room);
    }

    public Room get(Integer id) {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
