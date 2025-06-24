package br.com.conectaplantao.api.usuario.infrastructure.web.controller;

import br.com.conectaplantao.api.usuario.application.dto.*;
import br.com.conectaplantao.api.usuario.application.service.UsuarioService;
import br.com.conectaplantao.api.usuario.domain.model.Usuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;


    @PostMapping("/medicos")
    public ResponseEntity<UsuarioCadastradoDTO> criarMedico(@RequestBody @Valid CriarMedicoDTO dto) {
        Usuario medicoCriado = usuarioService.criarMedico(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(medicoCriado.getId()).toUri();
        return ResponseEntity.created(uri).body(UsuarioCadastradoDTO.fromDomain(medicoCriado));
    }

    @PostMapping("/hospitais")
    public ResponseEntity<UsuarioCadastradoDTO> criarHospital(@RequestBody @Valid CriarHospitalDTO dto) {
        Usuario hospitalCriado = usuarioService.criarHospital(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(hospitalCriado.getId()).toUri();
        return ResponseEntity.created(uri).body(UsuarioCadastradoDTO.fromDomain(hospitalCriado));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioCadastradoDTO>> listarTodosUsuarios() {
        List<UsuarioCadastradoDTO> usuarios = usuarioService.listarTodos()
                .stream()
                .map(UsuarioCadastradoDTO::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioCadastradoDTO> buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(usuario -> ResponseEntity.ok(UsuarioCadastradoDTO.fromDomain(usuario)))
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/medicos/{id}")
    public ResponseEntity<UsuarioCadastradoDTO> atualizarMedico(@PathVariable Long id, @RequestBody @Valid AtualizarMedicoDTO dto) {
        Usuario medicoAtualizado = usuarioService.atualizarMedico(id, dto);
        return ResponseEntity.ok(UsuarioCadastradoDTO.fromDomain(medicoAtualizado));
    }

    @PutMapping("/hospitais/{id}")
    public ResponseEntity<UsuarioCadastradoDTO> atualizarHospital(@PathVariable Long id, @RequestBody @Valid AtualizarHospitalDTO dto) {
        Usuario hospitalAtualizado = usuarioService.atualizarHospital(id, dto);
        return ResponseEntity.ok(UsuarioCadastradoDTO.fromDomain(hospitalAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativarUsuario(@PathVariable Long id) {
        usuarioService.desativar(id);
        return ResponseEntity.noContent().build();
    }



}
