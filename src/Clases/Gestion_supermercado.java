package Clases;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Gestion_supermercado {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al Supermercado Luna"); //Nombre cualquiera de supermercado
        System.out.println("Seleccione su opcion:");
        System.out.println("1. Administrador");
        System.out.println("2. Empleado");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        LocalDateTime horaInicio = LocalDateTime.now(); //Tal vez hay q ponerlo en las distintas clases

        switch (opcion) {
            case 1:
                iniciarSesionAdministrador(horaInicio);
                break;
            case 2:
                Persona empleado=new Empleado(); //Crear Buscador de empleado de alguna lista o cargarlo por Json
                if (empleado instanceof Cajero) {
                    iniciarSesionCajero((Cajero) empleado, horaInicio);
                }
                break;
            default:
                System.out.println("Opción inválida. Saliendo..");
        }
    }

    private static void iniciarSesionAdministrador(LocalDateTime horaInicio) {
        System.out.println("Inicio de sesión como Administrador");
        System.out.println("Hora de inicio: " + horaInicio);
        //tal vez mover a clase Administrador

    }

    private static void iniciarSesionCajero(Cajero cajero, LocalDateTime horaInicio) {
        System.out.println("Cajero: " + cajero.getNombre() + " " + cajero.getApellido());
        System.out.println("Hora de inicio: " + horaInicio);

        // tal vez mover a Cajero
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese 'salir' para cerrar la caja:");
        String comando = scanner.nextLine();

        if (comando.equalsIgnoreCase("salir")) {
            cajero.cerrarCaja();
        }
    }

}
