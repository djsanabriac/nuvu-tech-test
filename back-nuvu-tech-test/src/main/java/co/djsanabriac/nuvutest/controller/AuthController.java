package co.djsanabriac.nuvutest.controller;

import co.djsanabriac.nuvutest.model.dto.GeneralResponse;
import co.djsanabriac.nuvutest.model.dto.LoginRequestDTO;
import co.djsanabriac.nuvutest.model.entity.User;
import co.djsanabriac.nuvutest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequestDTO){
        if( (loginRequestDTO.getEmail() == null && loginRequestDTO.getUsername() ==  null)
                || loginRequestDTO.getPassword() == null){
            return ResponseEntity.badRequest()
                    .body(new GeneralResponse(false, "null_parameters"));
        }

        User user = userRepository.findByUsernameOrEmail(loginRequestDTO.getUsername(), loginRequestDTO.getEmail());

        if ( user == null ){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new GeneralResponse(false, "incorrect_user_or_password"));
        }

        Boolean passwordMatches = new BCryptPasswordEncoder().matches(loginRequestDTO.getPassword(), user.getPassword());

        if ( !passwordMatches ){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new GeneralResponse(false, "incorrect_user_or_password"));
        }

        //String token = getJWTToken(user);
        Map<String, Object> toReturn = new HashMap<>();
        toReturn.put("user_id", user.getUser_id());
        //TODO Implementar token de autorizacion
        //toReturn.setToken(token);
        return ResponseEntity.ok(new GeneralResponse(true, "login_success", toReturn).toMap());
    }
}
