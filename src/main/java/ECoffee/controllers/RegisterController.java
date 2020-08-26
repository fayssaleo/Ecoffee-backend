package ECoffee.controllers;

import ECoffee.entities.Role;
import ECoffee.entities.User;
import ECoffee.services.EmailService;
import ECoffee.services.RoleService;
import ECoffee.services.UserService;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;
    private EmailService emailService;
    private RoleService serviceRole;

    @Autowired
    public RegisterController(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService, EmailService emailService, RoleService serviceRole) {

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
        this.emailService = emailService;
        this.serviceRole = serviceRole;
    }
    @CrossOrigin("*")
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String greet(){
        return "welcome sanaa";
    }


    // Return registration form template
    @CrossOrigin("*")
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(@RequestBody ModelAndView modelAndView, User user){
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    // Process form input data
    @CrossOrigin("*")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public  ResponseEntity<String> processRegistrationForm(
            @RequestBody User user, HttpServletRequest request) {
        try{
            // Lookup user in database by e-mail
            User userExists = userService.findByUsername(user.getUsername());

            System.out.println(userExists);

            if (userExists != null) {
                return new ResponseEntity<>("Un autre utlisateur est déja enregistré avec le même email", HttpStatus.BAD_REQUEST);
            } else {
                user.setUsername(user.getUsername());
                user.setEmail(user.getEmail());
                user.setPassword(user.getPassword());
                Set<Role> roles = new HashSet<>();
                for(Role role : serviceRole.listAll()){
                    if(StringUtils.equals(user.getRoles(), user.getRoles())){
                        roles.add(role);
                        break;
                    }
                }
                user.setRoles(roles);
                // Disable user until they click on confirmation link in email
                user.setEnabled(true);

                // Generate random 36-character string token for confirmation link
                user.setConfirmationToken(UUID.randomUUID().toString());

                userService.save(user);

                String appUrl = request.getScheme() + "://" + request.getServerName();

                SimpleMailMessage registrationEmail = new SimpleMailMessage();
                registrationEmail.setTo(user.getEmail());
                registrationEmail.setSubject("Registration Confirmation");
                registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
                        + appUrl + "/confirm?token=" + user.getConfirmationToken());
                registrationEmail.setFrom("noreply@domain.com");

                //emailService.sendEmail(registrationEmail);

                return new ResponseEntity<>("Un email de confirmation a été envoyé à : " + user.getEmail(), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Process confirmation link
    @CrossOrigin("*")
    @RequestMapping(value="/confirm", method = RequestMethod.GET)
    public ModelAndView showConfirmationPage(ModelAndView modelAndView, @RequestParam("token") String token) {

        User user = userService.findByConfirmationToken(token);

        if (user == null) { // No token found in DB
            modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
        } else { // Token found
            modelAndView.addObject("confirmationToken", user.getConfirmationToken());
        }

        modelAndView.setViewName("confirm");
        return modelAndView;
    }

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
        user.setPassword(bCryptPasswordEncoder.encode((CharSequence) requestParams.get("password")));

        // Set user to enabled
        user.setEnabled(true);

        // Save user
        userService.save(user);

        modelAndView.addObject("successMessage", "Your password has been set!");
        return modelAndView;
    }
}
