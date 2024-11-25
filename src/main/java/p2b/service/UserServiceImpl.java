package p2b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import p2b.dto.RegisterUserDto;
import p2b.dto.UserCreateDto;
import p2b.dto.UserUpdateDto;
import p2b.entity.PermissionEntity;
import p2b.entity.UserEntity;
import p2b.exception.EmailExistsException;
import p2b.exception.UserNotFoundException;
import p2b.repository.PermissionRepository;
import p2b.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private PermissionRepository permissionRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, PermissionRepository permissionRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public UserEntity findById(long id) {
        var optional = userRepository.findByIdAndIsDisabledFalse(id);
        if (optional.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        return optional.get();
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity insert(UserCreateDto o) {
        // Verify email
        if (findByEmail(o.getEmail()) != null) {
            throw new EmailExistsException(o.getEmail());
        }

        // hash password
        String hashedPassword = passwordEncoder.encode(o.getPassword());

        UserEntity user = new UserEntity(o.getUsername(), o.getEmail(), hashedPassword, o.getAvatar());

        // create default permission
        return userRepository.save(user);
    }

    @Override
    public UserEntity registerUser(RegisterUserDto dto) {
        // Verify email
        if (findByEmail(dto.getEmail()) != null) {
            throw new EmailExistsException(dto.getEmail());
        }

        // hash password
        String hashedPassword = passwordEncoder.encode(dto.getPassword());

        UserEntity user = new UserEntity(dto.getUsername(), dto.getEmail(), hashedPassword);

        // add user default permissions
        Set<PermissionEntity> defaultPermissions = new HashSet<>(permissionRepository.findByIsDefault(true));
        user.setPermissions(defaultPermissions);

        // create default permission
        return userRepository.save(user);
    }


    @Override
    public UserEntity update(UserUpdateDto o) {
        UserEntity user = findById(o.getId());

        if (o.getUsername() != null) {
            user.setUsername(o.getUsername());
        }

        if (o.getAvatar() != null) {
            user.setAvatar(o.getAvatar());
        }

        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        UserEntity user = findById(id);

        // soft delete user entity
        user.setDisabled(true);
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
    }
}
