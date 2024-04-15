package vista;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import controlador.Logica;
import modelo.*;

public class MenuPrincipal {

	public static Logica logica;
	public static List<Factura> listaFacturas = new ArrayList<>();
	public static Bodega bodega = null;
	static List<Premio> listPremiosSinEncolar = null;

	public static void main(String[] args) {

		// 1) Inicializar Controlador (Internamente se inicia la conexion con la base de
		// datos)
		logica = new Logica();
		logica.iniciarConexionBD();// Metodo que crea la conexion con mysql

		iniciarMenuPrincipal();
	}

	private static void iniciarMenuPrincipal() {
		// 2)Iniciar Interfaz
		Scanner scanner = new Scanner(System.in);
		int opcion;

		// Bucle del menú principal
		do {
			System.out.println("\n!***Menú Principal Mercado UQ!***");
			System.out.println("1. Gestor de Facturas");
			System.out.println("2. Gestor de Premios");
			System.out.println("3. Gestor de Ruta");
			System.out.println("4. Salir\n");
			System.out.print("Seleccione una opción:");
			opcion = scanner.nextInt();

			switch (opcion) {
			case 1:
				gestionarFacturasMenu();
				break;
			case 2:
				gestionarPremiosMenu();
				break;
			case 3:
				gestionarRutaMenu();
				break;
			case 4:
				System.out.println("Saliendo del programa...");
				logica.cerrarConexion();
				break;
			default:
				System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
				break;
			}
		} while (opcion != 4);

		scanner.close();

	}

	// Método para el gestor de facturas
	public static void gestionarFacturasMenu() {

		Scanner scanner = new Scanner(System.in);
		int opcion;
		String nroIdentificacionCliente;

		// Bucle del menú principal de facturas
		do {
			System.out.println("\n!***Gestor de Facturas**!***");
			System.out.println("1. Listar todas las facturas");
			System.out.println("2. Listar facturas por cliente");
			System.out.println("3. Salir\n");
			System.out.print("Seleccione una opción:");
			opcion = scanner.nextInt();

			switch (opcion) {
			case 1:
				listaFacturas = logica.gestionarFacturas(1, null); // Se le envia la opcion y null por que no hay filtro
																	// de cedula

				// Se listan las facturas
				for (int i = 0; i < listaFacturas.size(); i++) {

					System.out.println(listaFacturas.get(i).toString());
				}
				iniciarMenuPrincipal();

				break;
			case 2:
				System.out.print("Ingrese el número de identificación del cliente: "); // Se captura la cedula
				nroIdentificacionCliente = scanner.nextInt() + "";
				listaFacturas = logica.gestionarFacturas(2, nroIdentificacionCliente); // Se le envia parametro opcion y
																						// el numero de
				// cedula
				// Se listan las facturas
				for (Factura factura : listaFacturas) {
					System.out.println(factura.toString());
				}

				iniciarMenuPrincipal();

				break;
			case 3:
				System.out.println("Saliendo del programa...");
				logica.cerrarConexion();
				break;
			default:
				System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
				break;
			}
		} while (opcion != 3);

		scanner.close();

	}

	// Método para el gestor de premios
	public static void gestionarPremiosMenu() {

		Scanner scanner = new Scanner(System.in);
		int opcion;

		System.out.println("\n!***Gestor de Premios**!");
		System.out.println("1. Revisar Bodega de obsequios");
		System.out.println("2. Premiar clientes");
		System.out.println("3. Encolar premios para ser entregados");
		System.out.println("4. Generar carga de avion con entregas");
		System.out.println("5. Salir\n");
		System.out.print("Seleccione una opción:");
		opcion = scanner.nextInt();

		switch (opcion) {
		case 1:

			ArrayList<Stack<Producto>> colaBodega = logica.getBodega(); // Revisar Bodega (Carga las pilas)
			imprimirColaBodega(colaBodega); //

			break;
		case 2:

			listPremiosSinEncolar = logica.gestionarPremios(); // Premiar clientes

			break;
		case 3:

			logica.encolarPremios(listPremiosSinEncolar); // Encolar premios

		case 4:

			logica.cargarAvion(); // Generar carga de avion

		case 5:
			System.out.println("Saliendo del programa...");
			logica.cerrarConexion();
			break;

		default:
			System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
			break;
		}

	}

	// Método para el gestor de ruta
	public static void gestionarRutaMenu() {
		System.out.println("!***Gestor de Ruta**!");
		System.out.println("1. Generar ruta para el avión");
		System.out.println("2. Generar información de ruta para aeropuertos");

	}

	public static void imprimirColaBodega(ArrayList<Stack<Producto>> cola) {

		System.out.println("\n<---------------------Bodega de Obsequios---------------------->");
		int indiceNumPila = 1;
		for (Stack<Producto> pila : cola) {

			System.out.println("Pila: # " + indiceNumPila);

			indiceNumPila++;

			// Imprimir productos en la pila
			for (Producto producto : pila) {
				System.out.println("  --->" + producto.getNombreProducto() + "---> Categoria: "
						+ producto.getCategoria().getNombreCategoria());
			}
			System.out.println("\n");
		}
		System.out.println("\n<---------------------------------------------------------------->");
	}
}