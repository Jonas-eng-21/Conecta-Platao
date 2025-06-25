package br.com.conectaplantao.api.usuario.application.service;

import br.com.conectaplantao.api.usuario.application.dto.CriarMedicoDTO;
import br.com.conectaplantao.api.usuario.domain.model.Medico;
import br.com.conectaplantao.api.usuario.domain.model.Usuario;
import br.com.conectaplantao.api.usuario.domain.ports.UsuarioRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepositoryPort usuarioRepositoryPort;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Deve criar um médico com sucesso quando o e-mail não existe")
    void criarMedico_ComEmailNaoExistente_DeveRetornarMedicoSalvo() {

        var dto = new CriarMedicoDTO("Dr. House", "house@example.com", "senha123", "12345", "Diagnóstico");
        when(usuarioRepositoryPort.findByEmail(dto.email())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(dto.senha())).thenReturn("senha_criptografada");
        when(usuarioRepositoryPort.save(any(Medico.class))).thenAnswer(invocation -> invocation.getArgument(0));


        Usuario medicoSalvo = usuarioService.criarMedico(dto);


        assertNotNull(medicoSalvo);
        assertEquals(dto.email(), medicoSalvo.getEmail());
        assertEquals("senha_criptografada", medicoSalvo.getSenha());
        verify(usuarioRepositoryPort, times(1)).findByEmail(dto.email());
        verify(usuarioRepositoryPort, times(1)).save(any(Medico.class));
        verify(passwordEncoder, times(1)).encode(dto.senha());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar criar médico com e-mail já existente")
    void criarMedico_ComEmailExistente_DeveLancarExcecao() {

        var dto = new CriarMedicoDTO("Dr. House", "house@example.com", "senha123", "12345", "Diagnóstico");
        when(usuarioRepositoryPort.findByEmail(dto.email())).thenReturn(Optional.of(new Medico()));

        assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.criarMedico(dto);
        });

        verify(usuarioRepositoryPort, never()).save(any());
        verify(passwordEncoder, never()).encode(anyString());
    }

}
