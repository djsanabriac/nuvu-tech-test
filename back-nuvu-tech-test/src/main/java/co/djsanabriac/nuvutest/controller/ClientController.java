package co.djsanabriac.nuvutest.controller;

import co.djsanabriac.nuvutest.model.dto.CreateUserRequestDTO;
import co.djsanabriac.nuvutest.model.dto.GeneralResponse;
import co.djsanabriac.nuvutest.model.entity.IdType;
import co.djsanabriac.nuvutest.model.entity.User;
import co.djsanabriac.nuvutest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
                user.setPassword(null);
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
    public ResponseEntity createClient(@RequestBody(required = true) CreateUserRequestDTO newUser ){

        if( !newUser.isComplete() ){
            return ResponseEntity.badRequest().body(new GeneralResponse(false, "missing_arguments").toMap());
        }

        User user = new User();
        user.setName(newUser.getName());
        user.setLast_name(newUser.getLast_name());
        user.setId_type(new IdType(newUser.getId_type(), null, null));
        user.setId_number(newUser.getId_number());
        user.setEmail(newUser.getEmail());
        user.setPhone_number(newUser.getPhone_number());

        try {
            User u = userRepository.save(user);
            return ResponseEntity.ok(new GeneralResponse(true, "client_created", u).toMap());
        }catch (DataIntegrityViolationException de){
            return ResponseEntity.badRequest().body(new GeneralResponse(false, "duplicate_idnumber_or_email", de.getCause().getCause().getMessage()).toMap());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new GeneralResponse(false, "error_saving", e.getMessage()).toMap());
        }

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
