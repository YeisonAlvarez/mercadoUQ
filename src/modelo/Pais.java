package modelo;

public class Pais {

	public int idPais;
	public String nombrePais;
	public int tipo;

	public Pais() {
		super();
	}

	
	public Pais(int idPais, String nombrePais, int tipo) {
		super();
		this.idPais = idPais;
		this.nombrePais = nombrePais;
		this.tipo = tipo;
	}


	public int getIdPais() {
		return idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}


	public int getTipo() {
		return tipo;
	}


	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	

}
