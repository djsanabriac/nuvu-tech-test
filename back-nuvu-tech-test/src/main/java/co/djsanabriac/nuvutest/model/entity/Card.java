package co.djsanabriac.nuvutest.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter @Setter private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id")
    @NotNull
    @Getter @Setter private User user;

    @Column
    @NotNull
    @Getter @Setter private String card_number;

    @Column
    @NotNull
    @Getter @Setter private Date expiration_date;

    @Column
    @NotNull
    @Getter @Setter private Integer cvv;

    @Column
    @NotNull
    @Getter @Setter private String card_holder_name;


    @ManyToOne
    @JoinColumn(name="payment_network_id")
    @Getter @Setter private PaymentNetwork payment_network;


    @Column(length = 1)
    @NotNull
    @Getter @Setter private String state;

}
