package br.com.conectaplantao.api.usuario.domain.model;

import br.com.conectaplantao.api.usuario.domain.enums.TipoUsuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Hospital extends Usuario {

    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;

    public Hospital(String email, String senha, String nomeFantasia, String razaoSocial, String cnpj) {
        super(email, senha, TipoUsuario.HOSPITAL, true);
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
    }

    public Hospital(Long id, String email, String senha, String nomeFantasia, String razaoSocial, String cnpj, Boolean ativo) {
        super(id, email, senha, TipoUsuario.HOSPITAL, ativo);
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
    }
}
