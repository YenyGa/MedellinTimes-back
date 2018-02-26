package com.medellintimes.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "CATEGORIA")
@ApiModel
public class Categoria {

    @Id
    private String id;
    @NotNull
    private String nombre;
    private String descripcion;
    
    public Categoria(){
        
    }

    public Categoria(String id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	@ApiModelProperty(example = "12345")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ApiModelProperty(example = "Deportes", position = 1)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@ApiModelProperty(example = "Categoria en la que se habla de todo lo relacionado con los deportes en todo el mundo", position = 2)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
}
