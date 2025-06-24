package br.com.conectaplantao.api.usuario.application.dto;

import jakarta.validation.constraints.Email;

public record AtualizarHospitalDTO(
        @Email(message = "Formato de e-mail inválido")
        String email,
        String nomeFantasia,
        String razaoSocial
) {
}
