import Clases.*;
import org.json.JSONObject;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        /*
        Gestion_productos gestion = new Gestion_productos();

        gestion.AddProducto(new Producto("Zanahoria", 1.2, 10, "Verdura"));
        gestion.AddProducto(new Producto("Manzana", 0.5, 50, "Fruta"));
        gestion.AddProducto(new Producto("Arroz", 2.0, 20, "Grano"));

        System.out.println("Antes de ordenar:");
        gestion.mostrarLista();

        gestion.ordenarProductosPorNombre();

        System.out.println("\nDespués de ordenar:");
        gestion.mostrarLista();

         */

        // Crear productos
        Producto producto1 = new Producto("Manzanas", 3.99, 10, "Frutas");
        Producto producto2 = new Producto("Pan", 1.99, 5, "Panadería");
        Producto producto3 = new Producto("Leche", 2.50, 2, "Lácteos");

        // Serializar productos
        JSONObject jsonProducto1 = producto1.toJSON();
        JSONObject jsonProducto2 = producto2.toJSON();
        System.out.println("Productos serializados:");
        System.out.println(jsonProducto1.toString(4));
        System.out.println(jsonProducto2.toString(4));

        // Deserializar producto
        Producto productoDeserializado = new Producto(jsonProducto1);
        System.out.println("Producto deserializado:");
        System.out.println(productoDeserializado);

        // Crear un cliente
        Cliente cliente = new Cliente("Juan", "Pérez", "12345678", "Calle Falsa 123", "juan@example.com", 123456789);
        System.out.println("\nCliente creado:");
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Correo: " + cliente.getCorreoElectronico());

        // Crear carrito
        Carrito carrito = new Carrito(1, cliente);
        carrito.agregarProducto(producto1);
        carrito.agregarProducto(producto2);
        carrito.agregarProducto(producto3);
        System.out.println("\nCarrito creado para el cliente:");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Productos en el carrito:");
        for (Producto p : carrito.getListaProductos()) {
            System.out.println("- " + p.getNombre() + " ($" + p.getPrecio() + ")");
        }

        // Calcular total del carrito
        double totalCarrito = carrito.calcularTotal();
        System.out.println("Total del carrito: $" + totalCarrito);

        // Crear una venta
        Venta venta = new Venta(LocalDateTime.now(), cliente, totalCarrito);
        System.out.println("\nVenta creada:");
        System.out.println(venta);

        // Serializar la venta
        JSONObject jsonVenta = venta.toJSON();
        System.out.println("\nVenta serializada:");
        System.out.println(jsonVenta.toString(4));

        // Deserializar la venta
        Venta ventaDeserializada = new Venta(jsonVenta);
        System.out.println("\nVenta deserializada:");
        System.out.println(ventaDeserializada);
    }
}