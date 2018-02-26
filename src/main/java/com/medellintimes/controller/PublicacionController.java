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
import com.medellintimes.entity.Publicacion;
import com.medellintimes.service.PublicacionService;

@Controller
@RequestMapping("/api/v1/publicaciones")
public class PublicacionController {

    @Autowired
    PublicacionService service;
    
    @PostMapping(consumes = "Application/json")
    public ResponseEntity<Publicacion> guardarPublicacion(@RequestBody Publicacion publicacion) {
        RequestError requestError;
        if (service.verificarCamposNoNulos(publicacion)) {
            if (!service.publicacionExiste(publicacion.getId())) {
                return new ResponseEntity<Publicacion>(service.guardarPublicacion(publicacion), HttpStatus.OK);
            } else {
                requestError = new RequestError("Mala petición", "La publicacion con el id dado ya existe");
            }
        } else {
            requestError = new RequestError("Mala petición", "Campos obligatorios no enviados");
        }
        return new ResponseEntity(requestError.toString(), HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Publicacion> obtenerPublicacionPorId(@PathVariable String id) {
        if (service.verificarStrings(id)) {
            Publicacion publicacion = service.obtenerPublicacionPorId(id);
            if (publicacion != null) {
                return new ResponseEntity<Publicacion>(service.obtenerPublicacionPorId(id), HttpStatus.OK);
            }
        }
        return new ResponseEntity(new RequestError("Mala petición", "La publicacion con el id dado dado no existe").toString(), HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping()
    public ResponseEntity<List<Publicacion>> obtenerPublicaciones(@RequestParam(name = "categoria", required = false) String categoria,
    													@RequestParam(name = "lugar", required = false) String lugar) {
        return new ResponseEntity<List<Publicacion>>(service.obtenerPublicacionesPorParametros(categoria, lugar), HttpStatus.OK);
    }
    
    @PutMapping(consumes = "Application/json")
    public ResponseEntity<Publicacion> actualizarPublicacion(@RequestBody Publicacion publicacionActualizada) {
        RequestError requestError;
        if (service.verificarCamposNoNulos(publicacionActualizada)) {
            if (service.publicacionExiste(publicacionActualizada.getId())) {
                return new ResponseEntity<Publicacion>(service.guardarPublicacion(publicacionActualizada), HttpStatus.OK);
            } else {
                requestError = new RequestError("Mala petición", "La publicacion con el id dado no existe");
            }
        } else {
            requestError = new RequestError("Mala petición", "Campos obligatorios no enviados");
        }
        return new ResponseEntity(requestError.toString(), HttpStatus.BAD_REQUEST);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarPublicacion(@PathVariable String id) {
        if (service.publicacionExiste(id)) {
            return new ResponseEntity<String>(service.borrarPublicacion(id).getId(), HttpStatus.OK);
        }
        return new ResponseEntity(new RequestError("Mala peticion", "La publicacion con el id dado no existe").toString(), 
                HttpStatus.BAD_REQUEST);
    }
}
