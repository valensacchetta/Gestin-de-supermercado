package Clases;
import org.json.JSONObject;

import java.util.*;
import java.time.LocalDateTime;

public class Cajero extends Empleado {
    private int transaccionesRealizadas;
    private List<Producto> listaProductos;


    public Cajero(String dni, String nombre, String apellido, String cargo, double salario) {
        super(dni, nombre, apellido, cargo, salario);
        this.transaccionesRealizadas = 0; // Inicia en 0
        this.listaProductos = new LinkedList<>();
    }

    public int getTransaccionesRealizadas() {
        return transaccionesRealizadas;
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

    public void transaccionConCliente (){
        Venta nuevaVenta;
        Scanner sc = new Scanner(System.in);
        // Buscar al cliente por DNI
        System.out.println("Ingrese el DNI del cliente:");
        String dni = sc.nextLine();
        Gestion_clientes gestionClientes= new Gestion_clientes();
        Cliente cliente = gestionClientes.buscarClientePorDNI(dni); // Metodo que busca al cliente

        if (cliente != null) {
            // Crear venta con cliente registrado
            Carrito carrito = nuevoCarritoCliente(cliente);
            listaProductos = carrito.getListaProductos();
            nuevaVenta = new Venta(LocalDateTime.now(),cliente,carrito.calcularTotal());
            System.out.println(nuevaVenta);

            // Incrementar contador de transacciones para este cajero
            this.transaccionesRealizadas++;
        } else {
            System.out.println("Cliente no encontrado. Por favor, registre al cliente primero.");
        }
    }
    public void transaccionSinCliente(){
        // Crear venta sin cliente registrado
        Carrito carrito = nuevoCarritoSinCliente();
        Venta nuevaVenta;
        listaProductos = carrito.getListaProductos();
        nuevaVenta = new Venta(LocalDateTime.now(), getListaProductos(), carrito.calcularTotal());
        System.out.println(nuevaVenta);

        // Incrementar contador de transacciones para este cajero
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
        int telefono = sc.nextInt();
        gestionClientes.agregarCliente(new Cliente(nombre,apellido,dni,direccion,email,telefono)); //Cliente guardado en la lista de clientes
    }


    @Override
    public String toString() {
        return super.toString() +
                ", Cajero{" +
                "transaccionesRealizadas=" + transaccionesRealizadas +
                '}';
    }

    public Carrito nuevoCarritoCliente (Cliente cliente){
        Carrito carrito =new Carrito(cliente);
        Scanner sc = new Scanner(System.in);
        Gestion_productos gesProd = new Gestion_productos();
        gesProd.mostrarLista();
        String prod;
        int cant;
        do {
            System.out.println("Ingrese el nombre del producto a agregar, '0' para terminar :");
            prod = sc.nextLine();
            if(prod!="0") {
                System.out.println("Cantidad? :");
                cant = sc.nextInt();
                sc.nextLine();
                carrito.agregarProducto(gesProd.buscarProductoPorNombre(prod),cant);
            }
        }while (prod!="0");
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
                System.out.println("Cantidad? :");
                cant = sc.nextInt();
                sc.nextLine();
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

        System.exit(0); // Termina el programa directamente
    }
}
