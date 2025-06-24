### Classe Base `Usuario.java`

Esta será uma classe **abstrata**. Ninguém no sistema será apenas um "Usuário"; será sempre um `Medico` ou um `Hospital`. A classe abstrata nos ajuda a garantir essa regra e a compartilhar os campos e comportamentos comuns.

**Caminho:** `src/main/java/br/com/conectaplantao/api/usuario/domain/model/Usuario.java`

Java

```
package br.com.conectaplantao.api.usuario.domain.model;

import br.com.conectaplantao.api.usuario.domain.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe de domínio abstrata que representa um usuário genérico.
 * Contém os atributos e regras de negócio comuns a todos os usuários.
 * NÃO possui anotações de persistência (como @Entity), mantendo o domínio puro.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Usuario {

    private Long id;
    private String email;
    private String senha; // A senha em seu estado "bruto" antes de ser criptografada
    private TipoUsuario tipoUsuario;
    private Boolean ativo = true;

}
```

**💡 Por que este código?**

- **Abstração (SOLID):** Representa o conceito de Usuário sem se prender aos detalhes. Força a criação de tipos específicos (`Medico`, `Hospital`), o que está alinhado com a regra de negócio.
- **Domínio Puro:** Note a ausência total de anotações de frameworks. Esta classe poderia ser usada em uma aplicação desktop, web ou mobile sem nenhuma alteração.
- **Lombok:** Usei `@Getter`, `@Setter`, etc., para reduzir o código boilerplate. É uma prática comum e produtiva.