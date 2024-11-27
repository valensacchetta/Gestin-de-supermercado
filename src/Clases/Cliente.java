package Clases;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Cliente extends Persona {
    //Atributos: id, nombre,apellido,dni(super), direccion, telefono, correo

    private static int id;
    private int contador;
    private String direccion;
    private String correoElectronico;
    private String telefono;
    private int status; // 1 si esta dado de alta, 0 de baja

    //Constructores
    public Cliente(String nombre,String apellido, String dni, String direccion, String correoElectronico, String telefono) {
        super(dni,nombre,apellido);
        id=++contador;
        this.status=1;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
    }

    // Constructor para deserialización desde JSON
    public Cliente(JSONObject jsonCliente) {
        super(jsonCliente); // Deserializa los atributos heredados
        try {
            this.id = jsonCliente.getInt("id");
            this.direccion = jsonCliente.getString("direccion");
            this.correoElectronico = jsonCliente.optString("correoElectronico", null);
            this.telefono = jsonCliente.getString("telefono");
            this.status = jsonCliente.getInt("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Cliente(String nombre,String apellido, String dni, String direccion, String telefono) {
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

    public String getTelefono() {
        return telefono;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // Método para serialización a JSON
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON(); // Incluye los atributos de Persona
        try {
            jsonObject.put("id", id);
            jsonObject.put("direccion", direccion);
            jsonObject.put("correoElectronico", correoElectronico);
            jsonObject.put("telefono", telefono);
            jsonObject.put("status", status);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
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
