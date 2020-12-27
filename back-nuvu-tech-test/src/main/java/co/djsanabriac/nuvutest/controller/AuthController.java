package co.djsanabriac.nuvutest.controller;

import antlr.ASTNULLType;
import co.djsanabriac.nuvutest.dto.User;
import co.djsanabriac.nuvutest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUsers(){

        List<User> result = new ArrayList<>();

        Iterable users = userRepository.findAll();
        users.forEach(o -> result.add((User) o));

        return ResponseEntity.ok(result);
    }
}
