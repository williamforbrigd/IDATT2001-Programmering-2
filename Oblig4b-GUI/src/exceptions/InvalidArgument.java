package exceptions;

/**
 * An exception class that inherits IllegalArgumentException. The exception is a unchecked exception, because it
 * is not checked by the compiler. In that way, the programmer has the full responsibility to ensure that the program
 * handles this exception. An IllegalArgumentException is a runtime exception, because the exception can happen under runtime
 * when an invalid input parameter is being sent in by the user of the system. This is a problem that we do not know
 * under compilationtime, and it happens after the program has compiled. Therefore it is impportant for the programmer
 * to give the user a meaningful message of the problem, and ensure that the program does not crash when this exception
 * is being caught.
 */

public class InvalidArgument extends IllegalArgumentException {

    /**
     * Creates an instance of the exception.
     * @param message the message to be given to the user.
     * @param cause
     */

    public InvalidArgument(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidArgument(String message) {
        super(message);
    }
}
