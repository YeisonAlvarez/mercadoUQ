package Utilitarios;
import java.util.*;


	
	public class GeneradorAutoincremental {
	    private static int siguienteNumero = 1;
	    private static Set<Integer> numerosUtilizados = new HashSet<>();

	    public static synchronized int generarNumero() {
	        while (numerosUtilizados.contains(siguienteNumero)) {
	            siguienteNumero++;
	        }
	        numerosUtilizados.add(siguienteNumero);
	        return siguienteNumero++;
	    }
	}



