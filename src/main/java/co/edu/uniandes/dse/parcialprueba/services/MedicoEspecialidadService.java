package co.edu.uniandes.dse.parcialprueba.services;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicoEspecialidadService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    public MedicoEntity addEspecialidad(Long medicoId, Long especialidadId) throws EntityNotFoundException {
        Optional<MedicoEntity> medicoOpt = medicoRepository.findById(medicoId);
        if (!medicoOpt.isPresent()) {
            throw new EntityNotFoundException("El médico con el id proporcionado no existe.");
        }

        Optional<EspecialidadEntity> especialidadOpt = especialidadRepository.findById(especialidadId);
        if (!especialidadOpt.isPresent()) {
            throw new EntityNotFoundException("La especialidad con el id proporcionado no existe.");
        }

        MedicoEntity medico = medicoOpt.get();
        EspecialidadEntity especialidad = especialidadOpt.get();
        medico.getEspecialidades().add(especialidad);

        return medicoRepository.save(medico);
    }
}
