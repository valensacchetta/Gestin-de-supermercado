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
        String cliente = sc.nextLine();
        if (cliente.equals("n")) {
            nuevaVenta = new Venta(LocalDateTime.now(),getListaProductos(),calcularTotal());
        }
        this.transaccionesRealizadas++;
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
}
