package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;

@SpringBootTest
@Transactional
public class EspecialidadServiceTest {

    @Autowired
    private EspecialidadService especialidadService;

    @SuppressWarnings("unused")
    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Test
    public void testCreateEspecialidad_Correct() throws IllegalOperationException {
        EspecialidadEntity especialidad = new EspecialidadEntity();
        especialidad.setDescripcion("Descripción válida de especialidad");

        EspecialidadEntity result = especialidadService.createEspecialidad(especialidad);

        assertNotNull(result);
        assertEquals("Descripción válida de especialidad", result.getDescripcion());
    }

    @Test
    public void testCreateEspecialidad_Exception() {
        EspecialidadEntity especialidad = new EspecialidadEntity();
        especialidad.setDescripcion("Corta");

        IllegalOperationException exception = assertThrows(IllegalOperationException.class, () -> {
            especialidadService.createEspecialidad(especialidad);
        });

        assertEquals("La descripción debe tener al menos 10 caracteres.", exception.getMessage());
    }
    
}
