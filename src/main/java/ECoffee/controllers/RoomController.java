package ECoffee.controllers;

import ECoffee.entities.Room;
import ECoffee.entities.User;
import ECoffee.services.RoomService;
import ECoffee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "*",  allowedHeaders = "true", allowCredentials = "true", methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class RoomController {
    @Autowired
    private RoomService service;

    //RESTful API that returns a list of rooms
    @CrossOrigin("*")
    @GetMapping("/room")
    public List<Room> list() {
        return service.listAll();
    }

    //RESTful API that allows to get information about a specific room based on ID
    @CrossOrigin("*")
    @GetMapping("/room/{id}")
    public ResponseEntity<Room> get(@PathVariable Integer id) {
        try {
            Room room = service.get(id);
            return new ResponseEntity<Room>(room, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
        }
    }

    //RESTful API that allows the users to create a room
    @CrossOrigin("*")
    @PostMapping("/create-room")
    public void add(@RequestBody Room room) {
        service.save(room);
    }

    // RESTful API for update room
    @CrossOrigin("*")
    @PutMapping("/room/{id}")
    public ResponseEntity<?> update(@RequestBody Room room, @PathVariable Integer id) {
        try {
            Room existRoom = service.get(id);
            service.save(room);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //RESTful API for the delete operation
    @CrossOrigin("*")
    @DeleteMapping("/room/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
