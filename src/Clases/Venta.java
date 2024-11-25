package Clases;

import Interfaces.Serializable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Venta implements Serializable {
    //Atributos: id, fecha, cliente, listaProductos, total
    private int idVenta;
    private static int contador=0;
    private LocalDateTime fecha;
    private Cliente cliente;
    private List<Producto> listaProductos;
    private double total;

    //Constructor
    public Venta(LocalDateTime fecha, Cliente cliente,double total) {

        this.idVenta = ++contador; //autoincremental para que cada venta sea unica
        this.fecha = fecha;
        this.cliente = cliente;
        this.listaProductos = new ArrayList<Producto>();
        this.total = total;
    }

    public Venta(LocalDateTime fecha, List<Producto> listaProductos, double total) {
        this.idVenta = ++contador;
        this.fecha = fecha;
        this.listaProductos = listaProductos;
        this.total = total;
    }

    // Constructor para deserialización desde JSON
    public Venta(JSONObject jsonVenta) {
        try {
            this.idVenta = jsonVenta.getInt("idVenta");
            this.fecha = LocalDateTime.parse(jsonVenta.getString("fecha"), DateTimeFormatter.ISO_DATE_TIME);
            this.cliente = new Cliente(jsonVenta.getJSONObject("cliente")); // Deserializa el cliente
            this.listaProductos = new ArrayList<>();
            JSONArray productosArray = jsonVenta.getJSONArray("listaProductos");
            for (int i = 0; i < productosArray.length(); i++) {
                this.listaProductos.add(new Producto(productosArray.getJSONObject(i))); // Deserializa cada producto
            }
            this.total = jsonVenta.getDouble("total");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Metodos

    public int getIdVenta() {
        return idVenta;
    }

    public static int getContador() {
        return contador;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Supermercado Luna \n{" +
                "idVenta=" + idVenta +
                "\n fecha=" + fecha +
                "\n listaProductos=" + listaProductos +
                "\n total=" + total +
                '}';
    }

    // Metodo para serialización a JSON
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("idVenta", idVenta);
            jsonObject.put("fecha", fecha.format(DateTimeFormatter.ISO_DATE_TIME)); // Serializa la fecha como String
            jsonObject.put("cliente", cliente.toJSON()); // Serializa el cliente
            JSONArray productosArray = new JSONArray();
            for (Producto producto : listaProductos) {
                productosArray.put(producto.toJSON()); // Serializa cada producto
            }
            jsonObject.put("listaProductos", productosArray);
            jsonObject.put("total", total);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venta venta = (Venta) o;
        return idVenta == venta.idVenta && Double.compare(total, venta.total) == 0 && Objects.equals(fecha, venta.fecha) && Objects.equals(cliente, venta.cliente) && Objects.equals(listaProductos, venta.listaProductos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVenta, fecha, cliente, listaProductos, total);
    }
}
