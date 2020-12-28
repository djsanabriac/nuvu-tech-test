package co.djsanabriac.nuvutest.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter private Integer id;

    @Column(unique = true)
    @NotEmpty
    @NotNull
    @Getter @Setter private String roleName;
    @Column(unique = true)
    @NotEmpty
    @NotNull
    @Getter @Setter private String description;

    @ManyToMany(mappedBy = "roles")
    @Getter @Setter Set<User> users;

}
