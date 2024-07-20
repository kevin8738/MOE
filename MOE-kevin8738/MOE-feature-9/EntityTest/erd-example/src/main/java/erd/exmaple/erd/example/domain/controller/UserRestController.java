package erd.exmaple.erd.example.domain.controller;


import erd.exmaple.erd.example.domain.UserEntity;
import erd.exmaple.erd.example.domain.dto.*;


import erd.exmaple.erd.example.domain.dto.passwordDTO.PasswordChangeRequestDTO;
import erd.exmaple.erd.example.domain.dto.passwordDTO.PasswordFindRequestDTO;
import erd.exmaple.erd.example.domain.dto.passwordDTO.PasswordSetRequestDTO;
import erd.exmaple.erd.example.domain.jwt.AuthRequest;
import erd.exmaple.erd.example.domain.jwt.AuthResponse;
import erd.exmaple.erd.example.domain.service.UserService.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.userdetails.UserDetails;


import erd.exmaple.erd.example.domain.jwt.JwtUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRestController {
    private final UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/join")
    public ResponseEntity<UserResponseDTO.JoinResultDTO> joinUser(@RequestBody @Valid UserRequestDTO.JoinDto joinDto) {
        UserResponseDTO.JoinResultDTO response = userService.joinUser(joinDto);
        if (!response.isSuccess()) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/check-phoneNumber")
    public ResponseEntity<UserPhoneNumberCheckResultDTO> checkPhoneNumber(@RequestParam String phoneNumber) {
        return ResponseEntity.ok(userService.checkPhoneNumber(phoneNumber));
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long userId) {
        UserResponseDTO user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login") // 로그인 엔드포인트
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            // 사용자 인증 시도
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getPhoneNumber(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            // 인증 실패 시 예외 처리
            throw new Exception("Incorrect phone number or password", e);
        }

        // 인증된 사용자 정보 로드
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authRequest.getPhoneNumber());

        // JWT 토큰 생성
        final String jwt = jwtUtil.generateToken(userDetails);

        // 성공적인 로그인에 대한 로그 추가
        System.out.println("User " + authRequest.getPhoneNumber() + " successfully logged in.");


        // 생성된 JWT 토큰을 응답으로 반환
        return ResponseEntity.ok(new AuthResponse(jwt));
    }


    // 비밀번호 변경 엔드포인트
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @RequestBody @Valid PasswordChangeRequestDTO passwordChangeRequest) {
        try {
            userService.changePassword(passwordChangeRequest);
            return ResponseEntity.ok("Password changed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 비밀번호 찾기 및 새 비밀번호 설정 엔드포인트
    @PostMapping("/find-password")
    public ResponseEntity<String> findAndResetPassword(@RequestBody @Valid PasswordFindRequestDTO passwordFindRequest) {
        try {
            userService.findPassword(passwordFindRequest);
            return ResponseEntity.ok("New password has been set.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

    //    @PostMapping("/reset-password")
//    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequestDTO request) {
//        String phoneNumber = request.getPhoneNumber();
//        String confirmPhoneNumber = request.getConfirmPhoneNumber();
//
//        if (!phoneNumber.equals(confirmPhoneNumber)) {
//            return ResponseEntity.badRequest().body("Phone numbers do not match.");
//        }
//
//        String newPassword = userService.resetPasswordByPhoneNumber(phoneNumber);
//        if (newPassword != null) {
//            return ResponseEntity.ok("Password reset successfully. Your new password is: " + newPassword);
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to reset password.");
//        }
//    }



