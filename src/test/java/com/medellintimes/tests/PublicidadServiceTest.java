package com.medellintimes.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.medellintimes.repository.PublicidadRepository;
import com.medellintimes.service.PublicidadService;

public class PublicidadServiceTest {

	private PublicidadService publicidadService;
	
    @Mock
    private PublicidadRepository publicidadRepository;
    
    @Before
    public void init() {
        this.publicidadService = new PublicidadService(publicidadRepository);
    }
    
	@Test
	public void siStringEstaVacioRetornaFalse() {
		//Arrange
		String palabra = "";
		
		//Act
		boolean resultado = publicidadService.verificarStrings(palabra);
		
		//Assert
		assertFalse(resultado);
	}
	
	@Test
	public void siStringEsNuloRetornaFalse() {
		//Arrange
		String palabra = null;
		
		//Act
		boolean resultado = publicidadService.verificarStrings(palabra);
		
		//Assert
		assertFalse(resultado);
	}

}
