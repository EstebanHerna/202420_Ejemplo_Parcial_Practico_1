package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;

@SpringBootTest
@Transactional
public class MedicoEspecialidadServiceTest {

    @Autowired
    private MedicoEspecialidadService medicoEspecialidadService;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Test
    public void testAddEspecialidadCorrectly() throws EntityNotFoundException {
        // Crear y guardar el médico
        final MedicoEntity medico = new MedicoEntity();
        medico.setRegistroMedico("RM1234");
        medicoRepository.save(medico);

        // Crear y guardar la especialidad
        EspecialidadEntity especialidad = new EspecialidadEntity();
        especialidad.setDescripcion("Especialidad válida");
        especialidad = especialidadRepository.save(especialidad);

        // Añadir la especialidad al médico
        MedicoEntity result = medicoEspecialidadService.addEspecialidad(medico.getId(), especialidad.getId());

        assertNotNull(result);
        assertTrue(result.getEspecialidades().contains(especialidad));
    }

    @Test
    public void testAddEspecialidad_MedicoNotFound() {
        // Crear y guardar una especialidad válida
        EspecialidadEntity especialidad = new EspecialidadEntity();
        especialidad.setDescripcion("Especialidad válida");
        EspecialidadEntity savedEspecialidad = especialidadRepository.save(especialidad);

        // Intentar añadir la especialidad a un médico que no existe
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            medicoEspecialidadService.addEspecialidad(999L, savedEspecialidad.getId());
        });

        assertEquals("Médico no encontrado", exception.getMessage());
    }
    
}
