package andrew.restaurant_k_p_o4.model;

import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    private Integer id;

    @Size(max = 255)
    private String username;

    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String passwordHash;

    @Size(max = 255)
    private String role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
