package api.dto;

import api.model.Wallet;

// DTO pattern finally not used cause project is too small but we should 
public class TransferDto {

	private Long id;

	private Wallet source;

	private Wallet destination;

	private Long amount;

	private String state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Wallet getSource() {
		return source;
	}

	public void setSource(Wallet source) {
		this.source = source;
	}

	public Wallet getDestination() {
		return destination;
	}

	public void setDestination(Wallet destination) {
		this.destination = destination;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
