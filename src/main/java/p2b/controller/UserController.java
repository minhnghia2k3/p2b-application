package p2b.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import p2b.dto.ApiResponse;
import p2b.dto.RegisterUserDto;
import p2b.dto.UserResponseDto;
import p2b.dto.UserUpdateDto;
import p2b.entity.UserEntity;
import p2b.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{userID}")
    public ResponseEntity<ApiResponse<UserResponseDto>> findUserById(@PathVariable Long userID) {
        UserEntity user = userService.findById(userID);

        // Map to userResponseDto
        UserResponseDto userResponse = new UserResponseDto(user);
        ApiResponse<UserResponseDto> response = new ApiResponse<>(HttpStatus.OK.value(), userResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<ApiResponse<UserResponseDto>> registerUser(@Valid @RequestBody RegisterUserDto dto) {
        UserEntity userEntity = userService.registerUser(dto);

        UserResponseDto UserResponseDto = new UserResponseDto(userEntity);

        var response = new ApiResponse<>(HttpStatus.CREATED.value(), UserResponseDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{userID}")
    public ResponseEntity<ApiResponse<UserResponseDto>> updateUser(@PathVariable Long userID,
                                                                   @Valid @RequestBody UserUpdateDto dto
    ) {
        dto.setId(userID);
        UserEntity user = userService.update(dto);

        UserResponseDto UserResponseDto = new UserResponseDto(user);

        var response = new ApiResponse<>(HttpStatus.OK.value(), UserResponseDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userID) {
        userService.delete(userID);

        return ResponseEntity.noContent().build();
    }
}
