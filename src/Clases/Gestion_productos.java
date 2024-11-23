package Clases;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Gestion_productos {
    private Map<String, Producto> listaDeProductos; // Mapa con el nombre como clave
    private final String archivoProductos = "productos.json";         // Ruta del archivo para guardar/cargar

    public Gestion_productos() {
        listaDeProductos = new HashMap<>();
        cargarDesdeArchivo(); // Carga los productos al inicializar
    }

    // Métodos para manejar productos
    public void addProducto(Producto producto) {
        if (!listaDeProductos.containsKey(producto.getNombre())) {
            listaDeProductos.put(producto.getNombre(), producto);
            System.out.println("Producto agregado correctamente.");
            guardarEnArchivo(); // Guarda automáticamente
        } else {
            System.out.println("Ya existe un producto con el nombre: " + producto.getNombre());
        }
    }

    public void deleteProducto(String nombre) {
        if (listaDeProductos.remove(nombre) != null) {
            System.out.println("Producto eliminado correctamente.");
            guardarEnArchivo(); // Guarda automáticamente
        } else {
            System.out.println("No se encontró un producto con el nombre: " + nombre);
        }
    }

    public void mostrarLista() {
        if (listaDeProductos.isEmpty()) {
            System.out.println("La lista de productos está vacía.");
            return;
        }
        for (Producto producto : listaDeProductos.values()) {
            System.out.println(producto);
        }
    }

    public Producto buscarProductoPorNombre(String nombre) {
        return listaDeProductos.get(nombre);
    }

    // Serialización
    private void guardarEnArchivo() {
        JSONArray jsonArray = new JSONArray();
        for (Producto producto : listaDeProductos.values()) {
            jsonArray.put(producto.toJSON());
        }
        try (FileWriter file = new FileWriter(archivoProductos)) {
            file.write(jsonArray.toString(4));
            System.out.println("Productos guardados en: " + archivoProductos);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    // Deserialización
    private void cargarDesdeArchivo() {
        File archivo = new File(archivoProductos);
        if (!archivo.exists()) {
            System.out.println("El archivo inexistente. Se creará uno nuevo cuando se guarde.");
            return;
        }
        try (Scanner scanner = new Scanner(new FileReader(archivoProductos))) {
            StringBuilder jsonString = new StringBuilder();
            while (scanner.hasNextLine()) {
                jsonString.append(scanner.nextLine());
            }
            JSONArray jsonArray = new JSONArray(jsonString.toString());
            listaDeProductos.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Producto producto = new Producto(jsonObject);
                listaDeProductos.put(producto.getNombre(), producto);
            }
            System.out.println("Productos cargados desde: " + archivoProductos);
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
        }
    }

    /*
    public void editarProducto() {
        if (listaDeProductos.isEmpty()) {
            System.out.println("No hay productos en la lista para editar.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Lista de productos:");
        mostrarLista();

        System.out.print("Ingrese el índice del producto que desea editar: ");
        int indice;
        try {
            indice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Entrada inválida. Debe ingresar un número.");
            return;
        }

        if (indice < 0 || indice >= listaDeProductos.size()) {
            System.out.println("Índice fuera de rango. Intente nuevamente.");
            return;
        }

        Producto producto = listaDeProductos.get(indice);

        scanner.nextLine();

        System.out.println("¿Desea modificar el nombre actual (" + producto.getNombre() + ")? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Ingrese el nuevo nombre: ");
            producto.setNombre(scanner.nextLine());
        }

        System.out.println("¿Desea modificar el precio actual (" + producto.getPrecio() + ")? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Ingrese el nuevo precio: ");
            try {
                producto.setPrecio(scanner.nextDouble());
            } catch (Exception e) {
                System.out.println("Entrada inválida. El precio no se actualizó.");
                scanner.nextLine();
            }
        }

        System.out.println("¿Desea modificar las unidades actuales (" + producto.getUnidades() + ")? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Ingrese la nueva cantidad de unidades: ");
            try {
                producto.setUnidades(scanner.nextInt());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Las unidades no se actualizaron.");
                scanner.nextLine();
            }
        }

        scanner.nextLine();

        System.out.println("¿Desea modificar la categoría actual (" + producto.getCategoria() + ")? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Ingrese la nueva categoría: ");
            producto.setCategoria(scanner.nextLine());
        }

        System.out.println("Producto editado correctamente.");
    }

     */



}
