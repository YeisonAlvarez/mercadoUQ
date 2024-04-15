package modelo;

//Clase para representar una entrega
public class Entrega implements Comparable<Entrega> {

	private Premio premio;
	private Cliente cliente;

	// Constructor
	public Entrega(Premio premio, Cliente cliente) {
		this.premio = premio;
		this.cliente = cliente;
	}

	// Método para obtener el premio asociado a la entrega
	public Premio getPremio() {
		return premio;
	}

	// Método para obtener el cliente asociado a la entrega
	public Cliente getCliente() {
		return cliente;
	}

	// Método compareTo para comparar entregas según los criterios de prioridad,
	// país y secuencia
	@Override
	public int compareTo(Entrega otraEntrega) {
		// Comparamos por prioridad, país y secuencia
		if (cliente.getPrioridad() != otraEntrega.getCliente().getPrioridad()) {
			return Integer.compare(cliente.getPrioridad(), otraEntrega.getCliente().getPrioridad());
		} else if (cliente.getPaisPrioridad() != otraEntrega.getCliente().getPaisPrioridad()) {
			return Integer.compare(cliente.getPaisPrioridad(), otraEntrega.getCliente().getPaisPrioridad());
		} else {
			return Integer.compare(cliente.getSecuenciaPrioridad(), otraEntrega.getCliente().getSecuenciaPrioridad());
		}
	}

	// Método toString para imprimir la entrega
	@Override
	public String toString() {
		return "\nPremio: " + premio.toString() + "\n" + cliente.toString();
	}
}