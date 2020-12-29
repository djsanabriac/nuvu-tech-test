package co.djsanabriac.nuvutest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class CreateCardRequestDTO {

    @Getter @Setter private Integer id;
    @Getter @Setter private Integer user_id;
    @Getter @Setter private String card_number;
    @Getter @Setter private Date expiration_date;
    @Getter @Setter private Integer cvv;
    @Getter @Setter private String card_holder_name;
    @Getter @Setter private Integer payment_network_id;
    @Getter @Setter private String state;

}
