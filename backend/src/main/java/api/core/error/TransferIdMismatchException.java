package api.core.error;

public class TransferIdMismatchException extends RuntimeException {

	private static final long serialVersionUID = 4531982111346948697L;

	public TransferIdMismatchException(String message) {
		super(message);
	}

}
