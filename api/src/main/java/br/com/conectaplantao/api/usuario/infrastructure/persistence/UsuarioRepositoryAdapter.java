package br.com.conectaplantao.api.usuario.infrastructure.persistence;

import br.com.conectaplantao.api.usuario.domain.model.Hospital;
import br.com.conectaplantao.api.usuario.domain.model.Medico;
import br.com.conectaplantao.api.usuario.domain.model.Usuario;
import br.com.conectaplantao.api.usuario.domain.ports.UsuarioRepositoryPort;
import br.com.conectaplantao.api.usuario.infrastructure.persistence.entity.HospitalPersistenceEntity;
import br.com.conectaplantao.api.usuario.infrastructure.persistence.entity.MedicoPersistenceEntity;
import br.com.conectaplantao.api.usuario.infrastructure.persistence.repository.SpringDataUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<Usuario> findAll() {
        return springDataRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        springDataRepository.deleteById(id);
    }


    private UsuarioPersistenceEntity toEntity(Usuario usuario) {
        if (usuario instanceof Medico medico) {
            MedicoPersistenceEntity entity = new MedicoPersistenceEntity();
            entity.setId(medico.getId());
            entity.setEmail(medico.getEmail());
            entity.setSenha(medico.getSenha());
            entity.setAtivo(medico.getAtivo());
            entity.setNome(medico.getNome());
            entity.setCrm(medico.getCrm());
            entity.setEspecialidade(medico.getEspecialidade());
            return entity;

        } else if (usuario instanceof Hospital hospital) {
            HospitalPersistenceEntity entity = new HospitalPersistenceEntity();
            entity.setId(hospital.getId());
            entity.setEmail(hospital.getEmail());
            entity.setSenha(hospital.getSenha());
            entity.setAtivo(hospital.getAtivo());
            entity.setNomeFantasia(hospital.getNomeFantasia());
            entity.setRazaoSocial(hospital.getRazaoSocial());
            entity.setCnpj(hospital.getCnpj());
            return entity;

        } else {
            throw new IllegalArgumentException("Tipo de usuário desconhecido para mapeamento de entidade: " + usuario.getClass().getName());
        }
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
