package Clases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venta {
    //Atributos: id, fecha, cliente, listaProductos, total
    private int idVenta;
    private Date fecha;
    private Cliente cliente;
    private List<Producto> listaProductos;
    private double total;

    //Constructor
    public Venta(int idVenta, Date fecha, Cliente cliente){
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.cliente = cliente;
        this.listaProductos = new ArrayList<Producto>();
        this.total = 0;
    }

    //Metodos
    public double calcularTotal(){
        double total = 0;
        for(Producto producto: listaProductos){
            total += producto.getPrecio();
        }
        return total;
    }

}
