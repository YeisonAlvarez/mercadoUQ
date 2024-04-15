package modelo;


import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Factura {

	public int nroFactura;
	public Date fechaFactura;
	public double totalFactura;
	public Cliente cliente;
	public List<Producto> listaProductos;

	public Factura(int nroFactura, Date fechaFactura, double totalFactura, Cliente cliente,
			List<Producto> listaProductos) {
		super();
		this.nroFactura = nroFactura;
		this.fechaFactura = fechaFactura;
		this.totalFactura = totalFactura;
		this.cliente = cliente;
		this.listaProductos = listaProductos;
	}

	public Factura() {
		super();
	}

	public int getNroFactura() {
		return nroFactura;
	}

	public void setNroFactura(int nroFactura) {
		this.nroFactura = nroFactura;
	}

	public Date getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public double getTotalFactura() {
		return totalFactura;
	}

	public void setTotalFactura(double totalFactura) {
		this.totalFactura = totalFactura;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	
	public String obtenerDiaFactura(Date fechaFactura) {
		
		
        // Convertir Date a Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaFactura);

        
        // Obtener el día de la semana como número (1: Domingo, 2: Lunes, ..., 7: Sábado)
        int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);

        // Mapear el número del día de la semana a su representación en texto
        String[] diasSemana = new String[]{"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
        String diaEnTexto = diasSemana[diaSemana - 1]; // Restamos 1 porque el índice del arreglo comienza en 0

        return diaEnTexto;

	}
	
	
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("\n---------------------------------------Factura # " + nroFactura
				+ "--------------------------------------------------------------\n");
		sb.append("Fecha: " + fechaFactura + "\n");
		sb.append("Cliente: " + cliente.nombreCompleto + " CC:" + cliente.getCedulaCliente() + " de "
				+ cliente.getPais().getNombrePais() + "\n");

		// Encabezados de la tabla de productos
		sb.append(
				"-----------------------------------------Productos--------------------------------------------------------------\n");
		sb.append(String.format("%-5s %-70s %-10s %-20s", "ID", "|Nombre", " |Categoria", "|Precio") + "\n");
		sb.append(
				"----------------------------------------------------------------------------------------------------------------\n");

		// Filas de la tabla de productos
		for (Producto producto : listaProductos) {
			sb.append(producto.toString());
		}

		sb.append("\n\nTotal:                                                                                  "
				+ String.format("$%,.2f", totalFactura) + "\n");
		sb.append(
				"----------------------------------------------------------------------------------------------------------------"
						+ "\n\n\n");

		return sb.toString();
	}

}
