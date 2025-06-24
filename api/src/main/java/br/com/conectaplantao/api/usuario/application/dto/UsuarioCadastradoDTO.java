package br.com.conectaplantao.api.usuario.application.dto;

import br.com.conectaplantao.api.usuario.domain.enums.TipoUsuario;
import br.com.conectaplantao.api.usuario.domain.model.Usuario;

public record UsuarioCadastradoDTO(
        Long id,
        String email,
        TipoUsuario tipoUsuario,
        Boolean ativo
) {
    public static UsuarioCadastradoDTO fromDomain(Usuario usuario) {
        return new UsuarioCadastradoDTO(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getTipoUsuario(),
                usuario.getAtivo()
        );
    }
}
