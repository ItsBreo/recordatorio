package azael.josue.recordatorio.exception;

// Excepción personalizada para manejar casos donde un recurso no se encuentra
public class NotFoundException extends RuntimeException {
    public NotFoundException(String mensaje) {
        super(mensaje);
    }
}
