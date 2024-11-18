import Clases.Gestion_productos;
import Clases.Producto;

public class Main {
    public static void main(String[] args) {

        Gestion_productos gestion = new Gestion_productos();

        gestion.AddProducto(new Producto("Zanahoria", 1.2, 10, "Verdura"));
        gestion.AddProducto(new Producto("Manzana", 0.5, 50, "Fruta"));
        gestion.AddProducto(new Producto("Arroz", 2.0, 20, "Grano"));

        System.out.println("Antes de ordenar:");
        gestion.mostrarLista();

        gestion.ordenarProductosPorNombre();

        System.out.println("\nDespués de ordenar:");
        gestion.mostrarLista();
    }
}