package Clases;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Gestion_supermercado {
    static Administrador admin =new Administrador("12345678","admin","groso");

    public static void iniciar() {

        Scanner scanner = new Scanner(System.in);
        Gestion_empleados gestionEmpleados = new Gestion_empleados();
        Gestion_productos gestionProductos = new Gestion_productos();
        Gestion_ventas gestionVentas = new Gestion_ventas();
        Gestion_clientes gestionClientes = new Gestion_clientes();


        System.out.println();
        System.out.println("*************************************************");
        System.out.println("*                                               *");
        System.out.println("*         BIENVENIDO AL SUPERMERCADO LUNA       *");
        System.out.println("*                                               *");
        System.out.println("*************************************************");
        System.out.println();
        System.out.println("Seleccione su rol para iniciar sesión:");
        System.out.println("1. Administrador");
        System.out.println("2. Empleado");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                iniciarSesionAdministrador(gestionEmpleados, gestionProductos, gestionVentas, gestionClientes);
                break;
            case 2:
                iniciarSesionEmpleado(gestionEmpleados);
                break;
            default:
                System.out.println("Opción inválida. Saliendo...");
        }
    }

    private static void iniciarSesionAdministrador(Gestion_empleados gestionEmpleados, Gestion_productos gestionProductos, Gestion_ventas gestionVentas, Gestion_clientes gestionClientes) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese su DNI:");
        String dni = scanner.nextLine();

        // Busca al administrador por su DNI
        if (dni.equals(admin.getDni())) { // DNI del administrador predeterminado
            System.out.println("Ingrese la clave");
            String clave = scanner.nextLine();
            if (clave.equals(admin.cargarClaveDesdeArchivo()) && clave != null) {
                System.out.println("Bienvenido" +" "+ admin.getNombre() +" "+ admin.getApellido());
                LocalDateTime horaInicio = LocalDateTime.now();
                System.out.println("Inicio de sesión exitoso. Hora: " + horaInicio);
                admin.gestionarSistema(gestionEmpleados, gestionProductos, gestionVentas, gestionClientes);
            }else {
                System.out.println("Clave incorrecta, acceso denegado.");
            }
        } else {
            System.out.println("DNI incorrecto. Acceso denegado.");
        }
    }

    private static void iniciarSesionEmpleado(Gestion_empleados gestionEmpleados) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese su DNI:");
        String dni = scanner.nextLine();

        Empleado empleado = gestionEmpleados.buscarEmpleadoPorDNI(dni);
        if (empleado == null) {
            System.out.println("Empleado no encontrado. Acceso denegado.");
            return;
        }

        LocalDateTime horaInicio = LocalDateTime.now();
        System.out.println("Inicio de sesion exitoso. Hora: " + horaInicio);
        System.out.println("Bienvenido, " + empleado.getNombre() + " " + empleado.getApellido());

        if (empleado instanceof Cajero || empleado.getCargo().equalsIgnoreCase("cajero")) {
            iniciarSesionCajero(empleado);
        } else {
            System.out.println("No tiene tareas específicas asignadas.");
            System.exit(0);
        }
    }

    private static void iniciarSesionCajero(Empleado empleado) {
        Scanner scanner = new Scanner(System.in);
        Cajero cajero = new Cajero(empleado.getNombre(), empleado.getApellido(), empleado.getDni(), empleado.getCargo(), empleado.getSalarioHora());
        System.out.println("Bienvenido");
        System.out.println(empleado.getNombre() + " " + empleado.getApellido());
        boolean continuar = true;
        while (continuar) {
            try{
                cajero.mostrarMenuCajero();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
