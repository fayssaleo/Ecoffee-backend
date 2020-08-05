package ECoffee.controllers;

import ECoffee.entities.User;
import ECoffee.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*",  allowedHeaders = "true", allowCredentials = "true", methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class LoginController {
    @Autowired
    private AuthenticationManager authManager;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<User> login(@RequestBody User user) throws Exception {

        try {

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),
                    user.getPassword());

            Authentication authentication = this.authManager.authenticate(authenticationToken);

            User foundedUser = (User) authentication.getPrincipal();

            if (foundedUser == null) {
                throw new ServiceException(HttpStatus.UNAUTHORIZED, "Nom d'utlisateur ou mot de passe érroné");
            } else {
                if (!foundedUser.isEnabled()) {
                    throw new ServiceException(HttpStatus.BAD_REQUEST, "Votre compte n\'est pas encore active");
                } else {
                    return new ResponseEntity<>(foundedUser, HttpStatus.OK);
                }
            }
        } catch (DisabledException ex) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Votre compte n\'est pas encore active");
        } catch (LockedException ex) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Votre compte est verouillé");
        } catch (BadCredentialsException ex) {
            throw new ServiceException(HttpStatus.UNAUTHORIZED, "Nom d'utlisateur ou mot de passe érroné");
        } catch (InternalAuthenticationServiceException ex) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Une erruer est survenue");
        }

    }
}