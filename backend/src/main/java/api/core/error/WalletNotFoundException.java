package api.core.error;

public class WalletNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5351712002293972939L;

	public WalletNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}