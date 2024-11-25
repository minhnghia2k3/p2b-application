package p2b.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Set;

@Entity(name = "permission")
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private PermissionCode code;

    private boolean isDefault;

    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<UserEntity> users;

    public PermissionEntity() {
    }

    public PermissionEntity(PermissionCode code, boolean isDefault) {
        this.code = code;
        this.isDefault = isDefault;
    }

    public int getId() {
        return id;
    }

    public PermissionCode getCode() {
        return code;
    }

    public void setCode(PermissionCode code) {
        this.code = code;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
}
