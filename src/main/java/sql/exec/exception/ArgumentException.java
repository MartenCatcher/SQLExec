package sql.exec.exception;

/**
 * Created by mstupin on 2/22/2015.
 */
public class ArgumentException extends Exception {
    public ArgumentException() {
    }

    public ArgumentException(String message) {
        super(message);
    }

    public ArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArgumentException(Throwable cause) {
        super(cause);
    }
}
