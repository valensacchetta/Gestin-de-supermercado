package Clases;

import Interfaces.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Producto implements Serializable {
    private static int contadorId = 0; // Contador para asignar IDs únicos
    private int id; // Ahora es un atributo de instancia
    private String nombre;
    private double precio;
    private int unidades;
    private String categoria;

    // Constructor
    public Producto(String nombre, double precio, int unidades, String categoria) {
        this.id = ++contadorId; // Asigna un ID único automáticamente
        this.nombre = nombre;
        this.precio = precio;
        this.unidades = unidades;
        this.categoria = categoria;
    }

    // Constructor desde JSON
    public Producto(JSONObject producto) {
        try {
            this.id = producto.getInt("id");
            this.nombre = producto.getString("nombre");
            this.precio = producto.getDouble("precio");
            this.unidades = producto.getInt("unidades");
            this.categoria = producto.getString("categoria");
        } catch (JSONException e) {
            throw new IllegalArgumentException("Error al deserializar el producto desde JSON: " + e.getMessage());
        }
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getUnidades() {
        return unidades;
    }

    public String getCategoria() {
        return categoria;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Metodo para convertir a JSON
    @Override
    public JSONObject toJSON() {
        JSONObject producto = new JSONObject();
        try {
            producto.put("id", id);
            producto.put("nombre", nombre);
            producto.put("precio", precio);
            producto.put("unidades", unidades);
            producto.put("categoria", categoria);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id &&
                Double.compare(producto.precio, precio) == 0 &&
                unidades == producto.unidades &&
                Objects.equals(nombre, producto.nombre) &&
                Objects.equals(categoria, producto.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, precio, unidades, categoria);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", unidades=" + unidades +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
