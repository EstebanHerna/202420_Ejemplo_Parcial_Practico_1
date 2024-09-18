package co.edu.uniandes.dse.parcialprueba.services;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MedicoServiceTest {

    @Autowired
    private MedicoService medicoService;

    @SuppressWarnings("unused")
    @Autowired
    private MedicoRepository medicoRepository;

    @Test
    void testCreateMedicoSuccess() throws IllegalOperationException {
        MedicoEntity medico = new MedicoEntity();
        medico.setRegistroMedico("RM1234");

        MedicoEntity result = medicoService.createMedico(medico);
        assertNotNull(result);
        assertEquals("RM1234", result.getRegistroMedico());
    }

    @Test
    void testCreateMedicoWithInvalidRegistro() {
        MedicoEntity medico = new MedicoEntity();
        medico.setRegistroMedico("ABC123");

        IllegalOperationException exception = assertThrows(IllegalOperationException.class, () -> {
            medicoService.createMedico(medico);
        });

        assertEquals("El registro médico debe iniciar con 'RM'.", exception.getMessage());
    }
}
