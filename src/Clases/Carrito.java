package Clases;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
   // Atributos: listaProductos, cliente
   // Métodos: agregarProducto(), eliminarProducto(), calcularTotal(), finalizarCompra()

    private int idCarrito;
    private Cliente cliente;
    //private int id_cliente;
    private List<Producto> listaProductos;
    private double total;

    //constructores

    public Carrito(int idCarrito, Cliente cliente) {
        this.idCarrito = idCarrito;
        this.cliente = cliente;
        this.listaProductos = new ArrayList<>();
        this.total = 0;
    }

    //metodos
    public void agregarProducto(Producto producto) {
        listaProductos.add(producto);
    }
    public void eliminarProducto(Producto producto) {
        listaProductos.remove(producto);
    }
    public double calcularTotal(){
        double total = 0;
        for(Producto producto: listaProductos){
            total += producto.getPrecio();
        }
        return total;
    }
}
