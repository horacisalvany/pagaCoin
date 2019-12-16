package api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = false)
	private String name;

	@Column(nullable = false, unique = false)

	private String lastName;

	@Column(nullable = false, unique = false)

	private String email;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER)
	private List<Wallet> wallets;

	public User(String name, String lastName, String email, ArrayList<Wallet> wallets) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.wallets = wallets;
	}

	protected User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Wallet> getWallets() {
		return wallets;
	}

	public void setWallets(List<Wallet> setWallet2) {
		this.wallets = setWallet2;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastName=" + lastName + ", email=" + email + ", wallets="
				+ wallets + "]";
	}

}
