package com.medellintimes.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "PUBLICACION")
@ApiModel
public class Publicacion {

    @Id
    private String id;
    @NotNull
    private String encabezado;
    @NotNull
    private String descripcion;
    @NotNull
    private Date fecha;
    @NotNull
    private String lugar;
    @NotNull
    private String idCategoria;
    private String urlImagen;
    
    public Publicacion(){
        
    }

    public Publicacion(String id, String encabezado, String descripcion, Date fecha, String lugar, String idCategoria,
			String urlImagen) {
		super();
		this.id = id;
		this.encabezado = encabezado;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.lugar = lugar;
		this.idCategoria = idCategoria;
		this.urlImagen = urlImagen;
	}

	@ApiModelProperty(example = "12345")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ApiModelProperty(example = "La amarga despedida de Lindsey Vonn de los Juegos Olímpicos de Invierno", required = true, position = 1)
	public String getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
	}

	@ApiModelProperty(example = "Michelle Gisin se convirtió este jueves en nueva campeona olímpica de combinada de esquí alpino en "
			+ "PyeongChang en la que relegó a plata a la estadounidense Mikaela Shiffrin, cuya compatriota Lindsey Vonn se salió en "
			+ "la segunda manga.", required = true, position = 2)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@ApiModelProperty(example = "2017-07-21", required = true, position = 3)
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@ApiModelProperty(example = "PyeongChang", required = true, position = 4)
	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	@ApiModelProperty(example = "1", required = true, position = 5)
	public String getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	@ApiModelProperty(example = "http://www.lavanguardia.com/r/GODO/LV/p5/WebSite/2018/02/22/Recortada/_20180222091843-kTCH-U44977985538uNF-992x558@LaVanguardia-Web.jpg", position = 6)
	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
    
}
