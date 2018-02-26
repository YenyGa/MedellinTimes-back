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
import com.medellintimes.entity.Publicidad;
import com.medellintimes.service.PublicidadService;

@Controller
@RequestMapping("/api/v1/publicidad")
public class PublicidadController {

    @Autowired
    PublicidadService service;
    
    @PostMapping(consumes = "Application/json")
    public ResponseEntity<Publicidad> guardarPublicidad(@RequestBody Publicidad publicidad) {
        RequestError requestError;
        if (service.verificarCamposNoNulos(publicidad)) {
            if (!service.publicidadExiste(publicidad.getId())) {
                return new ResponseEntity<Publicidad>(service.guardarPublicidad(publicidad), HttpStatus.OK);
            } else {
                requestError = new RequestError("Mala petición", "La publicidad con el id dado ya existe");
            }
        } else {
            requestError = new RequestError("Mala petición", "Campos obligatorios no enviados");
        }
        return new ResponseEntity(requestError.toString(), HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Publicidad> obtenerPublicidadPorId(@PathVariable String id) {
        if (service.verificarStrings(id)) {
        	Publicidad publicidad = service.obtenerPublicidadPorId(id);
            if (publicidad != null) {
                return new ResponseEntity<Publicidad>(service.obtenerPublicidadPorId(id), HttpStatus.OK);
            }
        }
        return new ResponseEntity(new RequestError("Mala petición", "La publicidad con el id dado dado no existe").toString(), HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping()
    public ResponseEntity<List<Publicidad>> obtenerPublicidad(@RequestParam(name = "empresa", required = false) String empresa) {
        return new ResponseEntity<List<Publicidad>>(service.obtenerPublicidadPorParametros(empresa), HttpStatus.OK);
    }
    
    @PutMapping(consumes = "Application/json")
    public ResponseEntity<Publicidad> actualizarPublicidad(@RequestBody Publicidad publicidadActualizada) {
        RequestError requestError;
        if (service.verificarCamposNoNulos(publicidadActualizada)) {
            if (service.publicidadExiste(publicidadActualizada.getId())) {
                return new ResponseEntity<Publicidad>(service.guardarPublicidad(publicidadActualizada), HttpStatus.OK);
            } else {
                requestError = new RequestError("Mala petición", "La publicidad con el id dado no existe");
            }
        } else {
            requestError = new RequestError("Mala petición", "Campos obligatorios no enviados");
        }
        return new ResponseEntity(requestError.toString(), HttpStatus.BAD_REQUEST);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarPublicidad(@PathVariable String id) {
        if (service.publicidadExiste(id)) {
            return new ResponseEntity<String>(service.borrarPublicidad(id).getId(), HttpStatus.OK);
        }
        return new ResponseEntity(new RequestError("Mala peticion", "La publicidad con el id dado no existe").toString(), 
                HttpStatus.BAD_REQUEST);
    }
}
