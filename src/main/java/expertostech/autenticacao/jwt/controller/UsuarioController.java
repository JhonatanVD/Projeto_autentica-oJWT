package expertostech.autenticacao.jwt.controller;

import expertostech.autenticacao.jwt.model.UsuarioModel;
import expertostech.autenticacao.jwt.repository.UsuarioRepository;
import expertostech.autenticacao.jwt.services.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

//    @Autowired
    private static final UsuarioService usuarioService = new UsuarioService();


    public UsuarioController(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<UsuarioModel>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/salvar")
    public  ResponseEntity<UsuarioModel> salvar(@RequestBody UsuarioModel usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        return ResponseEntity.ok(repository.save(usuario));

    }

    @PostMapping("/login")
    public ResponseEntity<String> validarSenha(@RequestParam String login,
                                                @RequestParam String password) {

        Optional<UsuarioModel> optUsuario = repository.findByLogin(login);

        if (optUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario " + login + " nao encontrado");
        }

        UsuarioModel usuario = optUsuario.get();
        boolean valid = encoder.matches(password, usuario.getPassword());

        String msg = "";
        HttpStatus status = null;

        if (valid) {
            msg = UsuarioService.login(login);
            status = HttpStatus.OK;
        } else {
            msg = "Senha informada diferente da senha cadastrada";
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status).body(msg);
    }
}
