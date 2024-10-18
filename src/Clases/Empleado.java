package Clases;

public class Empleado extends Persona{

    //Atributos: id, nombre(super), cargo, salario

    private static int id;
    private String cargo;
    private double salario;

    ///Constructores


    public Empleado(String dni, String nombre, String apellido, String cargo, double salario) {
        super(dni, nombre, apellido);
        this.cargo = cargo;
        this.salario = salario;
        id++;
    }
}
