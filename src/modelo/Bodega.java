package modelo;

import java.sql.Connection;
import java.util.*;

import modeloDAO.CategoriaDAO;
import modeloDAO.ProductoDAO;

public class Bodega {

	public List<Categoria> listaCategorias = null;
	public List<Producto> listaProductos = null;
	ArrayList<Stack<Producto>> cola = new ArrayList<>(); // Cola que contiene los índices de categorías en orden de
	// llegada

	public ArrayList<Stack<Producto>> gestionarBodega(Connection conexionExitosa) {

		// Se consultan las categorias que existen
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		listaCategorias = categoriaDAO.getTodasLasCategorias(conexionExitosa);

		ProductoDAO productoDAO = new ProductoDAO();

		for (Categoria categoria : listaCategorias) {
			Stack<Producto> pila1 = new Stack<>();
			Stack<Producto> pila2 = new Stack<>();
			Stack<Producto> pila3 = new Stack<>();

			listaProductos = productoDAO.getProductosPorCategoria(categoria, conexionExitosa);

			for (int i = 0; i < listaProductos.size(); i++) {
				Producto producto = listaProductos.get(i);

				if (i < 4) {
					pila1.push(producto);
				} else if (i < 8) {
					pila2.push(producto);
				} else {
					pila3.push(producto);
				}
			}

			cola.add(pila1);
			cola.add(pila2);
			cola.add(pila3);
		}

		return cola;
	}

	public ArrayList<Stack<Producto>> getCola() {
		return cola;
	}

	public void setCola(ArrayList<Stack<Producto>> cola) {
		this.cola = cola;
	}

	public Producto seleccionarObsequio(Categoria categoriaMasRepet) {

		// Recorrer la cola
		for (Stack<Producto> pila : cola) {
			// Iterar sobre la pila de productos
			Iterator<Producto> iter = pila.iterator();
			while (iter.hasNext()) {
				Producto producto = iter.next();
				// Verificar si el producto coincide con la categoría deseada
				if (producto.getCategoria().getIdCategoria() == (categoriaMasRepet.getIdCategoria())) {
					// Eliminar el producto de la pila
					iter.remove();
					// Devolver el producto encontrado
					return producto;
				}
			}
		}
		// Si no se encuentra ningún producto con la categoría especificada
		return null;
	}

}
