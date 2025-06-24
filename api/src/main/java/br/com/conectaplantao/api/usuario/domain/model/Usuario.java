package br.com.conectaplantao.api.usuario.domain.model;

import br.com.conectaplantao.api.usuario.domain.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Usuario {

    private long id;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;
    private Boolean ativo = true;


}
