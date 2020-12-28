package co.djsanabriac.nuvutest.model.dto;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateUserRequestDTO {

    @Getter @Setter private String name;
    @Getter @Setter private String last_name;
    @Getter @Setter private Integer id_type;
    @Getter @Setter private String id_number;
    @Getter @Setter private String email;
    @Getter @Setter private String phone_number;

    public Boolean isComplete(){
        if( StringUtils.isBlank(name)
                || StringUtils.isBlank(last_name)
                || id_type == null || id_type < 1
                || StringUtils.isBlank(id_number)
                || StringUtils.isBlank(email)
                || StringUtils.isBlank(phone_number)
        ){
            return false;
        }

        return true;
    }

}
