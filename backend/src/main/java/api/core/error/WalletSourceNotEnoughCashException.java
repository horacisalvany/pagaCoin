package api.core.error;

public class WalletSourceNotEnoughCashException extends RuntimeException {

	private static final long serialVersionUID = -2784402641903306385L;

	public WalletSourceNotEnoughCashException(String message) {
		super(message);
	}
}