package Clases;

import org.json.JSONException;
import org.json.JSONObject;

public class Empleado extends Persona{

    //Atributos: id, nombre(super), cargo, salario
    private static int contador=0;
    private int id;
    private String cargo;
    private double salario;

    //Constructores

    public Empleado(String dni, String nombre, String apellido, String cargo, double salarioHora) {
        super(dni, nombre, apellido);
        this.cargo = cargo;
        this.salario = salarioHora;
        this.id=++contador;
    }

    public Empleado(JSONObject jsonEmpleado) {
        super(jsonEmpleado);
        try {
            this.id = jsonEmpleado.getInt("id");
            this.cargo = jsonEmpleado.getString("cargo");
            this.salario = jsonEmpleado.getDouble("salario");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public String getCargo() {
        return cargo;
    }

    public double getSalarioHora() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return super.toString() +
                "cargo='" + cargo + '\'' +
                ", salario=" + salario;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON(); // Llamar al metodo toJSON de la clase padre
        try {
            jsonObject.put("id", id);
            jsonObject.put("cargo", cargo);
            jsonObject.put("salario", salario);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
