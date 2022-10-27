package expertostech.autenticacao.jwt.data;

import expertostech.autenticacao.jwt.model.UsuarioModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class DetalheUsuarioData  implements UserDetails {


    private final Optional<UsuarioModel> usuario;

    public DetalheUsuarioData(Optional<UsuarioModel> usuario) {
        this.usuario = usuario;
    }

    @Override
    //getAuthorities = permissões do Usuário
    public Collection<? extends GrantedAuthority> getAuthorities()  {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return usuario.orElse( new UsuarioModel()).getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.orElse(new UsuarioModel()).getLogin();
    }

    // Conta não inspirada
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //Conta não bloqueada
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //Credencias não inspiradas
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

   //Usuario ativo
    @Override
    public boolean isEnabled() {
        return true;
    }
}
