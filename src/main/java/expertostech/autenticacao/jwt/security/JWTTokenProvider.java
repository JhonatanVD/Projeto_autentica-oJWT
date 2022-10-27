package expertostech.autenticacao.jwt.security;

import expertostech.autenticacao.jwt.services.DetalheUsuarioServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public class JWTTokenProvider {
    public static final int validityInMilliseconds = 600_000;
    public static final String secretKey = "119b6c33-1e8a-4944-b0d0-1353b040877f";

    @Autowired
//    private MyUserDetails myUserDetails;
    
    public String createToken(String username) {

        Claims claims = Jwts.claims().setSubject(username);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
    }

//    public Authentication getAuthentication(String token) {
//        UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
//        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//    }
}
