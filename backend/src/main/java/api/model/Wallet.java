package api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Wallet {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")

	private String id;

	@Column(nullable = false, unique = false)
	private Long balance;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	private User holder;

	protected Wallet() {
	}

	public Wallet(Long balance, User holder) {
		this.balance = balance;
		this.holder = holder;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Wallet [id=" + id + ", balance=" + balance + ", user= " + this.getHolder().getId() + "]";
	}

	public User getHolder() {
		return holder;
	}

	public void setHolder(User holder) {
		this.holder = holder;
	}
}
