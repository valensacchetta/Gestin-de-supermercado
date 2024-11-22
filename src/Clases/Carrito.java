package Clases;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    // Constructor para deserialización desde JSON
    public Carrito(JSONObject jsonCarrito) {
        try {
            this.idCarrito = jsonCarrito.getInt("idCarrito");
            this.cliente = new Cliente(jsonCarrito.getJSONObject("cliente")); // Deserializa el cliente
            this.listaProductos = new ArrayList<>();
            JSONArray productosArray = jsonCarrito.getJSONArray("listaProductos");
            for (int i = 0; i < productosArray.length(); i++) {
                JSONObject jsonProducto = productosArray.getJSONObject(i);
                this.listaProductos.add(new Producto(jsonProducto)); // Deserializa cada producto
            }
            this.total = jsonCarrito.getDouble("total");
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    // Método para serialización a JSON
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("idCarrito", idCarrito);
            jsonObject.put("cliente", cliente.toJSON()); // Serializa el cliente
            JSONArray productosArray = new JSONArray();
            for (Producto producto : listaProductos) {
                productosArray.put(producto.toJSON()); // Serializa cada producto
            }
            jsonObject.put("listaProductos", productosArray);
            jsonObject.put("total", calcularTotal()); // Calcula el total dinámicamente
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
