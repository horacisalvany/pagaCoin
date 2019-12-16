package api.core.error;

public class UserIdMismatchException extends RuntimeException {

	private static final long serialVersionUID = 4531982111346948697L;

	public UserIdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

}
