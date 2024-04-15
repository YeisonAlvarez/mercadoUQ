package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

	private static String driver = "com.mysql.jdbc.Driver"; // Conector Mysql que esta en Referenced Libraries, permite
															// conectarme a mysql workbench
	private static String usuario = "root"; // Usuario por default
	private static String password = "yei12345"; // Clave
	private static String url = "jdbc:mysql://localhost:3306/mercado"; // Indica que la conexion es local(localhost) y
																		// el puerto(3306) que se designo en el
																		// workbench
																		// paraconectarnos a la base de datos
																		// y la base de datos a la que se conecta
																		// (mercado)

	static {
		try {
			Class.forName(driver);
			System.out.println("Estableciendo Conexion con driver jdbc");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en el driver");
		}

	}

	Connection con = null;

	/**
	 * Metodo para poder obtener la conexion a la base de datos propio de java.sql
	 **/
	public Connection getConnection() {

		try {

			con = DriverManager.getConnection(url, usuario, password);
			System.out.println("Conexion satisfactoria con mysql");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error de conexion");

		}
		return con;

	}

	/**
	 * Metodo para poder Cerra la conexion a la base de datos propio de java.sql
	 **/
	public Connection close() {

		try {

			con.close();
			System.out.println("Se cerro la conexion exitosamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error de conexion");

		}
		return con;
	}

	public static void main(String[] args) {

		ConexionBD db = new ConexionBD();
		db.getConnection();
		// db.close();

	}

}