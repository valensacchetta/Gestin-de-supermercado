package Clases;

public abstract class Persona {
    private String dni;
    private String nombre;
    private String Apellido;

    public Persona(String dni, String nombre, String apellido) {
        if (dni.matches("\\d{8}")) { // Valida que el DNI tenga 8 dígitos.
            this.dni = dni;
        } else {
            throw new IllegalArgumentException("DNI inválido.");
        }
        this.nombre = nombre;
        Apellido = apellido;
    }
    public Persona(){}

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return Apellido;
    }
    @Override
    public String toString() {
        return "Persona{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + Apellido + '\'' +
                '}';
    }

}
