package Excepciones;

public class ExcepcionClienteNoEncontrado extends RuntimeException {
    public ExcepcionClienteNoEncontrado(String message) {
        super(message);
    }
}
