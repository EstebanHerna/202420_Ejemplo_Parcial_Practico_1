package co.edu.uniandes.dse.parcialprueba.entities;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "especialidad")
public class EspecialidadEntity extends BaseEntity {

    private String nombre;
    private String descripcion;

    @ManyToMany(mappedBy = "especialidades")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<MedicoEntity> medicos;
}