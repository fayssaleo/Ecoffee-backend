package ECoffee.controllers;

import ECoffee.entities.Role;
import ECoffee.entities.User;
import ECoffee.repositories.RoleRepository;
import ECoffee.services.RoleService;
import ECoffee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*",  allowedHeaders = "true", allowCredentials = "true", methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService service;

    @Autowired
    private RoleService roleService;

    //RESTful API that returns a list of users
    @CrossOrigin("*")
    @GetMapping("/users")
    public List<User> list() {
        return service.listAll();
    }

    //RESTful API that allows to get information about a specific user based on ID
    @CrossOrigin("*")
    @GetMapping("/user/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        try {
            User user = service.get(id).orElseThrow(() -> new NoSuchElementException("User not found"));
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    //RESTful API that allows the users to create user
    @CrossOrigin("*")
    @PostMapping(value={"/create-user","/create-user/{roleId}"})
    public void add(@RequestBody User user, @PathVariable(required = false) Integer roleId) {
        Role role;
        if(roleId == null || roleId==2)
             role= roleService.get(2);
        else
             role=roleService.get(1);
        user.setRole(role);
        user.setPassword(UUID.randomUUID().toString());
        service.save(user);

    }

    // RESTful API for update user
    @CrossOrigin("*")
    @PostMapping(value = {"/user/{id}","/user/{id}/{roleId}"})
    public ResponseEntity<?> update(

            @RequestBody User user, @PathVariable Integer id,@PathVariable(required = false) Integer roleId) {

        try {
            System.err.println(user.getBirthday());
            User existUser =service.get(id).get();
            Role role;
            if(roleId == null || roleId==2)
                role= roleService.get(2);
            else
                role=roleService.get(1);
            existUser.setRole(role);
            existUser.setUsername(user.getUsername());
            existUser.setEmail(user.getEmail());
            existUser.setFirstname(user.getFirstname());
            existUser.setLastname(user.getLastname());
            existUser.setCountry(user.getCountry());
            existUser.setCity(user.getCity());
            existUser.setBirthday(user.getBirthday());

            service.update(existUser);
           // System.err.println(existUser);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //RESTful API for the delete operation
    @CrossOrigin("*")
    @PostMapping("/userd/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}
