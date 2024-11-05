package estacio.API.EstoquePro.Utils;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private String SECRET_KEY = "3a783932b96045719ca4599a616a7b0c";

    public String generateToken(String cpf, String role) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role); 
        String token = JWT.create()
                .withIssuer("auth")
                .withSubject(cpf)
                .withExpiresAt(getExpirationDate())
                .withClaim("role", role)
                .sign(algorithm);
            return token;


    }
    
    private Instant getExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    
}
