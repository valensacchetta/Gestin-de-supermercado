package Clases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Gestion_ventas {
    private List<Venta> listaDeVentas;

    public Gestion_ventas() {
        listaDeVentas = new ArrayList<>();
    }

    // Método para agregar una venta
    public void agregarVenta(LocalDateTime fecha, Cliente cliente, List<Producto> productos) {
        // Calcular el total
        double total = productos.stream().mapToDouble(Producto::getPrecio).sum();

        // Crear una nueva venta
        Venta nuevaVenta = new Venta(fecha, cliente, total);
        nuevaVenta.getListaProductos().addAll(productos);

        // Agregarla a la lista
        listaDeVentas.add(nuevaVenta);
        System.out.println("Venta registrada correctamente. ID: " + nuevaVenta.getIdVenta());
    }

    // Método para mostrar todas las ventas
    public void mostrarVentas() {
        if (listaDeVentas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        } else {
            for (Venta venta : listaDeVentas) {
                System.out.println(venta);
            }
        }
    }

    // Método para buscar una venta por ID
    public Venta buscarVentaPorID(int idVenta) {
        for (Venta venta : listaDeVentas) {
            if (venta.getIdVenta() == idVenta) {
                return venta;
            }
        }
        System.out.println("Venta con ID " + idVenta + " no encontrada.");
        return null;
    }

    // Método para calcular el total vendido
    public double calcularTotalVendido() {
        return listaDeVentas.stream().mapToDouble(Venta::getTotal).sum();
    }
}
