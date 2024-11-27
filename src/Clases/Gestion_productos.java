package Clases;

import Excepciones.ExcepcionProductoNoEncontrado;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Gestion_productos {
    private static Map<String, Producto> listaDeProductos; // Mapa con el nombre como clave
    private final String archivoProductos = "productos.json"; // Ruta del archivo para guardar/cargar

    public Gestion_productos() {
        listaDeProductos = new HashMap<>();
        cargarDesdeArchivo(); // Carga los productos al inicializar
    }

    // Métodos para manejar productos
    public void addProducto(Producto producto) {
        if (producto == null || producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El producto o su nombre no pueden ser nulos.");
        }

        if (listaDeProductos.containsKey(producto.getNombre())) {
            Producto productoExistente = listaDeProductos.get(producto.getNombre());
            if (productoExistente.getUnidades() != producto.getUnidades()) {
                listaDeProductos.put(producto.getNombre(), producto); // Reemplaza el producto que ya existe
                guardarEnArchivo();
                System.out.println("Producto reemplazado con las nuevas unidades.");
            } else {
                System.out.println("Ya existe un producto con el mismo nombre y cantidad.");
            }
        } else {
            listaDeProductos.put(producto.getNombre(), producto); // Agregar el nuevo producto
            guardarEnArchivo();
            System.out.println("Producto agregado correctamente.");
        }
    }


    public void deleteProducto(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacío.");
        }
        Producto eliminado = listaDeProductos.remove(nombre);
        if (eliminado != null) {
            guardarEnArchivo();
            System.out.println("Producto eliminado correctamente.");
        } else {
            throw new ExcepcionProductoNoEncontrado("Producto con nombre " + nombre + " no encontrado.");
        }
    }

    public void mostrarLista() {
        if (listaDeProductos.isEmpty()) {
            System.out.println("La lista de productos está vacía.");
        } else {
            for (Producto producto : listaDeProductos.values()) {
                System.out.println("-------------------------------------------------");
                System.out.println(producto.getNombre());
                System.out.println(producto.getId());
                System.out.println(producto.getPrecio());
                System.out.println(producto.getCategoria());
                System.out.println(producto.getUnidades());
                System.out.println("-------------------------------------------------");
            }
        }
    }

    public Producto buscarProductoPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacío.");
        }
        Producto producto = listaDeProductos.get(nombre);
        if (producto == null) {
            throw new ExcepcionProductoNoEncontrado("Producto con nombre " + nombre + " no encontrado.");
        }
        return producto;
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
            System.err.println("Error al guardar el archivo: " + e.getMessage());
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

    public void ordenarPorNombre(boolean ascendente) {
        // Clonar la lista de productos a partir de los valores del mapa
        List<Producto> productosOrdenados = new ArrayList<>(listaDeProductos.values());

        // Ordenar por nombre usando un comparador anonimo
        Collections.sort(productosOrdenados, new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                return ascendente
                        ? p1.getNombre().compareToIgnoreCase(p2.getNombre())
                        : p2.getNombre().compareToIgnoreCase(p1.getNombre());
            }
        });

        for (int i = 0; i < productosOrdenados.size(); i++) {
            System.out.println("-------------------------------------------------");
            System.out.println(productosOrdenados.get(i).getId());
            System.out.println(productosOrdenados.get(i).getNombre());
            System.out.println(productosOrdenados.get(i).getPrecio());
            System.out.println(productosOrdenados.get(i).getCategoria());
            System.out.println(productosOrdenados.get(i).getUnidades());
            System.out.println("-------------------------------------------------");
        }
    }

    public void ordenarPorPrecio(boolean ascendente) {
        if (listaDeProductos.isEmpty()) {
            System.out.println("La lista de productos está vacía. No hay nada que ordenar.");
            return;
        }

        // Convertir los valores del mapa a una lista
        List<Producto> productosOrdenados = new ArrayList<>(listaDeProductos.values());

        // Ordenar por precio utilizando una expresión lambda
        productosOrdenados.sort(new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                return ascendente
                        ? Double.compare(p1.getPrecio(), p2.getPrecio())
                        : Double.compare(p2.getPrecio(), p1.getPrecio());
            }
        });

        // Mostrar los productos ordenados
        System.out.println("Productos ordenados por precio " + (ascendente ? "(ascendente):" : "(descendente):"));
        for (Producto producto : productosOrdenados) {
            System.out.println("-------------------------------------------------");
            System.out.println(producto.getNombre());
            System.out.println(producto.getId());
            System.out.println(producto.getPrecio());
            System.out.println(producto.getCategoria());
            System.out.println(producto.getUnidades());
            System.out.println("-------------------------------------------------");
        }
    }



}
