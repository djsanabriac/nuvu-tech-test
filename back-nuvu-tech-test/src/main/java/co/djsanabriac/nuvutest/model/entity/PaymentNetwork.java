package co.djsanabriac.nuvutest.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PaymentNetwork {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "payment_network_id")
    @Getter @Setter private Integer payment_network_id;

    @Column
    @NotNull
    @Getter @Setter private String name;

}
