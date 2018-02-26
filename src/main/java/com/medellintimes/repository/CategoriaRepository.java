package com.medellintimes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.medellintimes.entity.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, String>{
    
	Categoria save(Categoria categoria);
    
	Categoria findOne(String id);
    
    List<Categoria> findAll();
    
    List<Categoria> findByNombreContaining(String nombre);

 }
