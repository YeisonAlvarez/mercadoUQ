package modeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;

import modelo.Categoria;
import modelo.Cliente;
import modelo.Factura;
import modelo.Premio;
import modelo.Producto;

public class PremioDAO {

	public List<Factura> listFacturasElegidas = null;
	public ClienteDAO clienteDAO = new ClienteDAO();

	public List<Factura> elegirFacturasParaObsequio(int idCliente, Connection conexionExitosa) {

		ClienteDAO cliente = null;
		List<Factura> listaFacturasElegidas = new ArrayList<>();
		List<Producto> listaProductos = new ArrayList<>();
		PreparedStatement sentenciaPreparada = null;
		ResultSet resultadoConsulta = null;
		String consultaSQL = "";
		Cliente clienteFactura = null;

		// Obtener el 10% de las facturas
		int cantLimite = obtenerLimitConsulta(idCliente, conexionExitosa);

		// Esta Consulta trae las facturas de los últimos 24 meses de cada cliente y en
		// el limit se calculo el 10%
		consultaSQL = "select * from mercado.factura  where  fecha_factura >= DATE_SUB(CURRENT_DATE, INTERVAL 24 MONTH) and id_cliente ="
				+ idCliente + " ORDER BY fecha_factura DESC LIMIT " + cantLimite;

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

				// Estas facturas ya son el 10% de las ultimas 24 meses
				listaFacturasElegidas.add(factura);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaFacturasElegidas;
	}

	private int obtenerLimitConsulta(int idCliente, Connection conexionExitosa) {

		PreparedStatement sentenciaPreparada = null;
		ResultSet resultadoConsulta = null;
		String consultaSQL = "select CEIL(COUNT(*) * 0.1)  from mercado.factura where  fecha_factura >= DATE_SUB(CURRENT_DATE, INTERVAL 24 MONTH) and id_cliente = "
				+ idCliente;

		int limit = 0;

		try {
			sentenciaPreparada = conexionExitosa.prepareStatement(consultaSQL); // Metodo de sql para preparar la query
																				// antes de ser ejecutado
			resultadoConsulta = sentenciaPreparada.executeQuery(); // Aqui se ejecuta la sentencia

			while (resultadoConsulta.next()) { // Evalua el siguiente en el select del query

				limit = resultadoConsulta.getInt(1);// Se obtiene el numero de registros que corresponden al 10% de las
													// facturas

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return limit;
	}

}
