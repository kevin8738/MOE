package erd.exmaple.erd.example.domain.service.UserService;

import erd.exmaple.erd.example.domain.dto.passwordDTO.PasswordChangeRequestDTO;
import erd.exmaple.erd.example.domain.dto.UserPhoneNumberCheckResultDTO;
import erd.exmaple.erd.example.domain.dto.UserRequestDTO;
import erd.exmaple.erd.example.domain.dto.UserResponseDTO;
import erd.exmaple.erd.example.domain.dto.passwordDTO.PasswordFindRequestDTO;
import erd.exmaple.erd.example.domain.dto.passwordDTO.PasswordSetRequestDTO;

public interface UserService {
    UserResponseDTO.JoinResultDTO joinUser(UserRequestDTO.JoinDto joinDto);
    UserPhoneNumberCheckResultDTO checkPhoneNumber(String phoneNumber);
    UserResponseDTO getUserById(Long userId);
    String resetPasswordByPhoneNumber(String phoneNumber);
    void changePassword(PasswordChangeRequestDTO passwordChangeRequest) throws Exception;
    void findPassword(PasswordFindRequestDTO passwordFindRequest) throws Exception;
    //void setNewPassword(PasswordSetRequestDTO passwordSetRequest) throws Exception;
}