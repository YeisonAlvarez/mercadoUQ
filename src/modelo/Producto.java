package modelo;

public class Producto {

	public int idProducto;
	public String nombreProducto;
	public double precio;
	public Categoria categoria;

	public Producto() {
		super();
	}

	public Producto(int idProducto, String nombreProducto, double precio, Categoria categoria) {
		super();
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.precio = precio;
		this.categoria = categoria;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append(String.format("%-6s %-71s %-10s %-21s", idProducto, nombreProducto, categoria.nombreCategoria,
				String.format("$%,.2f", precio)));
		sb.append("\n");

		return sb.toString();
	}

}
