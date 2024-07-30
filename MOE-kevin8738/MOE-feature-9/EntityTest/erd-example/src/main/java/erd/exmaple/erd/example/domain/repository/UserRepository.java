package erd.exmaple.erd.example.domain.repository;

import erd.exmaple.erd.example.domain.UserEntity;
import erd.exmaple.erd.example.domain.enums.LoginStatus;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
import java.time.LocalDate;
=======
>>>>>>> 2a1b47c53e50be52577f77cffbbd6e9bd293ba33
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByPhoneNumber(String phoneNumber);
    Optional<UserEntity> findByNickname(String nickname);
    //Optional<UserEntity> findByPhoneNumberUser(String phoneNumber);
    List<UserEntity> findAllByStatus(LoginStatus status);
    List<UserEntity> findByNicknameContaining(String keyword);
    Optional<UserEntity> findByProviderId(String providerId);
<<<<<<< HEAD

=======
>>>>>>> 2a1b47c53e50be52577f77cffbbd6e9bd293ba33
}
