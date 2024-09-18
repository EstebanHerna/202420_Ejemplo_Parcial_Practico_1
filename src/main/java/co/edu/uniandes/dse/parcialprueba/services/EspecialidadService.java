package co.edu.uniandes.dse.parcialprueba.services;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    public EspecialidadEntity createEspecialidad(EspecialidadEntity especialidad) throws IllegalOperationException {
        if (especialidad.getDescripcion().length() < 10) {
            throw new IllegalOperationException("La descripción debe tener al menos 10 caracteres");
        }
        return especialidadRepository.save(especialidad);
    }

    public EspecialidadEntity saveEspecialidad(EspecialidadEntity especialidad) {
        return especialidadRepository.save(especialidad);
    }
}
