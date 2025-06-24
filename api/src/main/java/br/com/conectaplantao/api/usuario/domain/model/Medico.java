package br.com.conectaplantao.api.usuario.domain.model;

import br.com.conectaplantao.api.usuario.domain.enums.TipoUsuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Medico extends Usuario {

    private String nome;
    private String crm;
    private String especialidade;

    public Medico(Long id, String email, String senha, String nome, String crm, String especialidade, Boolean ativo) {
        super(id, email, senha, TipoUsuario.MEDICO, ativo);
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
    }
}
