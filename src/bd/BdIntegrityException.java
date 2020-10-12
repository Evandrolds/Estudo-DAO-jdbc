package bd;

/**
 *
 * @author Evandro
 */
                  // exessão personalizada
public class BdIntegrityException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    public BdIntegrityException(String msg) {
        super(msg);
    }
}
