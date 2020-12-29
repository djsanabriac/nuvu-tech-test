package co.djsanabriac.nuvutest.controller;

import co.djsanabriac.nuvutest.model.dto.CreateCardRequestDTO;
import co.djsanabriac.nuvutest.model.dto.GeneralResponse;
import co.djsanabriac.nuvutest.model.entity.Card;
import co.djsanabriac.nuvutest.model.entity.PaymentNetwork;
import co.djsanabriac.nuvutest.model.entity.User;
import co.djsanabriac.nuvutest.repository.CardRepository;
import co.djsanabriac.nuvutest.repository.PaymentNetworkRepository;
import co.djsanabriac.nuvutest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentNetworkRepository paymentNetworkRepository;

    @GetMapping(path = {""}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCards(@RequestParam(name = "card_id", required = false) Integer cardId,
                                   @RequestParam(name = "user_id", required = false) Integer userId){

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

        if( userId != null ){

            Card card = null;
            User u = new User();
            u.setId(userId);
            toReturn.addAll(cardRepository.findAllByUser(u));

            return ResponseEntity.ok(new GeneralResponse<>(true,
                    "success", toReturn).toMap());

        }

        toReturn.addAll((Collection<? extends Card>) cardRepository.findAll());

        return ResponseEntity.ok(new GeneralResponse<>(true,
                "success", toReturn).toMap());
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCard(@RequestBody(required = true) CreateCardRequestDTO newCard){

        if( !newCard.isComplete() ){
            return ResponseEntity.badRequest().body(new GeneralResponse(false, "missing_or_wrong__arguments").toMap());
        }

        Optional<User> u = userRepository.findById(newCard.getUserId());

        if( !u.isPresent() ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GeneralResponse<>(false,
                    "user_not_found"));
        }

        Optional<PaymentNetwork> pn = paymentNetworkRepository.findById(newCard.getPaymentNetworkId());

        if( !pn.isPresent() ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GeneralResponse<>(false,
                    "payment_network_not_found"));
        }

        Card toSave = new Card();
        toSave.setId(newCard.getId());
        toSave.setUser(u.get());
        toSave.setCard_number(newCard.getCardNumber());
        toSave.setExpiration_date(newCard.getExpirationDate());
        toSave.setCvv(newCard.getCvv());
        toSave.setCard_holder_name(newCard.getCardHolderName());
        toSave.setPayment_network(pn.get());
        toSave.setState(newCard.getState());

        try {
            Card c = cardRepository.save(toSave);
            return ResponseEntity.ok(new GeneralResponse(true, "card_created", c).toMap());
        }catch (DataIntegrityViolationException de){
            return ResponseEntity.badRequest().body(new GeneralResponse(false, "duplicate_idnumber_or_email", de.getCause().getCause().getMessage()).toMap());
        }catch (ConstraintViolationException ce){
            return ResponseEntity.badRequest().body(new GeneralResponse(false, "constraint_violation", ce.getCause().getCause().getMessage()).toMap());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new GeneralResponse(false, "error_saving", e.getMessage()).toMap());
        }

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
