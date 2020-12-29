package co.djsanabriac.nuvutest.controller;

import co.djsanabriac.nuvutest.model.dto.CreateCardRequestDTO;
import co.djsanabriac.nuvutest.model.dto.GeneralResponse;
import co.djsanabriac.nuvutest.model.entity.Card;
import co.djsanabriac.nuvutest.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @GetMapping(path = {"", "/{card_id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCards(@PathVariable(name = "card_id", required = false) Integer cardId){

        if( cardId != null ){

            Card card = null;
            Optional<Card> result = cardRepository.findById(cardId);

            if( !result.isPresent() ){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GeneralResponse<>(result.isPresent(),
                        "card_not_found"));
            }

            card = result.get();

            return ResponseEntity.ok(new GeneralResponse<>(result.isPresent(),
                    "success", card).toMap());

        }

        List<Card> toReturn = new ArrayList<>();

        toReturn.addAll((Collection<? extends Card>) cardRepository.findAll());

        return ResponseEntity.ok(new GeneralResponse(true,
                "success", toReturn).toMap());
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCard(@RequestBody(required = true) CreateCardRequestDTO newCard){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Not implemented");
    }

    @PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCard(@RequestBody(required = true) CreateCardRequestDTO newCard){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Not implemented");
    }

    @DeleteMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteCard(@RequestBody(required = true) CreateCardRequestDTO newCard){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Not implemented");
    }

}
