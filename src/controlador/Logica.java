package controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import Utilitarios.GeneradorAutoincremental;
import conexion.ConexionBD;
import modelo.*;
import modeloDAO.*;

public class Logica {

	public static ConexionBD conexion;
	public static Connection conexionExitosa;
	public List<Factura> listaFacturas = null;
	public List<Premio> listaPremios = null;
	public List<Categoria> listaCategorias = null;
	public List<Cliente> listaClientes = null;
	public List<Producto> listaProductos = null;
	Bodega bodega = null;
	GeneradorAutoincremental utilitario = new GeneradorAutoincremental();

	// Establecer Conexion con la base de datos
	public void iniciarConexionBD() {
		conexion = new ConexionBD();
		conexionExitosa = conexion.getConnection(); // Asi se conecta a la base de datos
	}

	// Cerra la Conexion con la base de datos
	public void cerrarConexion() {
		conexion.close();
	}

	// Metodos logica del programa se cumplen los requisitos funcionales
	public List<Factura> gestionarFacturas(int opcion, String nroIdenCliente) {

		FacturaDAO factura = new FacturaDAO();

		// Listar todas las facturas
		if (opcion == 1 && nroIdenCliente == null) {

			listaFacturas = factura.listarFacturas(conexionExitosa);

		}
		// Listar todas las facturas de un cliente especifico
		if (opcion == 2 && (nroIdenCliente != null)) {

			listaFacturas = factura.listarFacturasPorCliente(nroIdenCliente, conexionExitosa);
		}

		return listaFacturas;

	}

	public List<Premio> gestionarPremios() {

		List<Premio> listaPremiosFinales = new ArrayList<>();

		/** Aqui se aplican las reglas para premiar **/

		// 1)Se seleccionan todos los clientes a evaluar
		ClienteDAO clienteDAO = new ClienteDAO();
		listaClientes = clienteDAO.getListaClientes(conexionExitosa);

		System.out.println("\n<---------------------Premios sin encolar para entrega---------------------->");

		for (Cliente cliente : listaClientes) {

			// 1)Lista de premios (Sin encolar)
			listaPremios = getListaPremios(cliente, conexionExitosa);

			for (Premio premio : listaPremios) {
				if (premio == null) {
					System.out.println("Cliente:" + cliente.getNombreCompleto() + " CC:" + cliente.getCedulaCliente()
							+ " No Aplica para premios\n");
				} else {
					if (premio.getProducto() == null) {
						System.out.println("Cliente:" + cliente.getNombreCompleto() + " CC:"
								+ cliente.getCedulaCliente()
								+ " Si Aplica para premio, Pero no hay stock de la categoria mas repetida en sus facturas\n");
					} else {
						System.out.println(premio.toString());
					}

				}

			}
			listaPremiosFinales.addAll(listaPremios);

		}
		System.out.println("\n<-------------------------------------------------------------------------->");

		return listaPremiosFinales;
	}

	// REGLAS PARA PREMIAR
	public List<Premio> getListaPremios(Cliente cliente, Connection conexionExitosa) {

		List<Premio> listaPremios = new ArrayList<>();
		List<Factura> listaFacturasdelCli = gestionarFacturas(2, cliente.getCedulaCliente()); // Listar facturas por
																								// Cliente
		List<Producto> listaProductosFactCli = new ArrayList<>();
		double sumFacturasMenorMillon = 0.0;
		int contadorPremiosMenorMillon = 0;

		for (int i = 0; i < listaFacturasdelCli.size(); i++) {

			Factura factura = listaFacturasdelCli.get(i);
			String diaFactura = factura.obtenerDiaFactura(factura.fechaFactura);

			/**
			 * -----------------------------------REGLAS PARA PREMIAR-----------------------
			 */

			// No se tienen facturas en cuenta de los días Lunes a Miércoles
			if (diaFactura.equals("Lunes") || diaFactura.equals("Martes") || diaFactura.equals("Miercoles")) {

				listaFacturasdelCli.remove(i);
				i--;
			} else {

				for (Producto producto : factura.getListaProductos()) {

					// No se tienen en cuenta facturas de alimentos
					if (producto.getCategoria().nombreCategoria.equals("ALIMENTOS")) {

						listaFacturasdelCli.remove(i);
						i--;
						break;

					}
				}

				// Si la factura no se removio sigue evaluando para premio
				if (listaFacturasdelCli.contains(factura)) {
					/** Aqui se empienzan a premiar a los Clientes **/

					if (factura.totalFactura > 1000000) {

						Premio premio = null;
						premio = premiarCliente(cliente, conexionExitosa);

						listaPremios.add(premio);

						// Ya no se tiene en cuenta para le criterio de sumatoria de 1millon
						listaFacturasdelCli.remove(i);

					}
				}

			}

			// Si la factura no se removio sigue evaluando para premio
			if (listaFacturasdelCli.contains(factura)) {
				// Se suman los montos de las facturas restantes
				sumFacturasMenorMillon += factura.getTotalFactura();

				if (sumFacturasMenorMillon >= 1000000) {

					sumFacturasMenorMillon = 0;// Se reinicia sumatoria de facturas
					contadorPremiosMenorMillon++; // Va sumando cuantos premios se le van a dar
				}
			}

		}

		// Crea los premios para el cliente que cumplieron con sumatoria x700

		for (int i = 0; i < contadorPremiosMenorMillon; i++) {
			Premio premio = null;
			premio = premiarCliente(cliente, conexionExitosa); // Aqui adentro se valida
																// la categoria que mas
																// se repite

			listaPremios.add(premio);

		}

		return listaPremios;
	}

