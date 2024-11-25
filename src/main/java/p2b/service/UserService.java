package p2b.service;

import p2b.dto.RegisterUserDto;
import p2b.dto.UserCreateDto;
import p2b.dto.UserUpdateDto;
import p2b.entity.UserEntity;

public interface UserService {
    public UserEntity registerUser(RegisterUserDto dto);

//    public UserEntity loginUser(LoginUserDto dto);

    public UserEntity findById(long id);

    public UserEntity findByEmail(String email);

    public UserEntity insert(UserCreateDto dto);

    public UserEntity update(UserUpdateDto updateDto);

    public void delete(long id);
}
