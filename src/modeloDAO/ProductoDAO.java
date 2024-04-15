package modeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Categoria;
import modelo.Producto;

public class ProductoDAO {

	public List<Producto> listaProductosPorCategoria = new ArrayList<>();

	public Producto getProducto(int idProducto, Connection conexionExitosa) {

		Producto producto = new Producto();
		PreparedStatement sentenciaPreparada = null;
		ResultSet resultadoConsulta = null;
		String consultaSQL = "select * from producto where idproducto =" + idProducto; // Solo debe de traer un
																						// registro
		try {
			sentenciaPreparada = conexionExitosa.prepareStatement(consultaSQL);
			resultadoConsulta = sentenciaPreparada.executeQuery();

			while (resultadoConsulta.next()) { // Evalua el siguiente en el select del query

				int idCategoria = resultadoConsulta.getInt("id_categoria"); // Consultar el id de categoria en la base
																			// de datos

				CategoriaDAO categoriaDAO = new CategoriaDAO();

				producto.setCategoria(
						categoriaDAO.getCategoria(resultadoConsulta.getInt("id_categoria"), conexionExitosa));
				producto.setIdProducto(idProducto);
				producto.setNombreProducto(resultadoConsulta.getString("nombre_producto"));
				producto.setPrecio(resultadoConsulta.getDouble("precio"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return producto;

	}

	public List<Producto> getProductosPorCategoria(Categoria categoria, Connection conexionExitosa) {

		List<Producto> listaProductosPorCategoria = new ArrayList<>();
		PreparedStatement sentenciaPreparada = null;
		ResultSet resultadoConsulta = null;
		String consultaSQL = "select * from producto where id_categoria =" + categoria.getIdCategoria() + " limit 12";
		try {
			sentenciaPreparada = conexionExitosa.prepareStatement(consultaSQL);
			resultadoConsulta = sentenciaPreparada.executeQuery();

			while (resultadoConsulta.next()) { // Evalua el siguiente en el select del query

				Producto producto = new Producto();
				producto.setCategoria(categoria);
				producto.setIdProducto(resultadoConsulta.getInt("idProducto"));
				producto.setNombreProducto(resultadoConsulta.getString("nombre_producto"));
				producto.setPrecio(resultadoConsulta.getDouble("precio"));

				listaProductosPorCategoria.add(producto);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaProductosPorCategoria;

	}

}
