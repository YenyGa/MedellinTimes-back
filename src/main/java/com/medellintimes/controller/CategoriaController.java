package com.medellintimes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.medellintimes.config.RequestError;
import com.medellintimes.entity.Categoria;
import com.medellintimes.service.CategoriaService;

@Controller
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

    @Autowired
    CategoriaService service;
    
    @PostMapping(consumes = "Application/json")
    public ResponseEntity<Categoria> guardarCategoria(@RequestBody Categoria categoria) {
        RequestError requestError;
        if (service.verificarCamposNoNulos(categoria)) {
            if (!service.categoriaExiste(categoria.getId())) {
                return new ResponseEntity<Categoria>(service.guardarCategoria(categoria), HttpStatus.OK);
            } else {
                requestError = new RequestError("Mala petición", "La categoria con el id dado ya existe");
            }
        } else {
            requestError = new RequestError("Mala petición", "Campos obligatorios no enviados");
        }
        return new ResponseEntity(requestError.toString(), HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable String id) {
        if (service.verificarStrings(id)) {
        	Categoria categoria = service.obtenerCategoriaPorId(id);
            if (categoria != null) {
                return new ResponseEntity<Categoria>(service.obtenerCategoriaPorId(id), HttpStatus.OK);
            }
        }
        return new ResponseEntity(new RequestError("Mala petición", "La categoria con el id dado dado no existe").toString(), HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping()
    public ResponseEntity<List<Categoria>> obtenerPublicaciones(@RequestParam(name = "categoria", required = false) String nombre) {
        return new ResponseEntity<List<Categoria>>(service.obtenerCategoriaPorParametros(nombre), HttpStatus.OK);
    }
    
    @PutMapping(consumes = "Application/json")
    public ResponseEntity<Categoria> actualizarCategoria(@RequestBody Categoria categoriaActualizada) {
        RequestError requestError;
        if (service.verificarCamposNoNulos(categoriaActualizada)) {
            if (service.categoriaExiste(categoriaActualizada.getId())) {
                return new ResponseEntity<Categoria>(service.guardarCategoria(categoriaActualizada), HttpStatus.OK);
            } else {
                requestError = new RequestError("Mala petición", "La categoria con el id dado no existe");
            }
        } else {
            requestError = new RequestError("Mala petición", "Campos obligatorios no enviados");
        }
        return new ResponseEntity(requestError.toString(), HttpStatus.BAD_REQUEST);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarPublicacion(@PathVariable String id) {
        if (service.categoriaExiste(id)) {
            return new ResponseEntity<String>(service.borrarCategoria(id).getId(), HttpStatus.OK);
        }
        return new ResponseEntity(new RequestError("Mala peticion", "La categoria con el id dado no existe").toString(), 
                HttpStatus.BAD_REQUEST);
    }
}
