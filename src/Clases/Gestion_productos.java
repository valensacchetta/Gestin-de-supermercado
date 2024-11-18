package Clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Gestion_productos {
    private ArrayList<Producto> listaDeProductos;

    public Gestion_productos() {
        listaDeProductos = new ArrayList<>();
    }

    public void AddProducto(Producto producto) {
        listaDeProductos.add(producto);
        System.out.println("Se guardó el producto correctamente");
    }

    public void deleteProducto(Producto producto) {
        listaDeProductos.remove(producto);
    }

    public void deleteProducto(int index) {
        listaDeProductos.remove(index);
    }

    public void mostrarLista() {
        for (int i = 0; i < listaDeProductos.size(); i++) {
            Producto p = listaDeProductos.get(i);
            System.out.println(i + " - " + p.getNombre() + " | Precio: " + p.getPrecio() + " | Unidades: " + p.getUnidades() + " | Categoría: " + p.getCategoria());
        }
    }

    public boolean buscarProducto(Producto producto) {
        return listaDeProductos.contains(producto);
    }

    public Producto buscarProductoPorID(int id) {
        for (Producto producto : listaDeProductos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

    // Nuevo método para ordenar productos alfabéticamente por nombre
    public void ordenarProductosPorNombre() {
        listaDeProductos.sort(Comparator.comparing(Producto::getNombre));
        System.out.println("Lista de productos ordenada alfabéticamente por nombre.");
    }
}
