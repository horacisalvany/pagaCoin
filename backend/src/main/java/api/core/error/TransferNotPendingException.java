package api.core.error;

public class TransferNotPendingException extends RuntimeException {

	private static final long serialVersionUID = -6003902707719731858L;

	public TransferNotPendingException(String message) {
		super(message);
	}
}
