package expertostech.autenticacao.jwt.services;

import expertostech.autenticacao.jwt.security.JWTTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsuarioService {
    private static final JWTTokenProvider jwtTokenProvider = new JWTTokenProvider();

    public static String login(String usuario){
        return jwtTokenProvider.createToken(usuario);
    }

}
