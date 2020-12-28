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
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Returns users with no username & password if {user_id} is null. Otherwise, returns the user of the user_id
     *
     * @param userId
     * @return
     */
    @GetMapping(path = {"", "/{user_id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getClients(@PathVariable(name="user_id", required = false) Integer userId){

        if( userId != null ){

            User user = null;
            Optional<User> result = userRepository.findById(userId);

            if( result.isPresent() ){
                user = result.get();
            }


            return ResponseEntity.ok(new GeneralResponse<>(result.isPresent(),
                    "success", user).toMap());
        }

        List<User> toReturn = new ArrayList<>();

        toReturn.addAll(userRepository.findAllByUsernameOrEmailIsNull(null));

        return ResponseEntity.ok(new GeneralResponse(true,
                "success", toReturn).toMap());
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createClient(){
        return ResponseEntity.ok("dummy");
    }

    @PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateClient(){
        return ResponseEntity.ok("dummy");
    }

    @DeleteMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteClient(){
        return ResponseEntity.ok("dummy");
    }

}
