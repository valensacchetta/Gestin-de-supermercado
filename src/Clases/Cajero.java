package Clases;
import Excepciones.ExcepcionClienteNoEncontrado;
import Excepciones.ExcepcionProductoNoEncontrado;
import Excepciones.ExcepcionVentaInvalida;

import java.util.*;
import java.time.LocalDateTime;

public class Cajero extends Empleado {
    private int transaccionesRealizadas;
    private List<Producto> listaProductos;
    private int transaccionesMax;


    public Cajero( String nombre, String apellido,String dni, String cargo, double salario) {
        super(dni, nombre, apellido, cargo, salario);
        this.transaccionesRealizadas = 0;
        this.transaccionesMax =0;   // Inicia en 0
        this.listaProductos = new LinkedList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cajero cajero)) return false;
        if (!super.equals(o)) return false;
        return transaccionesRealizadas == cajero.transaccionesRealizadas;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), transaccionesRealizadas);
    }

    public int getTransaccionesRealizadas() {
        return transaccionesRealizadas;
    }

    public void mostrarMenuCajero() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nCajero: " + getNombre() + " " + getApellido());
            System.out.println("Seleccione una opción:");
            System.out.println("1. Realizar nueva transacción");
            System.out.println("2. Agregar producto al carrito");
            System.out.println("3. Eliminar producto del carrito");
            System.out.println("4. Mostrar productos en el carrito");
            System.out.println("5. Salir y cerrar sesión");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    nuevaTransaccion();
                    break;
                case 2:
                    agregarProductoAlCarrito();
                    break;
                case 3:
                    eliminarProductoDelCarrito();
                    break;
                case 4:
                    mostrarCarrito();
                    break;
                case 5:
                    cerrarCaja(); // Registra el cierre y termina el programa
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }
        }
    }


    public void nuevaTransaccion() {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Es cliente registrado? s/n");
        String op = sc.nextLine();
        if (op.equals("n")) {
            System.out.println("Quiere registrarse? s/n");
            op = sc.nextLine();
            if (op.equals("n")) {
                transaccionSinCliente();
            }else {
                registrarCliente();
            }
        } else {
            transaccionConCliente();
        }
    }

    public void transaccionConCliente() {
        Venta nuevaVenta;
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el DNI del cliente:");
        String dni = sc.nextLine();

        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("El DNI proporcionado es nulo o vacío.");
        }

        Gestion_clientes gestionClientes = new Gestion_clientes();
        Cliente cliente = gestionClientes.buscarClientePorDNI(dni);

        if (cliente == null) {
            throw new ExcepcionClienteNoEncontrado("Cliente con DNI " + dni + " no encontrado.");
        }

        Carrito carrito = nuevoCarritoCliente(cliente);

        if (carrito.getListaProductos().isEmpty()) {
            throw new ExcepcionVentaInvalida("No se pueden realizar ventas con un carrito vacío.");
        }

        nuevaVenta = new Venta(LocalDateTime.now(), cliente, carrito.calcularTotal());
        System.out.println(nuevaVenta);
        System.out.println("Precio con descuento del 10%");
        this.transaccionesRealizadas++;
    }

    public void transaccionSinCliente(){
        // Crear venta sin cliente registrado
        Carrito carrito = nuevoCarritoSinCliente();
        if (carrito.getListaProductos().isEmpty()) {
            throw new ExcepcionVentaInvalida("No se pueden realizar ventas con un carrito vacío.");
        }
        Venta nuevaVenta;
        listaProductos = carrito.getListaProductos();
        nuevaVenta = new Venta(LocalDateTime.now(), getListaProductos(), carrito.calcularTotal());
        System.out.println(nuevaVenta);
        this.transaccionesRealizadas++;
    }

    public void registrarCliente(){
        Scanner sc = new Scanner(System.in);
        Gestion_clientes gestionClientes= new Gestion_clientes();

        System.out.println("Ingrese nombre del cliente:");
        String nombre = sc.nextLine();
        System.out.println("Ingrese apellido del cliente:");
        String apellido = sc.nextLine();
        System.out.println("Ingrese el DNI del cliente:");
        String dni = sc.nextLine();
        System.out.println("Ingrese la direccion del cliente:");
        String direccion = sc.nextLine();
        System.out.println("Ingrese el email del cliente:");
        String email = sc.nextLine();
        System.out.println("Ingrese el telefono del cliente:");
        String telefono = sc.nextLine();
        sc.nextLine();
        gestionClientes.agregarCliente(new Cliente(nombre,apellido,dni,direccion,email,telefono)); //Cliente guardado en la lista de clientes
    }

    private void agregarProductoAlCarrito() {
        Scanner scanner = new Scanner(System.in);
        Gestion_productos gestionProductos = new Gestion_productos();

        System.out.println("Productos disponibles:");
        gestionProductos.mostrarLista();

        System.out.println("Ingrese el nombre del producto a agregar:");
        String nombreProducto = scanner.nextLine();

        Producto producto = gestionProductos.buscarProductoPorNombre(nombreProducto);
        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.println("Ingrese la cantidad a agregar:");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor que cero.");
        } else {
            addproducto(producto, cantidad);
            System.out.println("Producto agregado al carrito.");
        }
    }

    private void eliminarProductoDelCarrito() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Productos en el carrito:");
        mostrarCarrito();

        System.out.println("Ingrese el nombre del producto a eliminar:");
        String nombreProducto = scanner.nextLine();

        Producto productoAEliminar = null;
        for (Producto producto : listaProductos) {
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                productoAEliminar = producto;
                break;
            }
        }
        if (productoAEliminar == null) {
            System.out.println("Producto no encontrado en el carrito.");
        } else {
            removeproducto(productoAEliminar);
            System.out.println("Producto eliminado del carrito.");
        }
    }

    private void mostrarCarrito() {
        System.out.println("\nProductos en el carrito:");
        if (listaProductos.isEmpty()) {
            System.out.println("El carrito está vacío.");
        } else {
            for (Producto producto : listaProductos) {
                System.out.println("- " + producto.getNombre() + " | Precio: $" + producto.getPrecio());
            }
            System.out.println("Total: $" + listaProductos.stream().mapToDouble(Producto::getPrecio).sum());
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Cajero{" +
                "transaccionesRealizadas=" + transaccionesRealizadas +
                '}';
    }

    public Carrito nuevoCarritoCliente(Cliente cliente) {
        Carrito carrito = new Carrito(cliente);
        Scanner sc = new Scanner(System.in);
        Gestion_productos gesProd = new Gestion_productos();
        gesProd.mostrarLista();
        String prod;
        int cant;

        do {
            System.out.println("Ingrese el nombre del producto a agregar, '0' para terminar:");
            prod = sc.nextLine();
            if (!prod.equals("0")) {
                Producto producto = gesProd.buscarProductoPorNombre(prod);
                if (producto == null) {
                    throw new ExcepcionProductoNoEncontrado("Producto " + prod + " no encontrado.");
                }
                System.out.println("Cantidad?");
                cant = sc.nextInt();
                sc.nextLine();
                if (cant <= 0) {
                    throw new IllegalArgumentException("La cantidad ingresada debe ser mayor que cero.");
                }
                if((producto.getUnidades()-cant)<0){
                    throw new IllegalArgumentException("No hay tantas unidades del producto elegido");
                }
                producto.setUnidades(producto.getUnidades()-cant);
                carrito.agregarProducto(producto, cant);
            }
        } while (!prod.equals("0"));
        return carrito;
    }

    public Carrito nuevoCarritoSinCliente (){
        Carrito carrito =new Carrito();
        Scanner sc = new Scanner(System.in);
        Gestion_productos gesProd = new Gestion_productos();
        gesProd.mostrarLista();
        String prod;
        int cant;
        do {
            System.out.println("Ingrese el nombre del producto a agregar, '0' para terminar :");
            prod = sc.nextLine();
            if(!prod.equals("0")) {
                Producto producto = gesProd.buscarProductoPorNombre(prod);
                if (producto == null) {
                    throw new ExcepcionProductoNoEncontrado("Producto " + prod + " no encontrado.");
                }
                System.out.println("Cantidad? :");
                cant = sc.nextInt();
                sc.nextLine();
                if (cant <= 0) {
                    throw new IllegalArgumentException("La cantidad ingresada debe ser mayor que cero.");
                }
                if((producto.getUnidades()-cant)<0){
                    throw new IllegalArgumentException("No hay tantas unidades del producto elegido");
                }
                producto.setUnidades(producto.getUnidades()-cant);
                gesProd.addProducto(producto);
                carrito.agregarProducto(gesProd.buscarProductoPorNombre(prod),cant);
            }
        }while (!prod.equals("0"));
        return carrito;
    }

    public void addproducto(Producto producto,int cantidad){
        for(int i=0;i<cantidad;i++){
        this.listaProductos.add(producto);
        }
    }
    public void addproducto(Producto producto){
        this.listaProductos.add(producto);
    }
    public void removeproducto(Producto producto){
        this.listaProductos.remove(producto);
    }
    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void cerrarCaja() {
        LocalDateTime horaCierre = LocalDateTime.now();
        System.out.println("Cierre de sesión del Cajero.");
        System.out.println("Hora de cierre: " + horaCierre);
        if(transaccionesRealizadas > transaccionesMax) {
            transaccionesMax = transaccionesRealizadas;
            super.setSalario(this.getSalarioHora()+ transaccionesMax * 0.1);
        }
        System.exit(0); // Termina el programa directamente
    }
}
