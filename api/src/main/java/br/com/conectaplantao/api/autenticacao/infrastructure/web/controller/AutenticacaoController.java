package br.com.conectaplantao.api.autenticacao.infrastructure.web.controller;


import br.com.conectaplantao.api.autenticacao.application.dto.LoginRequestDTO;
import br.com.conectaplantao.api.autenticacao.application.dto.TokenResponseDTO;
import br.com.conectaplantao.api.autenticacao.application.service.AutenticacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid LoginRequestDTO dto) {
        String token = autenticacaoService.efetuarLogin(dto);
        return ResponseEntity.ok(new TokenResponseDTO(token));
    }

}
