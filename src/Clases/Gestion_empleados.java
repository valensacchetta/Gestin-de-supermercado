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

public class Gestion_empleados {
    private Map<String, Empleado> empleados; // Mapa para almacenar empleados por DNI
    private final String archivoJson = "empleados.json"; // Nombre del archivo JSON

    public Gestion_empleados() {
        empleados = new HashMap<>();
        cargarEmpleadosDesdeArchivo(); // Cargar empleados desde json
    }

    public void agregarEmpleado(Empleado empleado) {
        if (!empleados.containsKey(empleado.getDni())) {
            empleados.put(empleado.getDni(), empleado);
            guardarEmpleadosEnArchivo();
            System.out.println("Empleado agregado correctamente.");
        } else {
            System.out.println("El empleado con DNI " + empleado.getDni() + " ya está registrado.");
        }
    }

    public Empleado buscarEmpleadoPorDNI(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede ser nulo o vacío.");
        }
        Empleado empleado = this.empleados.get(dni);
        if (empleado == null) {
            throw new ExcepcionClienteNoEncontrado("Empleado con DNI " + dni + " no encontrado.");
        }
        return empleado;
    }

    public void mostrarEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (Empleado empleado : empleados.values()) {
                System.out.println(empleado);
            }
        }
    }

    // Metodo para eliminar un empleado. A este lo eliminamos definitivamente ya que consideramos que en caso de que volviese, haria un nuevo contrato.
    public void eliminarEmpleado(String dni) {
        if (empleados.containsKey(dni)) {
            empleados.remove(dni);
            guardarEmpleadosEnArchivo();
            System.out.println("Empleado con DNI " + dni + " eliminado.");
        } else {
            System.out.println("No se encontró un empleado con el DNI " + dni);
        }
    }

    private void guardarEmpleadosEnArchivo() {
        JSONArray arrayEmpleados = new JSONArray();
        for (Empleado empleado : empleados.values()) {
            arrayEmpleados.put(empleado.toJSON());
        }
        try (FileWriter file = new FileWriter(archivoJson)) {
            file.write(arrayEmpleados.toString(4));
            System.out.println("Clientes guardados en " + archivoJson);
        } catch (IOException e) {
            System.err.println("Error al guardar los clientes: " + e.getMessage());
        }
    }

    private void cargarEmpleadosDesdeArchivo() {
            File file = new File(archivoJson);
            if (!file.exists()) {
                System.out.println("El archivo de clientes no existe, se creará uno nuevo");
                return;
            }
            try {
                String contenido = new String(Files.readAllBytes(file.toPath()));
                JSONArray arrayEmpleados = new JSONArray(contenido);
                for (int i = 0; i < arrayEmpleados.length(); i++) {
                    JSONObject jsonEmpleado = arrayEmpleados.getJSONObject(i);
                    Empleado empleado = new Empleado(jsonEmpleado);
                    empleados.put(empleado.getDni(), empleado);
                }
                System.out.println("Clientes cargados desde el archivo");
            } catch (Exception e) {
                System.err.println("Error al cargar los clientes: " + e.getMessage());
            }
    }

}

