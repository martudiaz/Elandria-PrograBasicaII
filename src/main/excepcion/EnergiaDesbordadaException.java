package excepcion;

public class EnergiaDesbordadaException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public EnergiaDesbordadaException(String mensaje) {
        super(mensaje);
    }
}

