package Clases;

import java.util.Objects;

public class Producto {
    private static int id;
    private String nombre;
    private double precio;
    private int unidades;
    private String categoria;

    //constructor

    public Producto(String nombre, double precio, int unidades, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.unidades = unidades;
        this.categoria = categoria;
        id++;
    }
    //Getters
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public int getUnidades() {
        return unidades;
    }
    public String getCategoria() {
        return categoria;
    }
    public double getPrecio() {
        return precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Double.compare(precio, producto.precio) == 0 && unidades == producto.unidades && Objects.equals(nombre, producto.nombre) && Objects.equals(categoria, producto.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, precio, unidades, categoria);
    }
}
