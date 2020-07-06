package mx.uam.tsis.ejemplobackend.servicios;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.negocioo.GrupoService;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Grupo;

@RestController
@RequestMapping("/v1") // Versionamiento
@Slf4j 
public class GrupoController {
	
	
	@Autowired
	private GrupoService grupoService;
	
	
	@ApiOperation(
			value = "Crear grupo",
			notes = "Permite crear un nuevo grupo"
			) // Documentacion del api
	@PostMapping(path = "/grupos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody @Valid Grupo nuevoGrupo) {
				
		log.info("Recibí llamada a create con "+nuevoGrupo); // Logging
		
		Grupo grupo = grupoService.create(nuevoGrupo);
		
		if(grupo != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(grupo);			
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se puede crear alumno");
		}
	

	}
	///////////////////////////////////////////////////////////////////////////////
	@ApiOperation(
			value = "Obtiene Grupos",
			notes = "Permite obtener los grupos"
			) // Documentacion del api
	@GetMapping(path = "/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieveAll() {
		
		Iterable <Grupo> result = grupoService.retrieveAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(result); 
		
	}
	////////////////////////////////////////////////////////////////////////
	@ApiOperation(
			value = "Agrega un alumno a grupo",
			notes = "Permite agregar un alumno a un grupo"
			) // Documentacion del api
	@PostMapping(path = "/grupos/{id}/alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> addStudentToGroup(
			@PathVariable("id") Integer id,
			@RequestParam("matricula") Integer matricula) {
		
		boolean result = grupoService.addStudentToGroup(id, matricula);
		
		if(result) {
			return ResponseEntity.status(HttpStatus.OK).build(); 
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		}
		
	
	}
	
	/////////////////////////////////////////////////////////////////////////
	//ACTUALIZAR
			@ApiOperation(
					value = "Actualiza grupo",
					notes = "Actualiza un grupo ubicando su id"
					)
			@PutMapping(path = "/grupos/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity <?> update(@RequestBody Grupo nuevoGrupo, @PathVariable("id") Integer id) {//requestbody, lo que viene en la url
				if(grupoService.updateService(nuevoGrupo, id) != null) {
					log.info("Actualize el grupo a " +  nuevoGrupo);
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
			@DeleteMapping(path = "/grupos/{id}")
			public ResponseEntity <?>  delete(@PathVariable("id") Integer id) {
				if(grupoService.deleteOne(id)==false) {
					return ResponseEntity.status(HttpStatus.OK).build();
				} else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
				}
			}

}