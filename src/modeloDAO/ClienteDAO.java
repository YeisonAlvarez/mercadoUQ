package modeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import modelo.Factura;
import modeloDAO.PaisDAO;

public class ClienteDAO {

	public Cliente getCliente(int idCliente, Connection conexionExitosa) {

		Cliente cliente = new Cliente();
		PreparedStatement sentenciaPreparada = null;
		ResultSet resultadoConsulta = null;

		String consultaSQL = "select * from cliente where idCliente =" + idCliente; // Solo debe de traer un registro

		try {
			sentenciaPreparada = conexionExitosa.prepareStatement(consultaSQL);
			resultadoConsulta = sentenciaPreparada.executeQuery();

			while (resultadoConsulta.next()) { // Evalua el siguiente en el select del query

				cliente.setIdCliente(resultadoConsulta.getInt("idCliente")); // Nombre de la columna tal cual en la base
																				// de datos
				cliente.setCedulaCliente(resultadoConsulta.getString("nro_identificacion"));
				cliente.setDireccion(resultadoConsulta.getString("direccion"));
				cliente.setNombreCompleto(resultadoConsulta.getString("nombre_completo"));
				cliente.setGenero(resultadoConsulta.getInt("genero"));
				cliente.setEdad(resultadoConsulta.getInt("edad"));

				// Saber el pais del cliente
				PaisDAO paisDAO = new PaisDAO();
				cliente.setPais(paisDAO.getPais(resultadoConsulta.getInt("id_pais"), conexionExitosa));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cliente;
	}

	public int getIdClientePorCedula(String nroIdenCliente, Connection conexionExitosa) {

		int idCliente = 0;
		PreparedStatement sentenciaPreparada = null;
		ResultSet resultadoConsulta = null;

		String consultaSQL = "select * from cliente where nro_identificacion =" + nroIdenCliente; // Solo debe de traer
																									// un registro

		try {
			sentenciaPreparada = conexionExitosa.prepareStatement(consultaSQL);
			resultadoConsulta = sentenciaPreparada.executeQuery();

			while (resultadoConsulta.next()) { // Evalua el siguiente en el select del query

				idCliente = (resultadoConsulta.getInt("idCliente"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return idCliente;
	}

	public List<Cliente> getListaClientes(Connection conexionExitosa) {
		
		List<Cliente> listaClientes = new ArrayList<>();
		PreparedStatement sentenciaPreparada = null;
		ResultSet resultadoConsulta = null;

		String consultaSQL = "select * from cliente " ; // 

		try {
			sentenciaPreparada = conexionExitosa.prepareStatement(consultaSQL);
			resultadoConsulta = sentenciaPreparada.executeQuery();

			while (resultadoConsulta.next()) { // Evalua el siguiente en el select del query
				
				Cliente cliente = new Cliente();

				cliente.setIdCliente(resultadoConsulta.getInt("idCliente")); // Nombre de la columna tal cual en la base
																				// de datos
				cliente.setCedulaCliente(resultadoConsulta.getString("nro_identificacion"));
				cliente.setDireccion(resultadoConsulta.getString("direccion"));
				cliente.setNombreCompleto(resultadoConsulta.getString("nombre_completo"));
				cliente.setGenero(resultadoConsulta.getInt("genero"));
				cliente.setEdad(resultadoConsulta.getInt("edad"));

				// Saber el pais del cliente
				PaisDAO paisDAO = new PaisDAO();
				cliente.setPais(paisDAO.getPais(resultadoConsulta.getInt("id_pais"), conexionExitosa));
				
				listaClientes.add(cliente);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaClientes;
	}
}
