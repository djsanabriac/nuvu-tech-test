package co.djsanabriac.nuvutest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "user_id")
    @Getter @Setter private Integer user_id;

    @Column
    @Getter @Setter private String name;

    @Column
    @Getter @Setter private String last_name;

    @ManyToOne
    @JoinColumn(name="type_id")
    @Getter @Setter private IdType id_type;

    @Column(unique = true)
    @Getter @Setter private String id_number;


    @Column(unique = true)
    @NotEmpty
    @NotNull
    @Getter @Setter private String email;

    @Column
    @NotEmpty
    @Getter @Setter private String phone_number;

    @Column(unique = true)
    @Getter @Setter private String username;
    @Getter @Setter private String password;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    //JWT token
    private String token;
    public void addRole(Role role){
        if( roles == null){
            roles = new HashSet<>();
        }
        roles.add(role);
    }
}
