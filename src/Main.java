import Clases.*;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static int salir=0;
    public static void main(String[] args) {
       while(salir==0){
            try {
                Gestion_supermercado.iniciar();
                System.out.println();
            }catch (Exception e){
                e.printStackTrace();
                e.getMessage();
                System.out.println();
            }
       }

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
        /*
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

         */
        /*
        // Inicializamos Gestion_productos con el archivo definido
        Gestion_productos gestion = new Gestion_productos();

        // Agregamos productos
        Producto producto1 = new Producto("Manzanas", 1.2, 50, "Frutas");
        Producto producto2 = new Producto("Leche", 0.9, 20, "Lácteos");
        gestion.addProducto(producto1);
        gestion.addProducto(producto2);

        // Mostramos productos
        System.out.println("Lista actual:");
        gestion.mostrarLista();

        // Eliminamos un producto
        gestion.deleteProducto("Manzanas");

        // Mostramos productos después de eliminar
        System.out.println("Lista después de eliminar:");
        gestion.mostrarLista();

         */

        /*
        // Crear instancia de la clase de gestión de clientes
        Gestion_clientes gestionClientes = new Gestion_clientes();

        // Crear clientes
        Cliente cliente1 = new Cliente("Juan", "Pérez", "12345678", "Calle Falsa 123", "juan@example.com", 123456789);
        Cliente cliente2 = new Cliente("Ana", "Gómez", "87654321", "Avenida Siempre Viva 456", "ana@example.com", 987654321);
        Cliente cliente3 = new Cliente("Luis", "Martínez", "56781234", "Boulevard del Sol 789", "luis@example.com", 456789123);

        // Agregar clientes a la gestión
        System.out.println("Agregando clientes...");
        gestionClientes.agregarCliente(cliente1);
        gestionClientes.agregarCliente(cliente2);
        gestionClientes.agregarCliente(cliente3);

        // Mostrar todos los clientes
        System.out.println("\nClientes registrados:");
        gestionClientes.mostrarClientes();

        // Buscar un cliente por DNI
        System.out.println("\nBuscando cliente con DNI 12345678...");
        Cliente clienteBuscado = gestionClientes.buscarClientePorDNI("12345678");
        if (clienteBuscado != null) {
            System.out.println("Cliente encontrado: " + clienteBuscado);
        } else {
            System.out.println("Cliente no encontrado.");
        }

        // Dar de baja a un cliente
        System.out.println("\nDando de baja al cliente con DNI 12345678...");
        gestionClientes.eliminarCliente("12345678");

        // Mostrar todos los clientes después de dar de baja
        System.out.println("\nClientes después de dar de baja:");
        gestionClientes.mostrarClientes();

        // Intentar agregar un cliente con el mismo DNI
        System.out.println("\nIntentando agregar un cliente con DNI duplicado (12345678)...");
        gestionClientes.agregarCliente(cliente1);

        // Mostrar todos los clientes nuevamente
        System.out.println("\nClientes finales:");
        gestionClientes.mostrarClientes();

         */
        /*
        // Crear algunos productos
        Producto producto1 = new Producto("Producto A", 10.0, 5, "Categoria 1");
        Producto producto2 = new Producto("Producto B", 20.0, 3, "Categoria 2");
        Producto producto3 = new Producto("Producto C", 15.0, 2, "Categoria 1");

        // Crear un cliente
        Cliente cliente = new Cliente("Juan", "Pérez", "12345678", "Calle Falsa 123", "juan@example.com", 123456789);

        // Crear una lista de productos
        List<Producto> productos = new ArrayList<>();
        productos.add(producto1);
        productos.add(producto2);
        productos.add(producto3);

        // Crear una instancia de la gestión de ventas
        Gestion_ventas gestionVentas = new Gestion_ventas();

        // Registrar una venta
        gestionVentas.agregarVenta(LocalDateTime.now(), cliente, productos);

        // Mostrar todas las ventas registradas
        gestionVentas.mostrarVentas();

        // Calcular el total vendido
        System.out.println("Total vendido: " + gestionVentas.calcularTotalVendido());

         */
    }
}