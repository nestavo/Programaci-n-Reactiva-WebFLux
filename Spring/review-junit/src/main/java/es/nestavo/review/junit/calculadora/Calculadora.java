package es.nestavo.review.junit.calculadora;

public class Calculadora {


     int suma(int a, int b) {
		
    	 return a+b;
	}
     int restar(int a, int b) {
 		
    	 return a-b;
	}
     int multiplicar(int a, int b) {
 		
    	 return a*b;
	}
     int dividir(int a, int b) throws Exception {
    	 
    	 if (b ==0 ) {
    		 throw new Exception("No se puede dividir por cero");
    	 }
 		
    	 return a/b;
	}
}
