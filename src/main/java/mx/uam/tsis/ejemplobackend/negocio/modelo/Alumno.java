package mx.uam.tsis.ejemplobackend.negocio.modelo;

import lombok.Builder;
import lombok.Data;


@Builder
@Data//getter y setters
public class Alumno {
	private Integer matricula;
	
	private String nombre;
	
	private String carrera;
}