	public Premio premiarCliente(Cliente cliente, Connection conexionExitosa) {

		Premio premio = new Premio();
		PremioDAO premioDAO = new PremioDAO();

		List<Factura> listFacturasElegidas = premioDAO.elegirFacturasParaObsequio(cliente.idCliente, conexionExitosa);

		Categoria categoriaMasRepet = obtenerCategoriaMasRepeFactu(listFacturasElegidas);
		premio.setCliente(cliente);
		premio.setEstadoPremio(1);
		premio.setIdPremio(utilitario.generarNumero());

		// Obtener el producto de la Bodega
		if (this.bodega == null) {

			getBodega();
		}

		Producto productoObsequio = this.bodega.seleccionarObsequio(categoriaMasRepet);
		premio.setProducto(productoObsequio);

		return premio;
	}

	private Categoria obtenerCategoriaMasRepeFactu(List<Factura> listFacturasElegidas) {
		// Mapa para almacenar la frecuencia de cada categoría
		Map<Categoria, Integer> frecuenciaCategorias = new HashMap<>();

		// Calcular la frecuencia de cada categoría en las facturas
		for (Factura factura : listFacturasElegidas) {
			for (Producto producto : factura.getListaProductos()) {
				Categoria categoria = producto.getCategoria();
				frecuenciaCategorias.put(categoria, frecuenciaCategorias.getOrDefault(categoria, 0) + 1);
			}
		}

		// Encontrar la categoría con la frecuencia más alta
		Categoria categoriaMasRepetida = null;
		int maxFrecuencia = 0;
		for (Map.Entry<Categoria, Integer> entry : frecuenciaCategorias.entrySet()) {
			if (entry.getValue() > maxFrecuencia) {
				categoriaMasRepetida = entry.getKey();
				maxFrecuencia = entry.getValue();
			}
		}

		return categoriaMasRepetida;
	}

	public void gestionarRuta() {

	}

	public void encolarPremios(List<Premio> listaPremiosSinEncolar) {

		// Se tiene que revisar las reglas de priorizacion de los clientes de dichos
		// premios
		for (int i = 0; i < listaPremiosSinEncolar.size(); i++) {
			Cliente clientePriorizado = evaluarPrioridadClientes(listaPremiosSinEncolar.get(i).getCliente());
			clientePriorizado.setSecuenciaPrioridad(i);
			listaPremiosSinEncolar.get(i).setCliente(clientePriorizado);

		}
		// Crear una cola de prioridad para encolar las entregas de premios
		PriorityQueue<Entrega> colaPrioridadEntregas = new PriorityQueue<>();

		// Encolar las entregas de premios
		for (Premio premio : listaPremiosSinEncolar) {

			if (premio.getProducto() != null) { // Solo entregara premios que ya tengan productos
				Entrega entrega = new Entrega(premio, premio.getCliente());
				colaPrioridadEntregas.offer(entrega);
			}

		}

		// Imprimir la cola de prioridad de entregas en consola
		imprimirColaPrioridadEntregas(colaPrioridadEntregas);

	}

	// Se aplican las 3 reglas de priorizar
	private Cliente evaluarPrioridadClientes(Cliente cliente) {

		// Prioridad de genero y edad
		if (cliente.getEdad() > 60) {
			cliente.setPrioridad(1);
		} else {
			if (cliente.getGenero() == 0) {// es mujer
				cliente.setPrioridad(2);
			} else {// es hombre
				cliente.setPrioridad(3);
			}
		}

		// Prioridad de pais
		if (cliente.getPais().getTipo() == 0) {// País en conflicto de la base datos
			cliente.setPaisPrioridad(1);
		} else {
			if (cliente.getGenero() == 1) {// =País en calamidad
				cliente.setPaisPrioridad(2);
			} else {// Otros países
				cliente.setPaisPrioridad(3);
			}
		}

		return cliente;
	}

	// Método para imprimir la cola de prioridad de entregas en consola
	private static void imprimirColaPrioridadEntregas(PriorityQueue<Entrega> colaPrioridadEntregas) {
		System.out.println("\n<---------------------Cola de Prioridad Entregas---------------------->");
		System.out.println("Cola de Prioridad de Entregas:");

		while (!colaPrioridadEntregas.isEmpty()) {
			System.out.println(colaPrioridadEntregas.poll());
		}

		System.out.println("\n<-------------------------------------------------------------------->");
	}

	public void cargarAvion() {
		// TODO Auto-generated method stub

	}

	public List<Premio> getListaPremiosSinEncolar() {
		List<Premio> premios = null;
		if (this.getListaPremios() == null) {
			premios = gestionarPremios();
		} else {
			return this.getListaPremios();
		}

		return premios;
	}

	public List<Premio> getListaPremios() {
		return listaPremios;
	}

	public void setListaPremios(List<Premio> listaPremios) {
		this.listaPremios = listaPremios;
	}

	public ArrayList<Stack<Producto>> getBodega() {

		ArrayList<Stack<Producto>> cola = null;
		if (this.bodega == null) {
			this.bodega = new Bodega();
			cola = bodega.gestionarBodega(conexionExitosa);
		} else {
			return this.bodega.getCola();
		}

		return cola;
	}

}
