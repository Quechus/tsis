package mx.uam.tsis.ejemplobackend.negocioo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.tsis.ejemplobackend.datos.AlumnoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;

@Service
public class AlumnoService {

	@Autowired
	private AlumnoRepository alumnoRepository;
	
	/**
	 * 
	 * @param nuevoAlumno
	 * @return el alumno que se acaba de crear si la creacion es exitosa, null de lo contrario
	 */
	public Alumno create(Alumno nuevoAlumno) {
		// Regla de negocio: No se puede crear más de un alumno con la misma matricula
		Optional <Alumno> alumnoOpt = alumnoRepository.findById(nuevoAlumno.getMatricula());
		if(!alumnoOpt.isPresent()) {
			return alumnoRepository.save(nuevoAlumno);
		}else {
			return null;
		}
	}
	
	/**
	 *
	 * @param
	 * @return
	 */
	public Alumno updateService(Alumno upAlumno, Integer matricula) {
		Optional<Alumno> alumno = alumnoRepository.findById(matricula);
		if(!alumno.isPresent()) {
			return alumnoRepository.save(upAlumno);/*.update(upAlumno, matricula);*/
		}else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param
	 * @return
	 */
	public Iterable <Alumno> retrieveAll () {
		return alumnoRepository.findAll();
	}
	
	/**
	 *
	 * @param
	 * @return
	 */
	public Optional<Alumno> retrieveOne (Integer matricula) {
		return alumnoRepository.findById(matricula);
	}
	
	/**
	 *
	 * @param
	 * @return
	 */
	public boolean deleteOne (Integer matricula) {
		alumnoRepository.deleteById(matricula);
		Optional<Alumno> alumno = alumnoRepository.findById(matricula);
		return alumno.isPresent();
	}
}
