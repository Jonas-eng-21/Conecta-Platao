package br.com.conectaplantao.api.usuario.infrastructure.persistence.repository;

import br.com.conectaplantao.api.usuario.infrastructure.persistence.UsuarioPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataUsuarioRepository extends JpaRepository<UsuarioPersistenceEntity, Long> {

    Optional<UsuarioPersistenceEntity> findByEmail(String email);

}
