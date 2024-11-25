package p2b.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterUserDto {
    @NotNull
    @NotEmpty
    private String username;

    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 8, max = 255)
    private String password;

    @Transient
    @NotNull
    @NotEmpty
    @JsonProperty("match_password")
    @Size(min = 8, max = 255)
    private String matchPassword;

    public @NotNull @NotEmpty String getUsername() {
        return username;
    }

    public void setUsername(@NotNull @NotEmpty String username) {
        this.username = username;
    }

    public @NotEmpty @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty @Email String email) {
        this.email = email;
    }

    public @NotNull @NotEmpty @Size(min = 8, max = 255) String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @NotEmpty @Size(min = 8, max = 255) String password) {
        this.password = password;
    }

    public String getMatchPassword() {
        return matchPassword;
    }

    public void setMatchPassword(String matchPassword) {
        this.matchPassword = matchPassword;
    }
}

