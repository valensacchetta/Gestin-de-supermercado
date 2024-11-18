package Clases;

import java.util.Objects;

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

    //Metodos

    public static int getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public int getTelefono() {
        return telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return telefono == cliente.telefono && Objects.equals(correoElectronico, cliente.correoElectronico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(correoElectronico, telefono);
    }


}
