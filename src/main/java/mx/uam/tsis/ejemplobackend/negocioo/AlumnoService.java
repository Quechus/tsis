package mx.uam.tsis.ejemplobackend.negocioo;

import java.util.List;

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
		Alumno alumno = alumnoRepository.findByMatricula(nuevoAlumno.getMatricula());
		if(alumno == null) {
			return alumnoRepository.save(nuevoAlumno);
		} else {
			return null;
		}
	}
	
	/**
	 *
	 * @param
	 * @return
	 */
	public Alumno updateService(Alumno upAlumno, Integer matricula) {
		Alumno alumno = alumnoRepository.findByMatricula(matricula);
		if(alumno != null) {
			return alumnoRepository.update(upAlumno, matricula);
		}else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param
	 * @return
	 */
	public List <Alumno> retrieveAll () {
		return alumnoRepository.find();
	}
	
	/**
	 *
	 * @param
	 * @return
	 */
	public Alumno retrieveOne (Integer matricula) {
		return alumnoRepository.findByMatricula(matricula);
	}
	
	/**
	 *
	 * @param
	 * @return
	 */
	public Alumno deleteOne (Integer matricula) {
		alumnoRepository.delete(matricula);
		Alumno alumno = alumnoRepository.findByMatricula(matricula);
		return alumno;
	}
}
