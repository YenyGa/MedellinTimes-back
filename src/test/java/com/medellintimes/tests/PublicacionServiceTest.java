package com.medellintimes.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.medellintimes.entity.Publicacion;
import com.medellintimes.repository.PublicacionRepository;
import com.medellintimes.service.PublicacionService;

@RunWith(MockitoJUnitRunner.class)
public class PublicacionServiceTest {

	private PublicacionService publicacionService;
	
    @Mock
    private PublicacionRepository publicacionRepository;
    
    @Before
    public void init() {
        this.publicacionService = new PublicacionService(publicacionRepository);
    }
    
	@Test
	public void siStringNoEsNuloRetornaTrue() {
		//Arrange
		String palabra = "mi nombre es Ana";
		
		//Act
		boolean resultado = publicacionService.verificarStrings(palabra);
		
		//Assert
		assertTrue(resultado);
	}
	
	@Test
	public void siEncabezadoEsNuloRetornaFalse() throws ParseException {
		//Arrange
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
    	Date fecha = new Date();
		fecha = sdf.parse("22-02-2018 10:20:56");
		Publicacion publicacion = new Publicacion("456", null, "descripcion de prueba", fecha, "Medellin - Colombia", "2", "http://urldeprueba.com.co");
		
		//Act
		boolean resultado = publicacionService.verificarCamposNoNulos(publicacion);
		
		//Assert
		assertFalse(resultado);
	}
	
	@Test
	public void retornaListaPublicacionesSiSeBuscaPorCategoria() throws ParseException {
		//Arrange
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
    	Date fecha = new Date();
		fecha = sdf.parse("22-02-2018 10:20:56");
		String categoria = "2";
		String lugar = "Bogotá";
		Publicacion publicacion = new Publicacion("456", null, "descripcion de prueba", fecha, "Medellin - Colombia", "2", "http://urldeprueba.com.co");
		Publicacion publicacion2 = new Publicacion("234", null, "descripcion de prueba", fecha, "Medellin - Colombia", "2", "http://urldeprueba.com.co");
		List<Publicacion> listaARetornar = new ArrayList<Publicacion>();
		listaARetornar.add(publicacion);
		listaARetornar.add(publicacion2);
		
		//Act
		when(publicacionRepository.findByIdCategoria(categoria)).thenReturn(listaARetornar);
		List<Publicacion> listaRetornada = publicacionService.obtenerPublicacionesPorParametros(categoria, lugar);
		
		//Assert
		assertEquals(listaARetornar, listaRetornada);
	}

}
