package Clases;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Gestion_ventas {
    private List<Venta> listaDeVentas;
    private final String archivoJson = "ventas.json";

    public Gestion_ventas() {
        listaDeVentas = new ArrayList<>();
        cargarVentasDesdeArchivo();  // Cargar ventas cuando se cree una instancia de la clase
    }

    // Método para agregar una venta
    public void agregarVenta(LocalDateTime fecha, Cliente cliente, List<Producto> productos) {
        // Calcular el total
        double total = productos.stream().mapToDouble(Producto::getPrecio).sum();

        // Crear una nueva venta
        Venta nuevaVenta = new Venta(fecha, cliente, total);
        nuevaVenta.getListaProductos().addAll(productos);

        // Agregarla a la lista
        listaDeVentas.add(nuevaVenta);
        System.out.println("Venta registrada correctamente. ID: " + nuevaVenta.getIdVenta());

        // Guardar ventas en archivo después de agregar una nueva
        guardarVentasEnArchivo();
    }

    // Método para mostrar todas las ventas
    public void mostrarVentas() {
        if (listaDeVentas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        } else {
            for (Venta venta : listaDeVentas) {
                System.out.println(venta);
            }
        }
    }

    // Método para buscar una venta por ID
    public Venta buscarVentaPorID(int idVenta) {
        for (Venta venta : listaDeVentas) {
            if (venta.getIdVenta() == idVenta) {
                return venta;
            }
        }
        System.out.println("Venta con ID " + idVenta + " no encontrada.");
        return null;
    }

    // Método para calcular el total vendido
    public double calcularTotalVendido() {
        return listaDeVentas.stream().mapToDouble(Venta::getTotal).sum();
    }

    // Método para guardar las ventas en un archivo JSON
    public void guardarVentasEnArchivo() {
        JSONArray ventasArray = new JSONArray();
        for (Venta venta : listaDeVentas) {
            ventasArray.put(venta.toJSON());  // Serializa cada venta
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ventas", ventasArray);

        try (FileWriter file = new FileWriter(archivoJson)) {
            file.write(jsonObject.toString(4));  // Escribe el JSON con una indentación de 4 espacios
            System.out.println("Ventas guardadas correctamente en el archivo " + archivoJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar las ventas desde un archivo JSON
    public void cargarVentasDesdeArchivo() {
        File archivo = new File(archivoJson);
        if (archivo.exists()) {
            try {
                String contenido = new String(java.nio.file.Files.readAllBytes(archivo.toPath()));
                JSONObject jsonObject = new JSONObject(contenido);
                JSONArray ventasArray = jsonObject.getJSONArray("ventas");

                for (int i = 0; i < ventasArray.length(); i++) {
                    JSONObject jsonVenta = ventasArray.getJSONObject(i);
                    Venta venta = new Venta(jsonVenta);  // Deserializa cada venta
                    listaDeVentas.add(venta);
                }
                System.out.println("Ventas cargadas correctamente desde el archivo.");
            } catch (IOException | org.json.JSONException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se encontró el archivo de ventas, comenzando con una lista vacía.");
        }
    }
}
