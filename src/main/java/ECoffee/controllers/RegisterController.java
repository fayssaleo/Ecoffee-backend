package ECoffee.controllers;

import ECoffee.entities.Role;
import ECoffee.entities.User;
import ECoffee.repositories.RoleRepository;
import ECoffee.services.RoleService;
import ECoffee.services.UserService;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*",  allowedHeaders = "true", allowCredentials = "true", methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class RegisterController {
    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private RoleService roleService;
    @Autowired
    public RegisterController(PasswordEncoder passwordEncoder, UserService userService, RoleService roleService) {

        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService=roleService;
    }
    // Return registration form template

    // Process form input data
    @CrossOrigin("*")
    @RequestMapping(value={"/register","/register/{roleId}"}, method = RequestMethod.POST)
    public ResponseEntity<String> processRegistrationForm( @RequestBody User user , HttpServletRequest request, @PathVariable(required = false) Integer roleId) {
        System.err.println(user);
        Role role;

        if(roleId == null || roleId==2)
            role= roleService.get(2);
        else
            role=roleService.get(1);
        user.setRole(role);
        try{

            // Lookup user in database by e-mail
            User userExists = userService.findByUsername(user.getUsername());



            if (userExists != null) {
                return new ResponseEntity<>("Un autre utlisateur est déja enregistré avec le même email", HttpStatus.BAD_REQUEST);
            } else {

                // Generate random 36-character string token for confirmation link
                user.setConfirmationToken(UUID.randomUUID().toString());
                user.setAccessToken(UUID.randomUUID().toString());
                user.setUdateAncCreatedAt();

                userService.save(user);

                return new ResponseEntity<>("vous pouvez se connecter !", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Process confirmation link

    // Process confirmation link
    @CrossOrigin("*")
    @RequestMapping(value="/confirm", method = RequestMethod.POST)
    public ModelAndView processConfirmationForm(ModelAndView modelAndView, BindingResult bindingResult, @RequestParam Map requestParams, RedirectAttributes redir) {

        modelAndView.setViewName("confirm");

        Zxcvbn passwordCheck = new Zxcvbn();

        Strength strength = passwordCheck.measure((CharSequence) requestParams.get("password"));

        if (strength.getScore() < 3) {
            bindingResult.reject("password");

            redir.addFlashAttribute("errorMessage", "Your password is too weak.  Choose a stronger one.");

            modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
            System.out.println(requestParams.get("token"));
            return modelAndView;
        }

        // Find the user associated with the reset token
        User user = userService.findByConfirmationToken((String) requestParams.get("token"));

        // Set new password
        user.setPassword(passwordEncoder.encode((CharSequence) requestParams.get("password")));

        // Set user to enabled
        user.setEnabled(true);

        // Save user
        userService.save(user);

        modelAndView.addObject("successMessage", "Your password has been set!");
        return modelAndView;
    }


}
