package com.medellintimes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.medellintimes.entity.Categoria;
import com.medellintimes.entity.Publicacion;
import com.medellintimes.entity.Publicidad;
import com.medellintimes.repository.CategoriaRepository;
import com.medellintimes.repository.PublicacionRepository;
import com.medellintimes.repository.PublicidadRepository;

import javax.annotation.PostConstruct;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SetUp {
    
    @Autowired
    PublicacionRepository publicacionRepository;
    
    @Autowired
    PublicidadRepository publicidadRepository;
    
    @Autowired
    CategoriaRepository categoriaRepository;

    @PostConstruct
    public void createData() {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
    	Date fecha = new Date();
		try {
			fecha = sdf.parse("22-02-2018 10:20:56");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        publicacionRepository.save(new Publicacion("12345", "Por primera vez, Los Simpsons presentarán al padre de Moe",
        		"El actor Ray Liotta será quien le dé la voz a Morty Szyslak. El personaje aparecerá durante un episodio que emitirá "
        		+ "la cadena Fox que se centrará en la historia de la vida de Moe.", fecha, "EEUU", "1", "images/1.jpg"));
        publicacionRepository.save(new Publicacion("6789", "James Rodríguez, posible canje entre el Real y el Bayern",
        		"El Bayern le propondría al onceno español un canje con Robert Lewandowski, obteniendo así los derechos deportivos del colombiano."
        		+ "Actualmente, James es propiedad del Real Madrid y se encuentra en préstamo en el Bayern.", fecha, "Alemania", "1", "images/2.jpg"));
        
        publicidadRepository.save(new Publicidad("123", "http://www.coca-cola.co.uk/stories/keep-on-trucking", "images/cocacola.jpg", "Coca cola"));
        publicidadRepository.save(new Publicidad("1234", "https://goo.gl/th2Dkq", "images/palmolive.jpg", "Palmolive"));
        
        categoriaRepository.save(new Categoria("1", "Farandula", "Toda la información de los famosos a nivel mundial"));
        categoriaRepository.save(new Categoria("2", "Deportes", "Toda la información de los deportes a nivel mundial"));
    }
}
