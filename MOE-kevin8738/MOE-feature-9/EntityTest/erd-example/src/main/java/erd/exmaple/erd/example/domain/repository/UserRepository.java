package erd.exmaple.erd.example.domain.repository;

import erd.exmaple.erd.example.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByPhoneNumber(String phoneNumber);
    Optional<UserEntity> findByNickname(String nickname);
    Optional<UserEntity> findByPhoneNumberUser(String phoneNumber);

}
