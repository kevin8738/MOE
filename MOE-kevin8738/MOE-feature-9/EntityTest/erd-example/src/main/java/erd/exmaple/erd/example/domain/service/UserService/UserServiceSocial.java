package erd.exmaple.erd.example.domain.service.UserService;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import erd.exmaple.erd.example.domain.UserEntity;
import erd.exmaple.erd.example.domain.dto.UserDTO;
import erd.exmaple.erd.example.domain.enums.Provider;
import erd.exmaple.erd.example.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceSocial {

    private final UserRepository userRepository;

    public Long getKakaoIdToken(String accessToken) throws Exception {
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        return getIdTokenFromProvider(accessToken, reqURL, "id", "Bearer");
    }

    public Long getNaverIdToken(String accessToken) throws Exception {
        String reqURL = "https://openapi.naver.com/v1/nid/me";
        return getIdTokenFromProvider(accessToken, reqURL, "response.id", "Bearer");
    }

    public Long getGoogleIdToken(String accessToken) throws Exception {
        String reqURL = "https://www.googleapis.com/oauth2/v3/userinfo";
        return getIdTokenFromProvider(accessToken, reqURL, "sub", "Bearer");
    }

    private Long getIdTokenFromProvider(String accessToken, String reqURL, String idKey, String tokenType) throws Exception {
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", tokenType + " " + accessToken);

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    result.append(line);
                }

                JsonElement element = JsonParser.parseString(result.toString());
                long idToken = element.getAsJsonObject().get(idKey).getAsLong();
                br.close();
                return idToken;
            } else {
                throw new Exception("Failed to get idToken from provider");
            }
        } catch (IOException e) {
            throw new Exception("서버에서 사용자 정보를 가져오는 중에 문제가 발생했습니다.", e);
        }
    }

    public UserDTO findUserById(Long id) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        return optionalUserEntity.map(UserDTO::toUserDTO).orElse(null);
    }

    public UserDTO processOAuth2User(OAuth2User oAuth2User) {
        String providerId = oAuth2User.getName();
        Optional<UserEntity> userEntityOptional = userRepository.findByProviderId(providerId);

        UserEntity userEntity;
        if (userEntityOptional.isPresent()) {
            userEntity = userEntityOptional.get();
        } else {
            // 새로운 사용자 생성
            userEntity = new UserEntity();
            userEntity.setProviderId(providerId);
            userEntity.setNickname(oAuth2User.getAttribute("name"));
            userEntity.setProvider(Provider.valueOf(oAuth2User.getAttribute("provider")));
            userRepository.save(userEntity);
        }

        return UserDTO.toUserDTO(userEntity);
    }
}