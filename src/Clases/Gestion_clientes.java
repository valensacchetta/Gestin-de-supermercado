package Clases;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

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
        return clientes.get(dni); // Retorna null si no existe
    }

    // Agregar un cliente nuevo
    public void agregarCliente(Cliente cliente) {
        if (clientes.containsKey(cliente.getDni())) {
            System.out.println("El cliente con DNI " + cliente.getDni() + " ya está registrado.");
        } else {
            clientes.put(cliente.getDni(), cliente);
            guardarClientesEnArchivo(); // Guardar los cambios en el archivo
            System.out.println("Cliente agregado correctamente.");
        }
    }

    // Dar de baja a un cliente
    public void eliminarCliente(String dni) {
        Cliente cliente = clientes.get(dni);
        if (cliente != null) {
            cliente.setStatus(0); // Cambia el estado a baja
            guardarClientesEnArchivo(); // Guardar los cambios en el archivo
            System.out.println("Cliente dado de baja correctamente.");
        } else {
            System.out.println("No se encontró un cliente con el DNI " + dni + ".");
        }
    }

    // Mostrar todos los clientes
    public void mostrarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            System.out.println("Lista de clientes:");
            for (Cliente cliente : clientes.values()) {
                System.out.println(cliente);
            }
        }
    }

    // Guardar los clientes en un archivo JSON
    private void guardarClientesEnArchivo() {
        JSONArray arrayClientes = new JSONArray();
        for (Cliente cliente : clientes.values()) {
            arrayClientes.put(cliente.toJSON()); // Convertir cada cliente a JSON
        }

        try (FileWriter file = new FileWriter(archivoJson)) {
            file.write(arrayClientes.toString(4)); // Escribe el JSON con formato
            System.out.println("Clientes guardados en " + archivoJson);
        } catch (IOException e) {
            System.err.println("Error al guardar los clientes en el archivo: " + e.getMessage());
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
                clientes.put(cliente.getDni(), cliente); // Agregar al mapa
            }

            System.out.println("Clientes cargados desde el archivo.");
        } catch (Exception e) {
            System.err.println("Error al cargar los clientes desde el archivo: " + e.getMessage());
        }
    }
}
