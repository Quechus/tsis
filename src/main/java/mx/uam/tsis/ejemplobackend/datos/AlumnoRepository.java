package mx.uam.tsis.ejemplobackend.datos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
import mx.uam.tsis.ejemplobackend.servicios.AlumnoController;

@Component
@Slf4j
public class AlumnoRepository {
	private Map <Integer, Alumno> alumnoRepository = new HashMap <>();
	
	public Alumno save(Alumno nuevoAlumno) {
		alumnoRepository.put(nuevoAlumno.getMatricula(), nuevoAlumno);
		return nuevoAlumno;
	}
	
	public Alumno findByMatricula(Integer matricula) {
		return alumnoRepository.get(matricula);
	}
	
	public List <Alumno> find() {
		return new ArrayList <> (alumnoRepository.values());
	}
	
	// update
	public Alumno update(Alumno upAlumno, Integer matricula) {
		return alumnoRepository.replace(matricula, upAlumno);
	}
	// delete
	public void delete(Integer matricula) {
		alumnoRepository.remove(matricula);
	}
	
}
