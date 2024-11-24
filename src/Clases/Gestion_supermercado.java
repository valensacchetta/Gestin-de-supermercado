package Clases;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Gestion_supermercado {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al Supermercado Luna");
        System.out.println("Seleccione su opción:");
        System.out.println("1. Administrador");
        System.out.println("2. Empleado");

        int opcion = 0;
        try {
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
        } catch (Exception e) {
            System.out.println("Entrada inválida. Saliendo del sistema...");
            return;
        }

        LocalDateTime horaInicio = LocalDateTime.now();

        switch (opcion) {
            case 1:
                iniciarSesionAdministrador(horaInicio);
                break;
            case 2:
                iniciarSesionEmpleado(horaInicio);
                break;
            default:
                System.out.println("Opción inválida. Saliendo...");
        }
    }

    private static void iniciarSesionAdministrador(LocalDateTime horaInicio) {
        System.out.println("Inicio de sesión como Administrador");
        System.out.println("Hora de inicio: " + horaInicio);
        //tal vez mover a clase Administrador

    }

    private static void iniciarSesionEmpleado(LocalDateTime horaInicio) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese su DNI:");
        String dni = scanner.nextLine();

        Gestion_clientes gestionClientes = new Gestion_clientes();
        Persona empleado = gestionClientes.buscarClientePorDNI(dni);

        if (empleado == null || !(empleado instanceof Empleado)) {
            System.out.println("Empleado no encontrado");
            return;
        }

        if (empleado instanceof Cajero) {
            iniciarSesionCajero((Cajero) empleado, horaInicio);
        } else {
            System.out.println("Acceso permitido, pero no tiene tareas específicas asignadas.");
        }
    }

    private static void iniciarSesionCajero(Cajero cajero, LocalDateTime horaInicio) {
        System.out.println("Cajero: " + cajero.getNombre() + " " + cajero.getApellido());
        System.out.println("Hora de inicio: " + horaInicio);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese 'salir' para cerrar la caja:");
        String comando = scanner.nextLine();

        if (comando.equalsIgnoreCase("salir")) {
            cajero.cerrarCaja();
        }
    }

}
