package Clases;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
   // Atributos: listaProductos, cliente
   // Métodos: agregarProducto(), eliminarProducto(), calcularTotal()

    private Cliente cliente;
    //private int id_cliente;
    private List<Producto> listaProductos;

    //constructores

    public Carrito(Cliente cliente) {
        this.cliente = cliente;
        this.listaProductos = new ArrayList<>();
    }

    public Carrito() {
        this.listaProductos = new ArrayList<>();
    }

    // Constructor para deserialización desde JSON
    public Carrito(JSONObject jsonCarrito) {
        try {
            this.cliente = new Cliente(jsonCarrito.getJSONObject("cliente")); // Deserializa el cliente
            this.listaProductos = new ArrayList<>();
            JSONArray productosArray = jsonCarrito.getJSONArray("listaProductos");
            for (int i = 0; i < productosArray.length(); i++) {
                JSONObject jsonProducto = productosArray.getJSONObject(i);
                this.listaProductos.add(new Producto(jsonProducto)); // Deserializa cada producto
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //metodos
    public void agregarProducto(Producto producto) {
        if (producto != null) {
            listaProductos.add(producto);
        } else {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
    }
    public void agregarProducto(Producto producto, int cantidad) {
        if (producto == null || cantidad <= 0) {
            throw new IllegalArgumentException("El producto no puede ser nulo y la cantidad debe ser mayor a cero.");
        }
        for (int i = 0; i < cantidad; i++) {
            listaProductos.add(producto);
        }
    }
    public void eliminarProducto(Producto producto) {
        if (!listaProductos.remove(producto)) {
            throw new IllegalArgumentException("El producto no está en el carrito.");
        }
    }
    public double calcularTotal() {
        double total = 0;
        for (Producto producto : listaProductos) {
            total += producto.getPrecio();
        }
        return total;
    }

    // Metodo para serialización a JSON
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
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
        return new ArrayList<>(listaProductos);
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }
}
