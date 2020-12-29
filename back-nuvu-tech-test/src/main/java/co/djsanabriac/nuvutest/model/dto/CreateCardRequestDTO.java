package co.djsanabriac.nuvutest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class CreateCardRequestDTO {

    @Getter @Setter private Integer id;
    @Getter @Setter private Integer userId;
    @Getter @Setter private String cardNumber;
    @Getter @Setter private Date expirationDate;
    @Getter @Setter private Integer cvv;
    @Getter @Setter private String cardHolderName;
    @Getter @Setter private Integer paymentNetworkId;
    @Getter @Setter private String state;

    public Boolean isComplete(){

        if( userId == null || userId < 1
                || StringUtils.isBlank(cardNumber)
                || expirationDate == null
                || cvv == null
                || StringUtils.isBlank(cardHolderName)
                || paymentNetworkId == null || paymentNetworkId < 1
                || "A".equals(state) || "I".equals(state)
            ){
            return false;
        }

        return true;

    }

}
