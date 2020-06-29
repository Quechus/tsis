package mx.uam.tsis.ejemplobackend.negocio.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data//getter y setters
@Entity //Persiste en BD
public class Alumno {
	@NotNull
	@ApiModelProperty(notes = "Matricula del alumno", required = true)
	@Id
	private Integer matricula;
	
	@NotBlank
	@ApiModelProperty(notes = "Nombre del alumno", required = true)
	private String nombre;
	
	@NotBlank
	@ApiModelProperty(notes = "Carrera del alumno", required = true)
	private String carrera;
}
