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

    public static int getId() {
        return id;
    }

    public String getCargo() {
        return cargo;
    }

    public double getSalario() {
        return salario;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return Double.compare(salario, empleado.salario) == 0 && Objects.equals(cargo, empleado.cargo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cargo, salario);
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "cargo='" + cargo + '\'' +
                ", salario=" + salario +
                '}';
    }

    public double calcularSalario() { //MODIFICARSE SEGUN EMPLEADO
        return salario;
    }
}
