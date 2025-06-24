package br.com.conectaplantao.api.usuario.infrastructure.persistence.entity;

import br.com.conectaplantao.api.usuario.infrastructure.persistence.UsuarioPersistenceEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("MEDICO")
@NoArgsConstructor
public class MedicoPersistenceEntity extends UsuarioPersistenceEntity {
}
