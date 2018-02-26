package com.medellintimes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medellintimes.entity.Categoria;
import com.medellintimes.repository.CategoriaRepository;

@Service
public class CategoriaService {

    CategoriaRepository repository;
    
    @Autowired
    public CategoriaService(CategoriaRepository repository) {
		this.repository = repository;
	}

	public boolean verificarStrings(String field) {
		return field != null && !"".equals(field);
    }
    
    public Categoria guardarCategoria(Categoria publicacion){
        return repository.save(publicacion);
    }
    
    public Categoria obtenerCategoriaPorId(String id) {
        return repository.findOne(id);
    }
    
    public List<Categoria> obtenerCategoriaPorParametros(String nombre) {
        if (verificarStrings(nombre)) {
            return repository.findByNombreContaining(nombre);
        }
        return repository.findAll();
    }

    public boolean verificarCamposNoNulos(Categoria categoria) {
        return verificarStrings(categoria.getNombre());
    }
    
    public boolean categoriaExiste(String id) {
        return repository.exists(id);
    }
    
    public List<Categoria> obtenerCategorias() {
        return repository.findAll();
    }
    
    public Categoria borrarCategoria(String id) {
    	Categoria categoria = repository.findOne(id);
        repository.delete(categoria);
        return categoria;
    }
}
