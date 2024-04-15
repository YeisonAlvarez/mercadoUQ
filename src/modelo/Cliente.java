package modelo;

public class Cliente {

	public int idCliente;
	public String nombreCompleto;
	public String cedulaCliente;
	public String direccion;
	public Pais pais;
	public int edad;
	public int genero;

	// Campos para manejar la Prioridad de la cola
	private int prioridad;
	private int paisPrioridad;
	private int secuenciaPrioridad;

	public Cliente() {
		super();
	}

	public Cliente(int idCliente, String nombreCompleto, String cedulaCliente, String direccion, Pais pais, int edad,
			int genero) {
		super();
		this.idCliente = idCliente;
		this.nombreCompleto = nombreCompleto;
		this.cedulaCliente = cedulaCliente;
		this.direccion = direccion;
		this.pais = pais;
		this.edad = edad;
		this.genero = genero;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getCedulaCliente() {
		return cedulaCliente;
	}

	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getGenero() {
		return genero;
	}

	public String getGeneroDesc() {
		if (genero == 0) {
			return "MUJER";
		} else {
			return "HOMBRE";
		}
	}
	public void setGenero(int genero) {
		this.genero = genero;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	public int getPaisPrioridad() {
		return paisPrioridad;
	}

	public void setPaisPrioridad(int paisPrioridad) {
		this.paisPrioridad = paisPrioridad;
	}

	public int getSecuenciaPrioridad() {
		return secuenciaPrioridad;
	}

	public void setSecuenciaPrioridad(int secuenciaPrioridad) {
		this.secuenciaPrioridad = secuenciaPrioridad;
	}

	public String toString() {
		return "Informacion detallada del Cliente:\n" + "-------------------------\n" + "Cliente: " + nombreCompleto
				+ "\n" + "Cédula: " + cedulaCliente + "\n" + "Dirección: " + direccion + "\n" + "País: "
				+ pais.getNombrePais() + "\n" + "Edad: " + edad + "\n" + "Género: " + getGeneroDesc() + "\n" + "Prioridad: "
				+ prioridad + "\n" + "País de Prioridad: " + paisPrioridad + "\n" + "Secuencia de Prioridad: "
				+ secuenciaPrioridad + "\n" + "-------------------------";
	}

}
