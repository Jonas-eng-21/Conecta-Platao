Este enum simples e poderoso garantirá que um usuário só possa ser de um tipo válido, evitando erros de digitação e adicionando clareza ao código.

**Caminho:** `src/main/java/br/com/conectaplantao/api/usuario/domain/enums/TipoUsuario.java`

Java

```
package br.com.conectaplantao.api.usuario.domain.enums;

/**
 * Enum para definir os tipos de usuários no sistema.
 * Garante a tipagem segura e evita o uso de Strings mágicas.
 */
public enum TipoUsuario {
    MEDICO,
    HOSPITAL
}
```

**💡 Por que este código?**

- **Segurança de Tipo:** Usar um `enum` em vez de `String` ou `int` previne que valores inválidos (ex: "DOUTOR", "HOSPITL") sejam atribuídos ao tipo de um usuário.
- **Clareza:** O código fica mais legível. `usuario.getTipo() == TipoUsuario.MEDICO` é muito mais claro do que `usuario.getTipo().equals("medico")`.