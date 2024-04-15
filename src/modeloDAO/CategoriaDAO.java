package modeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Categoria;
import modelo.Pais;

public class CategoriaDAO {

	public Categoria getCategoria(int idCategoria, Connection conexionExitosa) {

		Categoria categoria = new Categoria();
		PreparedStatement sentenciaPreparada = null;
		ResultSet resultadoConsulta = null;

		String consultaSQL = "select * from categoria where idcategoria =" + idCategoria; // Esta consulta solo deberia
																							// de traer un
																							// unico registro
		try {
			sentenciaPreparada = conexionExitosa.prepareStatement(consultaSQL);
			resultadoConsulta = sentenciaPreparada.executeQuery();

			while (resultadoConsulta.next()) {

				categoria.setIdCategoria(resultadoConsulta.getInt("idcategoria"));
				categoria.setNombreCategoria(resultadoConsulta.getString("nombre_categoria"));// Nombre columna base de
																								// datos
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return categoria;
	}

	public List<Categoria> getTodasLasCategorias(Connection conexionExitosa) {

		List<Categoria> listaCategorias = new ArrayList<>();
		PreparedStatement sentenciaPreparada = null;
		ResultSet resultadoConsulta = null;

		String consultaSQL = "select * from categoria limit 2 ";// Todas las categorias
		try {
			sentenciaPreparada = conexionExitosa.prepareStatement(consultaSQL);
			resultadoConsulta = sentenciaPreparada.executeQuery();

			while (resultadoConsulta.next()) {

				Categoria categoria = new Categoria();

				categoria.setIdCategoria(resultadoConsulta.getInt("idcategoria"));
				categoria.setNombreCategoria(resultadoConsulta.getString("nombre_categoria"));
				
				listaCategorias.add(categoria);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaCategorias;
	}
}
