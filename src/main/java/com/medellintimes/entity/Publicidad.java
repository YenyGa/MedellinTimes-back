package com.medellintimes.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "PUBLICIDAD")
@ApiModel
public class Publicidad {

    @Id
    private String id;
    @NotNull
    private String link;
    @NotNull
    private String empresa;
    private String urlImagen;
    
    public Publicidad(){
        
    }

    public Publicidad(String id, String link, String urlImagen, String empresa) {
		super();
		this.id = id;
		this.link = link;
		this.urlImagen = urlImagen;
		this.empresa = empresa;
	}

	@ApiModelProperty(example = "12345")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ApiModelProperty(example = "http://www.lavanguardia.com/r/GODO/LV/p5/WebSite/2018/02/22/Recortada/_20180222091843-kTCH-U44977985538uNF-992x558@LaVanguardia-Web.jpg", position = 1)
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@ApiModelProperty(example = "http://www.lavanguardia.com/r/GODO/LV/p5/WebSite/2018/02/22/Recortada/_20180222091843-kTCH-U44977985538uNF-992x558@LaVanguardia-Web.jpg", position = 2)
	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	@ApiModelProperty(example = "Gatorade")
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
    
}
