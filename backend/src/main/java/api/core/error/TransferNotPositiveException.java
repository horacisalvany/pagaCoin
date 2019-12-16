package api.core.error;

public class TransferNotPositiveException extends RuntimeException {

	private static final long serialVersionUID = 1658318061475720100L;

	public TransferNotPositiveException(String message) {
		super(message);
	}
}
