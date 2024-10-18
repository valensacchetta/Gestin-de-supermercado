package Clases;

public abstract class Persona {
    private String dni;
    private String nombre;
    private String Apellido;

    public Persona(String dni, String nombre, String apellido) {
        this.dni = dni;
        this.nombre = nombre;
        Apellido = apellido;
    }
}
