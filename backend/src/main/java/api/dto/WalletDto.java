package api.dto;

import api.model.User;

// DTO pattern finally not used cause project is too small but we should
public class WalletDto {

	private Long id;

	private Long balance;

	private User holder;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public User getHolder() {
		return holder;
	}

	public void setHolder(User holder) {
		this.holder = holder;
	}

}
