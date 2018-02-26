package com.medellintimes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.medellintimes.entity.Publicidad;

public interface PublicidadRepository extends CrudRepository<Publicidad, String>{
    
	Publicidad save(Publicidad publicidad);
    
	Publicidad findOne(String id);
    
    List<Publicidad> findAll();
    
    List<Publicidad> findByEmpresaContaining(String empresa);
    
 }
