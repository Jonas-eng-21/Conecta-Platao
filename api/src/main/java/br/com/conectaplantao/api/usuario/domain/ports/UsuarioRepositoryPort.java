package br.com.conectaplantao.api.usuario.domain.ports;

import br.com.conectaplantao.api.usuario.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioRepositoryPort {

    Usuario save(Usuario usuario);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findById(Long id);
}
