package erd.exmaple.erd.example.domain.service.UserService;

import erd.exmaple.erd.example.domain.dto.UserPhoneNumberCheckResultDTO;
import erd.exmaple.erd.example.domain.dto.UserRequestDTO;
import erd.exmaple.erd.example.domain.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO.JoinResultDTO joinUser(UserRequestDTO.JoinDto joinDto);
    UserPhoneNumberCheckResultDTO checkPhoneNumber(String phoneNumber);
    UserResponseDTO getUserById(Long userId);
}