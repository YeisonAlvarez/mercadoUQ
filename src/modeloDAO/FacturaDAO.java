package modeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.Cliente;
import modelo.Factura;
import modelo.Premio;
import modelo.Producto;

public class FacturaDAO {

	ClienteDAO clienteDAO = null;

	public List<Factura> listarFacturas(Connection conexionExitosa) {

		List<Factura> listaFacturas = new ArrayList<>();
		List<Producto> listaProductos = new ArrayList<>();
		PreparedStatement sentenciaPreparada = null;
		ResultSet resultadoConsulta = null;
		String consultaSQL = "";
		Cliente clienteFactura = null;

		consultaSQL = "select * from factura";

		// Se ejecuta la consulta
		try {
			sentenciaPreparada = conexionExitosa.prepareStatement(consultaSQL); // Metodo de sql para preparar la query
																				// antes de ser ejecutado
			resultadoConsulta = sentenciaPreparada.executeQuery(); // Aqui se ejecuta la sentencia

			while (resultadoConsulta.next()) { // Evalua el siguiente en el select del query

				Factura factura = new Factura();
				int idFactura = resultadoConsulta.getInt("idfactura");// llave primaria de la tabla factura
				factura.setNroFactura(idFactura); // Nombre exacto de la columna en la base de datos
				factura.setFechaFactura(resultadoConsulta.getDate("fecha_factura"));
				factura.setTotalFactura(resultadoConsulta.getDouble("total_factura"));

				// Saber el cliente de dicha factura
				clienteDAO = new ClienteDAO();
				int idCliente = resultadoConsulta.getInt("id_cliente");// llave foranea del cliente que esta en factura
				clienteFactura = clienteDAO.getCliente(idCliente, conexionExitosa); // llave foranea
				factura.setCliente(clienteFactura);

				// Sacar los productos de la factura
				DetalleFacturaDAO detalleFactura = new DetalleFacturaDAO();
				listaProductos = detalleFactura.getListadeProductosFactura(idFactura, conexionExitosa);
				factura.setListaProductos(listaProductos);

				listaFacturas.add(factura); // Se van agregando ya las facturas mapeadas de la base de datos a objetis
											// en java y se agregan a la lista de facturas

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaFacturas;

	}

	public List<Factura> listarFacturasPorCliente(String nroIdenCliente, Connection conexionExitosa) {

		List<Factura> listaFacturas = new ArrayList<>();
		List<Producto> listaProductos = new ArrayList<>();
		PreparedStatement sentenciaPreparada = null;
		ResultSet resultadoConsulta = null;
		String consultaSQL = "";
		Cliente clienteFactura = null;

		clienteDAO = new ClienteDAO();
		int idCliente = clienteDAO.getIdClientePorCedula(nroIdenCliente, conexionExitosa);

		consultaSQL = "select * from factura where id_cliente = " + idCliente;

		// Se ejecuta la consulta
		try {
			sentenciaPreparada = conexionExitosa.prepareStatement(consultaSQL); // Metodo de sql para preparar la query
																				// antes de ser ejecutado
			resultadoConsulta = sentenciaPreparada.executeQuery(); // Aqui se ejecuta la sentencia

			while (resultadoConsulta.next()) { // Evalua el siguiente en el select del query

				Factura factura = new Factura();
				int idFactura = resultadoConsulta.getInt("idfactura");// llave primaria de la tabla factura
				factura.setNroFactura(idFactura); // Nombre exacto de la columna en la base de datos
				factura.setFechaFactura(resultadoConsulta.getDate("fecha_factura"));
				factura.setTotalFactura(resultadoConsulta.getDouble("total_factura"));

				// Saber todo cliente de dicha factura
				clienteFactura = clienteDAO.getCliente(idCliente, conexionExitosa); // llave foranea
				factura.setCliente(clienteFactura);

				// Sacar los productos de la factura
				DetalleFacturaDAO detalleFactura = new DetalleFacturaDAO();
				listaProductos = detalleFactura.getListadeProductosFactura(idFactura, conexionExitosa);
				factura.setListaProductos(listaProductos);

				listaFacturas.add(factura); // Se van agregando ya las facturas mapeadas de la base de datos a objetis
											// en java y se agregan a la lista de facturas

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaFacturas;
	}

	
	

}
