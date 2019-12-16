package api.core.error;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2784402641903306385L;

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}