package modeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Cliente;
import modelo.Pais;

public class PaisDAO {

	public Pais getPais(int idPais, Connection conexionExitosa) {

		Pais pais = new Pais();
		PreparedStatement sentenciaPreparada = null;
		ResultSet resultadoConsulta = null;

		String consultaSQL = "select * from pais where idPais =" + idPais; // Esta consulta solo deberia de traer un
																			// unico registro
		try {
			sentenciaPreparada = conexionExitosa.prepareStatement(consultaSQL);
			resultadoConsulta = sentenciaPreparada.executeQuery();

			while (resultadoConsulta.next()) {

				pais.setIdPais(resultadoConsulta.getInt("idPais")); // Nombre de la columna tal cual en la base de datos
				pais.setNombrePais(resultadoConsulta.getString("nombre_pais"));
				pais.setTipo(resultadoConsulta.getInt("tipo"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pais;
	}
}
