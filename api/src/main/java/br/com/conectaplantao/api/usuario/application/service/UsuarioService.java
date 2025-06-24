package br.com.conectaplantao.api.usuario.application.service;

import br.com.conectaplantao.api.usuario.application.dto.AtualizarHospitalDTO;
import br.com.conectaplantao.api.usuario.application.dto.AtualizarMedicoDTO;
import br.com.conectaplantao.api.usuario.application.dto.CriarHospitalDTO;
import br.com.conectaplantao.api.usuario.application.dto.CriarMedicoDTO;
import br.com.conectaplantao.api.usuario.domain.model.Hospital;
import br.com.conectaplantao.api.usuario.domain.model.Medico;
import br.com.conectaplantao.api.usuario.domain.model.Usuario;
import br.com.conectaplantao.api.usuario.domain.ports.UsuarioRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario criarMedico(CriarMedicoDTO dto) {
        if (usuarioRepositoryPort.findByEmail(dto.email()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado no sistema.");
        }
        var senhaCriptografada = passwordEncoder.encode(dto.senha());
        var medico = new Medico(
                dto.email(),
                senhaCriptografada,
                dto.nome(),
                dto.crm(),
                dto.especialidade()
        );
        return usuarioRepositoryPort.save(medico);
    }

    @Transactional
    public Usuario criarHospital(CriarHospitalDTO dto) {
        if (usuarioRepositoryPort.findByEmail(dto.email()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado no sistema.");
        }
        var senhaCriptografada = passwordEncoder.encode(dto.senha());
        var hospital = new Hospital(
                dto.email(),
                senhaCriptografada,
                dto.nomeFantasia(),
                dto.razaoSocial(),
                dto.cnpj()
        );
        return usuarioRepositoryPort.save(hospital);
    }


    public List<Usuario> listarTodos() {
        return usuarioRepositoryPort.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepositoryPort.findById(id);
    }

    @Transactional
    public Usuario atualizarMedico(Long id, AtualizarMedicoDTO dto) {
        Usuario usuario = usuarioRepositoryPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o id: " + id));

        if (!(usuario instanceof Medico medico)) {
            throw new IllegalArgumentException("Usuário com o id " + id + " não é um médico.");
        }

        if (dto.nome() != null) medico.setNome(dto.nome());
        if (dto.email() != null) medico.setEmail(dto.email());
        if (dto.especialidade() != null) medico.setEspecialidade(dto.especialidade());

        return usuarioRepositoryPort.save(medico);
    }

    @Transactional
    public Usuario atualizarHospital(Long id, AtualizarHospitalDTO dto) {
        Usuario usuario = usuarioRepositoryPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o id: " + id));

        if (!(usuario instanceof Hospital hospital)) {
            throw new IllegalArgumentException("Usuário com o id " + id + " não é um hospital.");
        }

        if (dto.email() != null) hospital.setEmail(dto.email());
        if (dto.nomeFantasia() != null) hospital.setNomeFantasia(dto.nomeFantasia());
        if (dto.razaoSocial() != null) hospital.setRazaoSocial(dto.razaoSocial());

        return usuarioRepositoryPort.save(hospital);
    }

    @Transactional
    public void desativar(Long id) {
        Usuario usuario = usuarioRepositoryPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o id: " + id));

        usuario.setAtivo(false);
        usuarioRepositoryPort.save(usuario);
    }

}
