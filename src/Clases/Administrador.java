package Clases;

public class Administrador extends Persona{

    private static int id;

    public Administrador(String dni, String nombre, String apellido) {
        super(dni, nombre, apellido);
        id++;
    }
}
