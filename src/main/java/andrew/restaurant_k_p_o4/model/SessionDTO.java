package andrew.restaurant_k_p_o4.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SessionDTO {

    private Integer id;

    @Size(max = 255)
    private String userId;

    @Size(max = 255)
    private String sessionToken;

    @Size(max = 255)
    private String expiresAt;

}
