package erd.exmaple.erd.example.domain.jwt;


<<<<<<< HEAD
import lombok.Getter;
import lombok.Setter;


=======
>>>>>>> 2a1b47c53e50be52577f77cffbbd6e9bd293ba33
public class AuthResponse {
    private final String jwt;
    private final String message;

    public AuthResponse(String jwt,String message) {
        this.jwt = jwt;
        this.message = message;
    }

    public String getJwt() {
        return jwt;
    }
    public String getMessage(String message) {
        return message;
    }
}