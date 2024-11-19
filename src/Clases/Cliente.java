package Clases;

import java.util.Objects;

public class Cliente extends Persona {
    //Atributos: id, nombre,apellido,dni(super), direccion, telefono, correo

    private static int id;
    private int contador;
    private String direccion;
    private String correoElectronico;
    private int telefono;
    private int status; // 1 si esta dado de alta, 0 de baja

    //Constructores
    public Cliente(String nombre,String apellido, String dni, String direccion, String correoElectronico, int telefono) {
        super(nombre,apellido,dni);
        id=++contador;
        this.status=1;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
    }

    public Cliente(String nombre,String apellido, String dni, String direccion, int telefono) {
        super(nombre,apellido,dni);
        id=++contador;
        this.status=1;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
