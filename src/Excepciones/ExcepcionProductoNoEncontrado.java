package Excepciones;

public class ExcepcionProductoNoEncontrado extends RuntimeException {
    public ExcepcionProductoNoEncontrado(String message) {
        super(message);
    }
}
