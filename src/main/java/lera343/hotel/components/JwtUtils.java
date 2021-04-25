package lera343.hotel.components;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;

import lera343.hotel.entity.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
@RequiredArgsConstructor
public class JwtUtils {
    public static final SecretKey KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public String generateJwtToken(Authentication authentication) {
        var principal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+10000000))
                .signWith(KEY)
                .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validate(String token){
        try{
            token = token.replace("\"","");
            Jwts.parser()
				.setSigningKey(KEY)
                .parseClaimsJws(token);
            return true;
        }catch (Exception e){

            e.printStackTrace();
            return false;
        }
    }

}