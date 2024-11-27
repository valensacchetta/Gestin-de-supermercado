package Clases;

import Interfaces.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public abstract class Persona implements Serializable {
    private String dni;
    private String nombre;
    private String Apellido;


    public Persona(JSONObject jsonPersona) {
        try{
            dni = jsonPersona.getString("dni");
            nombre = jsonPersona.getString("nombre");
            Apellido = jsonPersona.getString("apellido");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + Apellido + '\'' +
                '}';
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = null;

        try{
            jsonObject = new JSONObject();

            jsonObject.put("dni", dni);
            jsonObject.put("nombre", nombre);
            jsonObject.put("apellido", Apellido);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(dni, persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }
}
