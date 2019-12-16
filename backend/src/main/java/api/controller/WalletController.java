package api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import api.model.Wallet;
import api.repo.WalletRepository;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

	@Autowired
	private WalletRepository walletRepository;

	@GetMapping
	public Iterable<?> findAll() {
		return walletRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Wallet create(@RequestBody Wallet wallet) {
		return walletRepository.save(wallet);
	}
}
