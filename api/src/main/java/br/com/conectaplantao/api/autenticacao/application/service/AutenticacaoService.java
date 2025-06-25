package br.com.conectaplantao.api.autenticacao.application.service;


import br.com.conectaplantao.api.autenticacao.application.dto.LoginRequestDTO;
import br.com.conectaplantao.api.shared.infrastructure.security.TokenService;
import br.com.conectaplantao.api.usuario.domain.model.Usuario;
import br.com.conectaplantao.api.usuario.domain.ports.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public String efetuarLogin(LoginRequestDTO dto) {
        var authToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var authentication = authenticationManager.authenticate(authToken);
        var userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        Usuario usuarioDeDominio = usuarioRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário autenticado não encontrado no banco de dados"));
        return tokenService.generateToken(usuarioDeDominio);
    }

}
