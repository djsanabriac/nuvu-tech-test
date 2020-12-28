package co.djsanabriac.nuvutest.controller;

import co.djsanabriac.nuvutest.model.dto.GeneralResponse;
import co.djsanabriac.nuvutest.model.entity.User;
import co.djsanabriac.nuvutest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getClients(){

        List<User> toReturn = new ArrayList<>();

        toReturn.addAll(userRepository.findAllByUsernameOrEmailIsNull(null));

        return ResponseEntity.ok(new GeneralResponse(true,
                "success", toReturn).toMap());
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createClient(){
        return ResponseEntity.ok("dummy");
    }

    @PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateClient(){
        return ResponseEntity.ok("dummy");
    }

    @DeleteMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteClient(){
        return ResponseEntity.ok("dummy");
    }

}
