package ECoffee.controllers;

import ECoffee.entities.Room;
import ECoffee.webSocketData.SubData;
import ECoffee.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.handler.annotation.MessageMapping;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "*",  allowedHeaders = "true", allowCredentials = "true", methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class RoomController {

   // String roomName =null;

    @Autowired
    private RoomService service;

    private SimpMessagingTemplate template;

    @Autowired
    public RoomController(SimpMessagingTemplate template) {
        this.template = template;
    }

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

    @MessageMapping("/subscribe")
    public void subs(SubData subdata) throws Exception {
        System.err.println("----------------------subs----roomName :"+subdata.getRoom()+"----------------------------");
        System.err.println(subdata);
        System.err.println("------------------------------------------------------");
      //  subdata.setTo();
        this.template.convertAndSend("/topic/new_user/"+ subdata.getRoom(), subdata);


    }

    @MessageMapping("/newUserStart/{roomName}")
    public void newUserStart(@DestinationVariable String roomName, SubData subdata) throws Exception {
     //   System.err.println(subdata.getRoom());
        System.err.println("----------------------newUserStart--roomName :"+roomName+"------------------------------");
        System.err.println(subdata);
        System.err.println("------------------------------------------------------");
        this.template.convertAndSend("/topic/newUserStart/"+ subdata.getTo(), subdata);

    }

    @MessageMapping("/sdp/{roomName}")
    public void sdp(@DestinationVariable String roomName, SubData subdata) throws Exception {
       // System.err.println(subdata.getRoom());
        System.err.println("----------------------sdp----roomName :"+roomName+"----------------------------");
        System.err.println(subdata);
        System.err.println("------------------------------------------------------");
        this.template.convertAndSend("/topic/sdp/"+ subdata.getTo(), subdata);

    }

    @MessageMapping("/ice_candidates/{roomName}")
    public void iceCandidate(@DestinationVariable String roomName, SubData subdata) throws Exception {
      //  System.err.println(subdata.getRoom());
        System.err.println("----------------------iceCandidate-----roomName :"+roomName+"---------------------------");
        System.err.println(subdata);
        System.err.println("------------------------------------------------------");
        this.template.convertAndSend("/topic/ice_candidates/"+ subdata.getTo(), subdata);

    }

    @MessageMapping("/chat/{roomName}")
    public void chat(@DestinationVariable String roomName, SubData subdata) throws Exception {
       // System.err.println(subdata.getRoom());
        System.err.println("----------------------chat------roomName :"+roomName+"--------------------------");
        System.err.println(subdata);
        System.err.println("------------------------------------------------------");
        this.template.convertAndSend("/topic/chat/"+ roomName, subdata);

    }




}
