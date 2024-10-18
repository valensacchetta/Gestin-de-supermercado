package Clases;

public class Cliente extends Persona {
    //Atributos: id, nombre(super), direccion, telefono, correo

    private static int id;
    private String direccion;
    private String correoElectronico;
    private int telefono;

    //Constructores
    public Cliente(String nombre,String apellido, String dni, String direccion, String correoElectronico, int telefono) {
        super(nombre,apellido,dni);
        id++;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
    }

    public Cliente(String nombre,String apellido, String dni, String direccion, int telefono) {
        super(nombre,apellido,dni);
        id++;
        this.direccion = direccion;
        this.correoElectronico = null;
        this.telefono = telefono;
    }
}
