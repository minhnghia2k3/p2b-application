package p2b.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import p2b.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByIdAndIsDisabledFalse(Long userID);

    UserEntity findByEmail(String email);
}
