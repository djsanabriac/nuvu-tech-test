package co.djsanabriac.nuvutest.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "role_id")
    @Getter @Setter private Integer role_id;

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

    public Role(){};

    public Role(Integer role_id, String roleName, String description){
        this.role_id = role_id;
        this.roleName = roleName;
        this.description = description;
    };
}
