package Clases;

import Excepciones.ExcepcionVentaNoEncontrada;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Gestion_ventas {
    private Set<Venta> listaDeVentas;
    private final String archivoJson = "ventas.json";

    public Gestion_ventas() {
        listaDeVentas = new HashSet<>();
        cargarVentasDesdeArchivo();  // Cargar ventas cuando se cree una instancia
    }

    // Metodo para agregar una venta
    public void agregarVenta(LocalDateTime fecha, Cliente cliente, List<Producto> productos) {
        if (fecha == null || cliente == null || productos == null || productos.isEmpty()) {
            throw new IllegalArgumentException("Los datos de la venta no pueden estar vacíos.");
        }

        double total = productos.stream().mapToDouble(Producto::getPrecio).sum();
        Venta nuevaVenta = new Venta(fecha, cliente, total);
        nuevaVenta.getListaProductos().addAll(productos);

        listaDeVentas.add(nuevaVenta);
        System.out.println("Venta registrada correctamente. ID: " + nuevaVenta.getIdVenta());

        guardarVentasEnArchivo();
    }


    //mostrar todas las ventas
    public void mostrarVentas() {
        if (listaDeVentas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        } else {
            for (Venta venta : listaDeVentas) {
                System.out.println(venta);
            }
        }
    }

    //buscar una venta por ID
    public Venta buscarVentaPorID(int idVenta) {
        for (Venta venta : listaDeVentas) {
            if (venta.getIdVenta() == idVenta) {
                return venta;
            }
        }
        throw new ExcepcionVentaNoEncontrada("Venta con ID " + idVenta + " no encontrada.");
    }

    //calcular el total vendido hasta el momento
    public double calcularTotalVendido() {
        return listaDeVentas.stream().mapToDouble(Venta::getTotal).sum();
    }


    // guardar las ventas en un archivo JSON
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

    //cargar las ventas desde un archivo JSON
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
