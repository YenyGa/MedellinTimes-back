package com.medellintimes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.medellintimes.entity.Publicacion;

public interface PublicacionRepository extends CrudRepository<Publicacion, String>{
    
    Publicacion save(Publicacion publicacion);
    
    Publicacion findOne(String id);
    
    List<Publicacion> findAll();
    
    List<Publicacion> findByIdCategoria(String idCategoria);
    
    List<Publicacion> findByLugarContaining(String lugar);
    
 }
