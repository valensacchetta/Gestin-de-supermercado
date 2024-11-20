package Clases;
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
        Venta nuevaVenta;
        System.out.println("¿Es cliente registrado? s/n");
        String op = sc.nextLine();

        if (op.equals("n")) {
            // Crear venta sin cliente registrado
            nuevaVenta = new Venta(LocalDateTime.now(), getListaProductos(), calcularTotal());
            System.out.println(nuevaVenta);

            // Incrementar contador de transacciones para este cajero
            this.transaccionesRealizadas++;

        } else {
            // Buscar al cliente por DNI
            System.out.println("Ingrese el DNI del cliente:");
            String dni = sc.nextLine();
            Gestion_clientes gestionClientes= new Gestion_clientes();
            Cliente cliente = gestionClientes.buscarClientePorDNI(dni); // Metodo que busca al cliente

            if (cliente != null) {
                // Crear venta con cliente registrado
                nuevaVenta = new Venta(LocalDateTime.now(),cliente,calcularTotal());
                System.out.println(nuevaVenta);

                // Incrementar contador de transacciones para este cajero
                this.transaccionesRealizadas++;
            } else {
                System.out.println("Cliente no encontrado. Por favor, registre al cliente primero.");
            }
        }
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
    public double calcularTotal(){
        double total = 0;
        for(Producto producto: listaProductos){
            total += producto.getPrecio();
        }
        return total;
    }

    public void addproducto(Producto producto,int cantidad){
        for(int i=0;i<cantidad;i++){
        this.listaProductos.add(producto);
        }
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
