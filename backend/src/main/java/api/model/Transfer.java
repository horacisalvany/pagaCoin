package api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Transfer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	private Wallet source;

	@OneToOne
	private Wallet destination;

	@Column(nullable = false, unique = false)

	private Long amount;

	@Column(nullable = false, unique = false)
	private String state;

	public Transfer(Wallet source, Wallet destination, long amount, String state) {
		this.source = source;
		this.destination = destination;
		this.amount = amount;
		this.state = state;
	}

	protected Transfer() {
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

	public long getAmount() {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Transfer [id=" + id + ", source = " + source.toString() + ", destination = " + destination.toString()
				+ ", amount=" + amount + ", state=" + state + "]";
	}
}
