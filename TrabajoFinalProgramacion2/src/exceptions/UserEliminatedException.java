package exceptions;

public class UserEliminatedException  extends RuntimeException {
    public UserEliminatedException() {
        super("El usuario ha sido eliminado");
    }
}
