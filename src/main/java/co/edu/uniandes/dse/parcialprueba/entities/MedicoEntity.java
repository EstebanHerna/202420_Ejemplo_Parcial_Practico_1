package co.edu.uniandes.dse.parcialprueba.entities;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "medico")
public class MedicoEntity extends BaseEntity {

    private String nombre;
    private String apellido;
    private String registroMedico;

    @ManyToMany
    @JoinTable(
        name = "medico_especialidad",
        joinColumns = @JoinColumn(name = "medico_id"),
        inverseJoinColumns = @JoinColumn(name = "especialidad_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<EspecialidadEntity> especialidades;
}