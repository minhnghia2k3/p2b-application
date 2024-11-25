package p2b.dto;

import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public class UserUpdateDto {
    private Long id;

    @Size(min = 2, max = 255)
    private String username;

    @URL
    private String avatar;

    public @Size(min = 2, max = 255) String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 2, max = 255) String username) {
        this.username = username;
    }

    public @URL String getAvatar() {
        return avatar;
    }

    public void setAvatar(@URL String avatar) {
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
