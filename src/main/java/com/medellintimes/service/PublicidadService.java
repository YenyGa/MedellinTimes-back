package com.medellintimes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medellintimes.entity.Publicidad;
import com.medellintimes.repository.PublicidadRepository;

@Service
public class PublicidadService {

    PublicidadRepository repository;
    
    @Autowired
    public PublicidadService(PublicidadRepository repository) {
		super();
		this.repository = repository;
	}

	public boolean verificarStrings(String field) {
		return field != null && !"".equals(field);
    }
    
    public Publicidad guardarPublicidad(Publicidad publicidad){
        return repository.save(publicidad);
    }
    
    public Publicidad obtenerPublicidadPorId(String id) {
        return repository.findOne(id);
    }
    
    public List<Publicidad> obtenerPublicidadPorParametros(String empresa) {
        if (verificarStrings(empresa)) {
            return repository.findByEmpresaContaining(empresa);
        }
        return repository.findAll();
    }

    public boolean verificarCamposNoNulos(Publicidad publicidad) {
        return verificarStrings(publicidad.getEmpresa())
                && verificarStrings(publicidad.getUrlImagen());
    }
    
    public boolean publicidadExiste(String id) {
        return repository.exists(id);
    }
    
    public List<Publicidad> obtenerPublicidad() {
        return repository.findAll();
    }
    
    public Publicidad borrarPublicidad(String id) {
    	Publicidad publicidad = repository.findOne(id);
        repository.delete(publicidad);
        return publicidad;
    }
}
