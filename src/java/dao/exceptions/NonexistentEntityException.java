package dao.exceptions;

/**
 *
 * @author elcior.carvalho
 */
public class NonexistentEntityException extends Exception {

    public NonexistentEntityException(String msg, Throwable cause) {
        super(msg, cause);
    }    
    public NonexistentEntityException(String msg) {
        super(msg);
    }
}
