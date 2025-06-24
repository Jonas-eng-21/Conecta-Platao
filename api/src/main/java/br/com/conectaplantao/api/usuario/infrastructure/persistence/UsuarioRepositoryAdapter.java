package br.com.conectaplantao.api.usuario.infrastructure.persistence;

import br.com.conectaplantao.api.usuario.domain.model.Hospital;
import br.com.conectaplantao.api.usuario.domain.model.Medico;
import br.com.conectaplantao.api.usuario.domain.model.Usuario;
import br.com.conectaplantao.api.usuario.domain.ports.UsuarioRepositoryPort;
import br.com.conectaplantao.api.usuario.infrastructure.persistence.repository.SpringDataUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final SpringDataUsuarioRepository springDataRepository;

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioPersistenceEntity entity = toEntity(usuario);
        UsuarioPersistenceEntity savedEntity = springDataRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return springDataRepository.findByEmail(email).map(this::toDomain);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return springDataRepository.findById(id).map(this::toDomain);
    }


    private UsuarioPersistenceEntity toEntity(Usuario usuario) {
        UsuarioPersistenceEntity entity = new UsuarioPersistenceEntity();
        entity.setId(usuario.getId());
        entity.setEmail(usuario.getEmail());
        entity.setSenha(usuario.getSenha());
        entity.setAtivo(usuario.getAtivo());

        if (usuario instanceof Medico medico) {
            entity.setNome(medico.getNome());
            entity.setCrm(medico.getCrm());
            entity.setEspecialidade(medico.getEspecialidade());
        } else if (usuario instanceof Hospital hospital) {
            entity.setNomeFantasia(hospital.getNomeFantasia());
            entity.setRazaoSocial(hospital.getRazaoSocial());
            entity.setCnpj(hospital.getCnpj());
        }
        return entity;
    }


    private Usuario toDomain(UsuarioPersistenceEntity entity) {
        if (entity.getCrm() != null) {
            return new Medico(
                    entity.getId(),
                    entity.getEmail(),
                    entity.getSenha(),
                    entity.getNome(),
                    entity.getCrm(),
                    entity.getEspecialidade(),
                    entity.getAtivo()
            );
        } else {
            return new Hospital(
                    entity.getId(),
                    entity.getEmail(),
                    entity.getSenha(),
                    entity.getNomeFantasia(),
                    entity.getRazaoSocial(),
                    entity.getCnpj(),
                    entity.getAtivo()
            );
        }
    }

}
