package br.com.conectaplantao.api.usuario.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CriarHospitalDTO(

        @NotBlank(message = "O e-mail não pode estar em branco")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "A senha não pode estar em branco")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String senha,

        @NotBlank(message = "O nome fantasia não pode estar em branco")
        String nomeFantasia,

        @NotBlank(message = "A razão social não pode estar em branco")
        String razaoSocial,

        @NotBlank(message = "O CNPJ não pode estar em branco")
        String cnpj

) {
}
