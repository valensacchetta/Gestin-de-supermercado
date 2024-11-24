package Clases;

import Excepciones.ExcepcionClienteNoEncontrado;
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
                    System.out.println(cliente);
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
}
