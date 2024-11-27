package Clases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Administrador extends Persona {

    private static int contador = 0; // Autoincremental para ID unico
    private int id;
    private final String archivoClave = "Clave.txt";

    public Administrador(String dni, String nombre, String apellido) {
        super(dni, nombre, apellido);
        this.id = ++contador;
    }

    public String cargarClaveDesdeArchivo() {
        File archivo = new File(archivoClave);
        if (!archivo.exists()) {
            System.out.println("El archivo de clave no existe.");
            return null;
        }
        try (Scanner scanner = new Scanner(new FileReader(archivo))) {
            return scanner.nextLine();
        } catch (IOException e) {
            System.out.println("Error al cargar la clave: " + e.getMessage());
            return null;
        }
    }

    public void gestionarSistema(Gestion_empleados gestionEmpleados, Gestion_productos gestionProductos, Gestion_ventas gestionVentas, Gestion_clientes gestionClientes) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nBienvenido, " + this.getNombre() + ". Seleccione una opción:");
            System.out.println("1. Gestionar empleados");
            System.out.println("2. Gestionar productos");
            System.out.println("3. Gestionar ventas");
            System.out.println("4. Gestionar clientes");
            System.out.println("5. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    gestionarEmpleados(gestionEmpleados);
                    break;
                case 2:
                    gestionarProductos(gestionProductos);
                    break;
                case 3:
                    gestionarVentas(gestionVentas);
                    break;
                case 4:
                    gestionarClientes(gestionClientes);
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    continuar = false;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion no valida, intente de nuevo.");
            }
        }
    }

    private void gestionarClientes(Gestion_clientes gestionClientes) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nGestion de clientes:");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Dar de baja cliente");
            System.out.println("3. Buscar cliente por DNI");
            System.out.println("4. Mostrar clientes activos");
            System.out.println("5. Editar datos de un cliente");
            System.out.println("6. Volver");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el DNI del cliente:");
                    String dni = scanner.nextLine();
                    System.out.println("Ingrese el nombre:");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese el apellido:");
                    String apellido = scanner.nextLine();
                    System.out.println("Ingrese la dirección:");
                    String direccion = scanner.nextLine();
                    System.out.println("Ingrese el email:");
                    String email = scanner.nextLine();
                    System.out.println("Ingrese el telefono:");
                    String telefono = scanner.nextLine();
                    scanner.nextLine();

                    gestionClientes.agregarCliente(new Cliente(nombre, apellido, dni, direccion, email, telefono));
                    break;
                case 2:
                    System.out.println("Ingrese el DNI del cliente a dar de baja:");
                    String dniEliminar = scanner.nextLine();
                    gestionClientes.eliminarCliente(dniEliminar);
                    break;
                case 3:
                    System.out.println("Ingrese el DNI del cliente a buscar:");
                    String dniBuscar = scanner.nextLine();
                    Cliente cliente = gestionClientes.buscarClientePorDNI(dniBuscar);
                    if (cliente != null && cliente.getStatus() == 1) {
                        System.out.println(cliente);
                    }
                    break;
                case 4:
                    System.out.println("Como quiere ver a los clientes?");
                    System.out.println("1. Como se fueron guardando");
                    System.out.println("2. Ordenados por nombre");
                    Scanner scanner2 = new Scanner(System.in);
                    int opcion2 = scanner2.nextInt();

                    switch (opcion2) {
                        case 1:
                            gestionClientes.mostrarClientes();
                            break;
                        case 2:
                            System.out.println("De forma ascendente o descendente");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            int opcion3 = scanner2.nextInt();

                            if (opcion3 == 1) {
                                gestionClientes.ordenarPorNombre(true);
                            }
                            else if (opcion3 == 2) {
                                gestionClientes.ordenarPorNombre(false);
                            }
                            else {
                                System.out.println("Ingrese un numero valido");
                            }
                            break;
                        default:
                            System.out.println("Opcion no valida, intente de nuevo.");
                            break;
                    }
                    break;
                case 5:
                    gestionClientes.editarCliente();
                    break;
                case 6:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion no valida, intente de nuevo.");
            }
        }
    }

    private void gestionarEmpleados(Gestion_empleados gestionEmpleados) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nGestion de empleados:");
            System.out.println("1. Agregar empleado");
            System.out.println("2. Eliminar empleado");
            System.out.println("3. Buscar empleado por DNI");
            System.out.println("4. Mostrar todos los empleados");
            System.out.println("5. Volver");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el DNI del empleado:");
                    String dni = scanner.nextLine();
                    System.out.println("Ingrese el nombre:");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese el apellido:");
                    String apellido = scanner.nextLine();
                    System.out.println("Ingrese el cargo:");
                    String cargo = scanner.nextLine();
                    System.out.println("Ingrese el salario:");
                    double salario = scanner.nextDouble();
                    scanner.nextLine();
                    gestionEmpleados.agregarEmpleado(new Empleado(dni, nombre, apellido, cargo, salario));
                    break;
                case 2:
                    System.out.println("Ingrese el DNI del empleado a eliminar:");
                    String dniEliminar = scanner.nextLine();
                    gestionEmpleados.eliminarEmpleado(dniEliminar);
                    break;
                case 3:
                    System.out.println("Ingrese el DNI del empleado a buscar:");
                    String dniBuscar = scanner.nextLine();
                    Empleado empleado = gestionEmpleados.buscarEmpleadoPorDNI(dniBuscar);
                    if (empleado != null) {
                        System.out.println(empleado);
                    } else {
                        System.out.println("Empleado no encontrado.");
                    }
                    break;
                case 4:
                    System.out.println("Como quiere ver los empleados?");
                    System.out.println("1. Como se fueron guardando");
                    System.out.println("2. Ordenados por nombre");
                    Scanner scanner2 = new Scanner(System.in);
                    int opcion2 = scanner2.nextInt();

                    switch (opcion2) {
                        case 1:
                            gestionEmpleados.mostrarEmpleados();
                            break;
                        case 2:
                            System.out.println("De forma ascendente o descendente");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            int opcion3 = scanner2.nextInt();

                            if (opcion3 == 1) {
                                gestionEmpleados.ordenarPorNombre(true);
                            }
                            else if (opcion3 == 2) {
                                gestionEmpleados.ordenarPorNombre(false);
                            }
                            else {
                                System.out.println("Ingrese un numero valido");
                            }
                            break;
                        default:
                            System.out.println("Opcion no valida, intente de nuevo.");
                            break;
                    }
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion no valida, intente de nuevo.");
            }
        }
    }

    private void gestionarProductos(Gestion_productos gestionProductos) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nGestion de productos:");
            System.out.println("1. Agregar producto");
            System.out.println("2. Eliminar producto");
            System.out.println("3. Buscar producto por nombre");
            System.out.println("4. Mostrar todos los productos");
            System.out.println("5. Editar un producto");
            System.out.println("6. Volver");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre del producto:");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese el precio:");
                    double precio = scanner.nextDouble();
                    System.out.println("Ingrese las unidades disponibles:");
                    int unidades = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese la categoría:");
                    String categoria = scanner.nextLine();

                    gestionProductos.addProducto(new Producto(nombre, precio, unidades, categoria));
                    break;
                case 2:
                    System.out.println("Ingrese el nombre del producto a eliminar:");
                    String prodEliminar = scanner.nextLine();
                    gestionProductos.deleteProducto(prodEliminar);
                    break;
                case 3:
                    System.out.println("Ingrese el nombre del producto a buscar:");
                    String prodBuscar = scanner.nextLine();
                    Producto producto = gestionProductos.buscarProductoPorNombre(prodBuscar);
                    if (producto != null) {
                        System.out.println(producto);
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;
                case 4:
                    System.out.println("Como quiere ver los productos?");
                    System.out.println("1. Como se fueron guardando");
                    System.out.println("2. Ordenados por nombre");
                    System.out.println("3. Ordenados por precio");
                    Scanner scanner2 = new Scanner(System.in);
                    int opcion2 = scanner2.nextInt();

                    switch (opcion2) {
                        case 1:
                            gestionProductos.mostrarLista();
                            break;
                        case 2:
                            System.out.println("De forma ascendente o descendente");
                            System.out.println("1. ascendente");
                            System.out.println("2. descendente");
                            int opcion3 = scanner2.nextInt();

                            if (opcion3 == 1) {
                                gestionProductos.ordenarPorNombre(true);
                            }
                            else if (opcion3 == 2) {
                                gestionProductos.ordenarPorNombre(false);
                            }
                            break;
                        case 3:
                            System.out.println("De forma ascendente o descendente");
                            System.out.println("1. ascendente");
                            System.out.println("2. descendente");
                            int opcion4 = scanner2.nextInt();

                            if (opcion4 == 1) {
                                gestionProductos.ordenarPorPrecio(true);
                            }
                            else if (opcion4 == 2) {
                                gestionProductos.ordenarPorPrecio(false);
                            }
                            break;
                        default:
                            System.out.println("Opcion no valida, intente de nuevo.");
                            break;
                    }
                    break;
                case 5:
                    gestionProductos.editarProducto();
                case 6:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion no valida, intente de nuevo.");
            }
        }
    }

    private void gestionarVentas(Gestion_ventas gestionVentas) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nGestion de ventas:");
            System.out.println("1. Mostrar todas las ventas");
            System.out.println("2. Buscar venta por ID");
            System.out.println("3. Calcular total vendido");
            System.out.println("4. Volver");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    gestionVentas.mostrarVentas();
                    break;
                case 2:
                    System.out.println("Ingrese el ID de la venta a buscar:");
                    int idVenta = scanner.nextInt();
                    scanner.nextLine();
                    Venta venta = gestionVentas.buscarVentaPorID(idVenta);
                    if (venta != null) {
                        System.out.println(venta);
                    } else {
                        System.out.println("Venta no encontrada.");
                    }
                    break;
                case 3:
                    System.out.println("El total vendido es: $" + gestionVentas.calcularTotalVendido());
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion no valida, intente de nuevo.");
            }
        }
    }
}