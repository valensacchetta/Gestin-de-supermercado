package Clases;

import Excepciones.ExcepcionClienteNoEncontrado;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Gestion_clientes {

    private Map<String, Cliente> clientes; // Usamos un Map con el DNI como clave
    private final String archivoJson = "clientes.json"; // Nombre del archivo JSON

    // Constructor
    public Gestion_clientes() {
        clientes = new HashMap<>();
        cargarClientesDesdeArchivo(); // Cargar los datos desde el archivo JSON
    }

    // Buscar cliente por DNI
    public Cliente buscarClientePorDNI(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede ser nulo o vacío.");
        }
        Cliente cliente = clientes.get(dni);
        if (cliente == null) {
            throw new ExcepcionClienteNoEncontrado("Cliente con DNI " + dni + " no encontrado.");
        }
        if (cliente.getStatus()==0){
            throw new ExcepcionClienteNoEncontrado("Cliente dado de baja.");
        }
        return cliente;
    }

    // Agregar un cliente nuevo
    public void agregarCliente(Cliente cliente) {
        if (cliente == null || cliente.getDni() == null || cliente.getDni().trim().isEmpty()) {
            throw new IllegalArgumentException("El cliente o su DNI no pueden ser nulos.");
        }
        if (clientes.containsKey(cliente.getDni()) && clientes.get(cliente.getDni()).getStatus() != 0) {
            throw new IllegalStateException("El cliente con DNI " + cliente.getDni() + " ya está registrado.");
        }
        cliente.setStatus(1); // Asegurar que el cliente se agrega como activo
        clientes.put(cliente.getDni(), cliente);
        guardarClientesEnArchivo();
        System.out.println("Cliente agregado correctamente.");
    }

    // Dar de baja a un cliente
    public void eliminarCliente(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede ser nulo");
        }
        Cliente cliente = clientes.get(dni);
        if (cliente == null) {
            throw new ExcepcionClienteNoEncontrado("Cliente con DNI " + dni + " no encontrado");
        }
        if (cliente.getStatus() == 0) {
            System.out.println("Este cliente ya está dado de baja");
            return;
        }
        cliente.setStatus(0); // Cambiar estado a baja (0 para inactivo)
        guardarClientesEnArchivo();
        System.out.println("Cliente dado de baja correctamente");
    }

    // Mostrar todos los clientes
    public void mostrarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            System.out.println("Lista de clientes activos:");
            for (Cliente cliente : clientes.values()) {
                if (cliente.getStatus() == 1) { // Solo mostrar activos
                    System.out.println("-------------------------------------------");
                    System.out.println("ID: " + cliente.getId());
                    System.out.println("DNI: " + cliente.getDni());
                    System.out.println("Nombre: " + cliente.getNombre());
                    System.out.println("Apellido: " + cliente.getApellido());
                    System.out.println("Telefono: " + cliente.getTelefono());
                    System.out.println("Correo: " + cliente.getCorreoElectronico());
                    System.out.println("Direccion: " + cliente.getDireccion());
                    System.out.println("Status: " + cliente.getStatus());
                    System.out.println("-------------------------------------------");
                }
            }
        }
    }

    // Guardar los clientes en un archivo JSON
    private void guardarClientesEnArchivo() {
        JSONArray arrayClientes = new JSONArray();
        for (Cliente cliente : clientes.values()) {
            arrayClientes.put(cliente.toJSON());
        }
        try (FileWriter file = new FileWriter(archivoJson)) {
            file.write(arrayClientes.toString(4));
            System.out.println("Clientes guardados en " + archivoJson);
        } catch (IOException e) {
            System.err.println("Error al guardar los clientes: " + e.getMessage());
        }
    }

    // Cargar los clientes desde un archivo JSON
    private void cargarClientesDesdeArchivo() {
        File file = new File(archivoJson);
        if (!file.exists()) {
            System.out.println("El archivo de clientes no existe, se creará uno nuevo.");
            return;
        }
        try {
            String contenido = new String(Files.readAllBytes(file.toPath()));
            JSONArray arrayClientes = new JSONArray(contenido);
            for (int i = 0; i < arrayClientes.length(); i++) {
                JSONObject jsonCliente = arrayClientes.getJSONObject(i);
                Cliente cliente = new Cliente(jsonCliente);
                clientes.put(cliente.getDni(), cliente);
            }
            System.out.println("Clientes cargados desde el archivo.");
        } catch (Exception e) {
            System.err.println("Error al cargar los clientes: " + e.getMessage());
        }
    }

    public void ordenarPorNombre(boolean ascendente) {
        // Clonar la lista de productos a partir de los valores del mapa
        List<Cliente> clientesLista = new ArrayList<>(clientes.values());

        // Ordenar por nombre usando un comparador anonimo
        Collections.sort(clientesLista, new Comparator<Cliente>() {
            @Override
            public int compare(Cliente c1, Cliente c2) {
                return ascendente
                        ? c1.getNombre().compareToIgnoreCase(c2.getNombre())
                        : c2.getNombre().compareToIgnoreCase(c1.getNombre());
            }
        });

        for (int i = 0; i < clientesLista.size(); i++) {
            if (clientesLista.get(i).getStatus() == 1){
                System.out.println("-------------------------------------------------");
                System.out.println(clientesLista.get(i).getId());
                System.out.println(clientesLista.get(i).getNombre());
                System.out.println(clientesLista.get(i).getApellido());
                System.out.println(clientesLista.get(i).getTelefono());
                System.out.println(clientesLista.get(i).getDni());
                System.out.println(clientesLista.get(i).getCorreoElectronico());
                System.out.println(clientesLista.get(i).getDireccion());
                System.out.println(clientesLista.get(i).getStatus());
                System.out.println("-------------------------------------------------");
            }
        }
    }

    public void editarCliente() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados para editar.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        // Mostrar la lista de clientes activos disponibles
        System.out.println("Lista de clientes activos:");
        for (Cliente cliente : clientes.values()) {
            if (cliente.getStatus() == 1) { // Solo mostrar clientes activos
                System.out.println("- DNI: " + cliente.getDni() + " | Nombre: " + cliente.getNombre());
            }
        }

        // Solicitar el DNI del cliente a editar
        System.out.print("Ingrese el DNI del cliente que desea editar: ");
        String dni = scanner.nextLine();

        // Buscar el cliente
        Cliente cliente = clientes.get(dni);
        if (cliente == null || cliente.getStatus() == 0) {
            System.out.println("Cliente con DNI '" + dni + "' no encontrado o está dado de baja.");
            return;
        }

        // Editar los atributos del cliente
        System.out.println("¿Desea modificar el nombre actual (" + cliente.getNombre() + ")? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Ingrese el nuevo nombre: ");
            cliente.setNombre(scanner.nextLine());
        }

        System.out.println("¿Desea modificar el apellido actual (" + cliente.getApellido() + ")? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Ingrese el nuevo apellido: ");
            cliente.setApellido(scanner.nextLine());
        }

        System.out.println("¿Desea modificar el teléfono actual (" + cliente.getTelefono() + ")? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Ingrese el nuevo teléfono: ");
            cliente.setTelefono(scanner.nextLine());
        }

        System.out.println("¿Desea modificar la dirección actual (" + cliente.getDireccion() + ")? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Ingrese la nueva dirección: ");
            cliente.setDireccion(scanner.nextLine());
        }

        System.out.println("¿Desea modificar el correo actual (" + cliente.getCorreoElectronico() + ")? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Ingrese el nuevo correo electrónico: ");
            cliente.setCorreoElectronico(scanner.nextLine());
        }

        // Guardar los cambios en el archivo JSON
        guardarClientesEnArchivo();
        System.out.println("Cliente editado correctamente.");
    }


}
