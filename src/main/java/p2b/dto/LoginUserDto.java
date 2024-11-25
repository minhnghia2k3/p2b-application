package p2b.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoginUserDto {
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 8, max = 255)
    private String password;
}
