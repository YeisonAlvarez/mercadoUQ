package modeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import modelo.Producto;
import modelo.Factura;

public class DetalleFacturaDAO {

	public List<Producto> listaProductos = new ArrayList<>();

	public List<Producto> getListadeProductosFactura(int idFactura, Connection conexionExitosa) {

		PreparedStatement sentenciaPreparada = null;
		ResultSet resultadoConsulta = null;
		String consultaSQL = "select * from detalleFactura where id_factura =" + idFactura; // Solo debe de traer un
																							// registro
		try {
			sentenciaPreparada = conexionExitosa.prepareStatement(consultaSQL);
			resultadoConsulta = sentenciaPreparada.executeQuery();
			
			while (resultadoConsulta.next()) { // Evalua el siguiente en el select del query

				int idProducto = resultadoConsulta.getInt("id_producto");
				
				// Saber el Id del Producto
				ProductoDAO productoDao = new ProductoDAO();
				
				Producto producto = productoDao.getProducto(idProducto, conexionExitosa);

				// Sacar los productos de la factura

				listaProductos.add(producto);
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaProductos;
	}

}
