package api.core.error;

public class TransferNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2784402641903306385L;

	public TransferNotFoundException(String message) {
		super(message);
	}
}