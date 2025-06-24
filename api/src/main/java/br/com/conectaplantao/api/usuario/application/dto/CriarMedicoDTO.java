package br.com.conectaplantao.api.usuario.application.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CriarMedicoDTO(
        @NotBlank(message = "O nome não pode estar em branco")
        String nome,

        @NotBlank(message = "O e-mail não pode estar em branco")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "A senha não pode estar em branco")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String senha,

        @NotBlank(message = "O CRM não pode estar em branco")
        String crm,

        @NotBlank(message = "A especialidade não pode estar em branco")
        String especialidade
) {
}
