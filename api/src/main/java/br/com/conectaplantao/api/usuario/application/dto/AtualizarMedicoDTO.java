package br.com.conectaplantao.api.usuario.application.dto;

import jakarta.validation.constraints.Email;

public record AtualizarMedicoDTO(
        String nome,
        @Email(message = "Formato de e-mail inválido")
        String email,
        String especialidade
) {
}
