package Clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Gestion_productos {
    private ArrayList<Producto> listaDeProductos;

    public Gestion_productos() {
        listaDeProductos = new ArrayList<>();
    }

    public void AddProducto(Producto producto) {
        listaDeProductos.add(producto);
        System.out.println("Se guardó el producto correctamente");
    }

    public void deleteProducto(Producto producto) {
        listaDeProductos.remove(producto);
    }

    public void deleteProducto(int index) {
        listaDeProductos.remove(index);
    }

    public void mostrarLista() {
        for (int i = 0; i < listaDeProductos.size(); i++) {
            Producto p = listaDeProductos.get(i);
            System.out.println(i + " - " + p.getNombre() + " | Precio: " + p.getPrecio() + " | Unidades: " + p.getUnidades() + " | Categoría: " + p.getCategoria());
        }
    }

    public boolean buscarProducto(Producto producto) {
        return listaDeProductos.contains(producto);
    }

    public Producto buscarProductoPorID(int id) {
        for (Producto producto : listaDeProductos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

    // Nuevo método para ordenar productos alfabéticamente por nombre
    public void ordenarProductosPorNombre() {
        listaDeProductos.sort(Comparator.comparing(Producto::getNombre));
        System.out.println("Lista de productos ordenada alfabéticamente por nombre.");
    }

    public void editarProducto(int id, String nuevoNombre, double nuevoPrecio, int nuevasUnidades, String nuevaCategoria) {
        Producto producto = buscarProductoPorID(id); // Buscar el producto por ID
        if (producto != null) {
            // Actualizar los atributos del producto
            if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                producto.setNombre(nuevoNombre);
            }
            if (nuevoPrecio >= 0) {
                producto.setPrecio(nuevoPrecio);
            }
            if (nuevasUnidades >= 0) {
                producto.setUnidades(nuevasUnidades);
            }
            if (nuevaCategoria != null && !nuevaCategoria.isEmpty()) {
                producto.setCategoria(nuevaCategoria);
            }
            System.out.println("Producto editado correctamente.");
        } else {
            System.out.println("Producto con ID " + id + " no encontrado.");
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

}
