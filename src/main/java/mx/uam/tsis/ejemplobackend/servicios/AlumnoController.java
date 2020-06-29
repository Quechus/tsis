package mx.uam.tsis.ejemplobackend.servicios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
import mx.uam.tsis.ejemplobackend.negocioo.AlumnoService;

/**
 * Controlador para el API rest
 * 
 * @author Carlos Jesus Morales Ocaranza
 *
 */
@RestController
@Slf4j
public class AlumnoController {
	
	// La "base de datos"
	//private Map <Integer, Alumno> alumnoRepository = new HashMap <>();
	@Autowired
	private AlumnoService alumnoService;
	
	//AGREGAR
	@ApiOperation(
			value = "Crear alumno",
			notes = "Permite la creación de un nuevo alumno, la matricula debe ser única"
			)
	@PostMapping(path = "/alumnos", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody Alumno nuevoAlumno) {
		
		// No se deben agregar dos alumnos con la misma matricula
		
		log.info("Recibí llamada a create con "+nuevoAlumno);
		
		//alumnoRepository.put(nuevoAlumno.getMatricula(), nuevoAlumno);
		Alumno alumno = alumnoService.create(nuevoAlumno);
		
		if(alumno != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(alumno);			
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se puede crear alumno");
		}
	}
	
	
	//GET TODOS
	@ApiOperation(
			value = "Obtiene alumnos",
			notes = "Obtiene todos los alumnos que se encuentran guardados"
			)
	@GetMapping(path = "/alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieveAll() {
		Iterable <Alumno> result = alumnoService.retrieveAll();
		return ResponseEntity.status(HttpStatus.OK).body(result);
		
	}
 
	
	//BUSCAR SOLO 1
	@ApiOperation(
			value = "Obtiene un alumno",
			notes = "Obtiene un solo alumno, basandose en su matricula"
			)
	@GetMapping(path = "/alumnos/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieve(@PathVariable("matricula") Integer matricula) {//path variable, variable que viene en la ruta
		log.info("Buscando al alumno con matricula "+matricula);
		
		//Alumno alumno = alumnoRepository.get(matricula);
		Optional<Alumno> alumno = alumnoService.retrieveOne(matricula);
		
		if(alumno != null) {
			return ResponseEntity.status(HttpStatus.OK).body(alumno);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	
	//ACTUALIZAR
		@ApiOperation(
				value = "Actualiza alumno",
				notes = "Actualiza un alumno ubicando su matricula"
				)
		@PutMapping(path = "/alumnos/{matricula}", consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity <?> update(@RequestBody Alumno nuevoAlumno, @PathVariable("matricula") Integer matricula) {//requestbody, lo que viene en la url
			if(alumnoService.updateService(nuevoAlumno, matricula) != null) {
				log.info("Actualize al alumno a " +  nuevoAlumno);
				return ResponseEntity.status(HttpStatus.OK).build();
			}else {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
				}
		}
	
		
	//ELIMINAR
		@ApiOperation(
				value = "Elimina alumno",
				notes = "Elimina un alumno con base a su matricula"
				)
		@DeleteMapping(path = "/alumnos/{matricula}")
		public ResponseEntity <?>  delete(@PathVariable("matricula") Integer matricula) {
			//Alumno alumno = alumnoRepository.get(matricula);
			if(alumnoService.deleteOne(matricula)==false) {
				return ResponseEntity.status(HttpStatus.OK).build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}
	
	//MAPPING LO QUE HACE ES UBICAR TU URL PARA QUE, EN BASE A ELLO, REALIZE LA SIGUIENTE FUNCION
 
}
