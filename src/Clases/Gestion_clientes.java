package Clases;

import java.util.ArrayList;
import java.util.List;

public class Gestion_clientes {

    private static List<Cliente> listaClientes= new ArrayList<>();

    public Cliente buscarClientePorDNI(String dni) {
        for (Cliente cliente : listaClientes) { // listaClientes de clientes registrados
            if (cliente.getDni().equals(dni)) {
                return cliente;
            }
        }
        return null; //si no encuentra al cliente
    }

    public void agregarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public void eliminarCliente(String dni) {
        Cliente cliente = buscarClientePorDNI(dni);
        cliente.setStatus(0);
        System.out.println("Cliente dado de baja correctamente");
    }
}
public Cliente buscarClientePorID(int id) {
    try {
        for (Cliente cliente : listaClientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        throw new Exception("Cliente no encontrado");
    } catch (Exception e) {
        System.out.println("Error al buscar cliente: " + e.getMessage());
        return null;
    }
public Cliente obtenerCliente(int indice) {
    try {
        return listaClientes.get(indice);
    } catch (IndexOutOfBoundsException e) {
        System.out.println("Índice fuera de rango: " + e.getMessage());
        return null;
    }
}

}

