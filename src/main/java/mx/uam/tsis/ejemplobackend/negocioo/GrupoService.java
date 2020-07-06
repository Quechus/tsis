package mx.uam.tsis.ejemplobackend.negocioo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.datos.GrupoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Grupo;

@Service
@Slf4j
public class GrupoService {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private AlumnoService alumnoService;
	
	public Grupo create(Grupo nuevo) {
		return grupoRepository.save(nuevo);
	}
	
	public Iterable <Grupo> retrieveAll(){
		return grupoRepository.findAll();
	}
	
	public boolean deleteOne (Integer id) {
		grupoRepository.deleteById(id);
		Optional<Grupo> grupo = grupoRepository.findById(id);
		return grupo.isPresent();
	}
	
	public Grupo updateService(Grupo grupo, Integer Id) {
		Optional<Grupo> upgrupo = grupoRepository.findById(Id);
		if(!upgrupo.isPresent()) {
			Grupo auxGrupo = upgrupo.get();
			return grupoRepository.save(auxGrupo);/*.update(upAlumno, matricula);*/
		}else {
			return null;
		}
	}
	
	public boolean addStudentToGroup(Integer groupId, Integer matricula) {
		
		log.info("Agregando alumno con matricula "+matricula+" al grupo "+groupId);
		Optional<Alumno> alumno = alumnoService.retrieveOne(matricula);
		Optional <Grupo> grupoOpt = grupoRepository.findById(groupId);
		if(!grupoOpt.isPresent() || alumno == null) {
			log.info("No se encontró alumno o grupo");
			return false;
		}
		Grupo grupo = grupoOpt.get();
		grupo.addAlumno(alumno);
		grupoRepository.save(grupo);
		return true;
	}
}
