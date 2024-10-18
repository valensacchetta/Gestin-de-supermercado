package Clases;

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
}
