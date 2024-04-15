package modelo;

import java.sql.Date;

public class Premio {

	public int idPremio;
	public Producto producto;
	public int estadoPremio;
	public Cliente cliente;

	public Premio() {
		super();

	}

	public Premio(int idPremio, Producto producto, int estadoPremio, Cliente cliente) {
		super();
		this.idPremio = idPremio;
		this.producto = producto;
		this.estadoPremio = estadoPremio;
		this.cliente = cliente;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getIdPremio() {
		return idPremio;
	}

	public void setIdPremio(int idPremio) {
		this.idPremio = idPremio;
	}

	public int getEstadoPremio() {
		return estadoPremio;
	}

	public void setEstadoPremio(int estadoPremio) {
		this.estadoPremio = estadoPremio;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID Premio: ").append(idPremio).append("\n");
		sb.append("Producto: ")
				.append(producto.nombreProducto + " Categoria: " + producto.getCategoria().getNombreCategoria())
				.append("\n");
		sb.append("Estado Premio: ").append(estadoPremio).append("\n");
		sb.append("Cliente: ").append(cliente.nombreCompleto + " CC:" + cliente.cedulaCliente).append("\n");
		return sb.toString();
	}

}
