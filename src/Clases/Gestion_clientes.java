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
