package erd.exmaple.erd.example.domain.service.UserService;

import erd.exmaple.erd.example.domain.dto.UserRequestDTO;
import erd.exmaple.erd.example.domain.dto.UserResponseDTO;
import erd.exmaple.erd.example.domain.dto.UserPhoneNumberCheckResultDTO;

public interface UserJoinService {
    UserResponseDTO.JoinResultDTO joinUser(UserRequestDTO.JoinDto joinDto);
    UserPhoneNumberCheckResultDTO checkPhoneNumber(String phoneNumber);
    UserResponseDTO getUserById(Long userId);
}