package p2b.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

public class UserCreateDto {
    @NotEmpty
    @Size(min = 2, max = 255)
    private String username;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 8, max = 255)
    private String password;

    @URL
    private String avatar;

    public @NotEmpty @Size(min = 2, max = 255) String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty @Size(min = 2, max = 255) String username) {
        this.username = username;
    }

    public @NotEmpty @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty @Email String email) {
        this.email = email;
    }

    public @NotEmpty @Size(min = 8, max = 255) String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty @Size(min = 8, max = 255) String password) {
        this.password = password;
    }

    public @URL String getAvatar() {
        return avatar;
    }

    public void setAvatar(@URL String avatar) {
        this.avatar = avatar;
    }
}

