package com.medellintimes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medellintimes.entity.Publicacion;
import com.medellintimes.repository.PublicacionRepository;

@Service
public class PublicacionService {

    PublicacionRepository repository;
    
    @Autowired
    public PublicacionService(PublicacionRepository repository) {
		this.repository = repository;
	}

	public boolean verificarStrings(String field) {
        return field != null && !"".equals(field);
    }
    
    public Publicacion guardarPublicacion(Publicacion publicacion){
        return repository.save(publicacion);
    }
    
    public Publicacion obtenerPublicacionPorId(String id) {
        return repository.findOne(id);
    }
    
    public List<Publicacion> obtenerPublicacionesPorParametros(String categoria, String lugar) {
        if (verificarStrings(categoria)) {
            return repository.findByIdCategoria(categoria);
        } else {
            if (verificarStrings(lugar)) {
                return repository.findByLugarContaining(lugar);
            }
        }
        return repository.findAll();
    }

    public boolean verificarCamposNoNulos(Publicacion publicacion) {
        return verificarStrings(publicacion.getEncabezado())
                && verificarStrings(publicacion.getDescripcion())
                && verificarStrings(publicacion.getLugar())
                && verificarStrings(publicacion.getIdCategoria())
                && publicacion.getFecha() != null;
    }
    
    public boolean publicacionExiste(String id) {
        return repository.exists(id);
    }
    
    public List<Publicacion> obtenerPublicaciones() {
        return repository.findAll();
    }
    
    public Publicacion borrarPublicacion(String id) {
    	Publicacion publicacion = repository.findOne(id);
        repository.delete(publicacion);
        return publicacion;
    }
    
}
