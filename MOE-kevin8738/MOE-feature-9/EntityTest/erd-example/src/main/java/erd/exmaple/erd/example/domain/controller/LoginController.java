package erd.exmaple.erd.example.domain.controller;

import erd.exmaple.erd.example.domain.dto.ReissueTokenResponseDTO;
import erd.exmaple.erd.example.domain.dto.UserDTO;
import erd.exmaple.erd.example.domain.service.UserService.UserServiceSocial;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    private final UserServiceSocial userServiceSocial;

     //user 경로로 접속 시 /user/login으로 리디렉션
    @GetMapping
    public String redirectToLogin() {
        log.info("Redirecting to login page");
        return "redirect:/user/login";
    }

    // 로그인 화면 (카카오, 네이버 선택)
    @GetMapping("/login")
    public String showLoginPage() {
        log.info("Accessing login page");
        return "login"; // login.html 템플릿을 반환
    }

    // 카카오 로그인 처리
    @PostMapping(value = "/kakao")
    @ResponseBody
    public ResponseEntity<?> kakaoLogin(@RequestBody ReissueTokenResponseDTO reissueTokenResponseDto) {
        log.info("Kakao login attempt");
        try {
            long idToken = userServiceSocial.getKakaoIdToken(reissueTokenResponseDto.getAccessToken());
            UserDTO userDto = userServiceSocial.findUserById(idToken);
            if (userDto != null) {
                log.info("Kakao login successful for user id: {}", userDto.getId());
                return ResponseEntity.ok(userDto);
            } else {
                log.warn("Kakao login failed: user not found");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Kakao login failed: user not found");
            }
        } catch (Exception e) {
            log.error("Exception occurred while getting idToken: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Kakao login failed: internal server error");
        }
    }

    // 네이버 로그인 처리
    @PostMapping(value = "/naver")
    @ResponseBody
    public ResponseEntity<?> naverLogin(@RequestBody ReissueTokenResponseDTO reissueTokenResponseDto) {
        log.info("Naver login attempt");
        try {
            long idToken = userServiceSocial.getNaverIdToken(reissueTokenResponseDto.getAccessToken());
            UserDTO userDto = userServiceSocial.findUserById(idToken);
            if (userDto != null) {
                log.info("Naver login successful for user id: {}", userDto.getId());
                return ResponseEntity.ok(userDto);
            } else {
                log.warn("Naver login failed: user not found");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Naver login failed: user not found");
            }
        } catch (Exception e) {
            log.error("Exception occurred while getting idToken: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Naver login failed: internal server error");
        }
    }

    // 구글 로그인 처리
    @PostMapping(value = "/google")
    @ResponseBody
    public ResponseEntity<?> googleLogin(@RequestBody ReissueTokenResponseDTO reissueTokenResponseDto) {
        log.info("Google login attempt");
        try {
            long idToken = userServiceSocial.getGoogleIdToken(reissueTokenResponseDto.getAccessToken());
            UserDTO userDto = userServiceSocial.findUserById(idToken);
            if (userDto != null) {
                log.info("Google login successful for user id: {}", userDto.getId());
                return ResponseEntity.ok(userDto);
            } else {
                log.warn("Google login failed: user not found");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Google login failed: user not found");
            }
        } catch (Exception e) {
            log.error("Exception occurred while getting idToken: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Google login failed: internal server error");
        }
    }

    // 카카오 로그인 뷰
    @GetMapping("/kakao")
    public String showKakaoLoginPage() {
        log.info("Accessing Kakao login page");
        return "kakao-login"; // kakao-login.html 템플릿을 반환
    }

    // 네이버 로그인 뷰
    @GetMapping("/naver")
    public String showNaverLoginPage() {
        log.info("Accessing Naver login page");
        return "naver-login"; // naver-login.html 템플릿을 반환
    }

    // 구글 로그인 뷰
    @GetMapping("/google")
    public String showGoogleLoginPage() {
        log.info("Accessing Google login page");
        return "google-login"; // google-login.html 템플릿을 반환
    }
}
