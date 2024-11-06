package es.nestavo.review.junit.calculadora;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculadoraTest {
	Calculadora calc;

	@BeforeAll
	public static void primero() {
		System.out.println("Se ejecuta al PRINCIPIO de todas las pruebas unitaria una vez");
	}

	@AfterAll
	public static void ultimo() {
		System.out.println("Se ejecuta al FINAL de todas las pruebas unitaria una vez");
	}

	@BeforeEach
	public void instanciaObjeto() {
		calc = new Calculadora();
		System.out.println("Se ejecuta al principio de cada prueba unitaria");
	}

	@AfterEach
	public void despuesDelTest() {
		System.out.println("SE ejecuta al final de cada prueba unitaria");
	}

	@Test
	@DisplayName("Test para el metodo SUMAR de la calculadora")
	public void sumarTest() {

		assertEquals(4, calc.suma(2, 2));

		assertNotEquals(5, calc.suma(2, 2));
	}

	@Test
	@DisplayName("Test para el metodo DIVIDIR de la calculadora")
	// @Disabled("esta prueba esta deshabilitada por el momento")
	public void dividirTest() throws Exception {

		assertTrue(calc.dividir(10, 2) == 5);
		assertFalse(calc.dividir(9, 3) == 2);
	}
	
	@Test
	public void arregloTest() {
		String [] arr1 = {"a", "b"};
		String [] arr2 = {"a", "b"};
		String [] arr3 = {"a", "b", "c"};
		
		assertArrayEquals(arr1, arr2);
	}
	@Test
	public void sameAndNotSameTest() {
		
		String str1= "mi cadena";
		String str2= str1;
		String str3= "mi segunda cadena";
		
		assertSame(str2, str1);
		assertNotSame(str1, str3);
	}
	@Test
	@DisplayName("Test probar excepeciones")
	public void dividirExceptionTest() {
		
		assertThrows(Exception.class, ()-> {calc.dividir(10, 0);});
		assertDoesNotThrow(() -> {calc.dividir(10, 2);});
	
	}
}
