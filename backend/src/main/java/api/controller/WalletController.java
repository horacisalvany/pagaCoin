package api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import api.core.error.UserNotFoundException;
import api.model.Wallet;
import api.repo.WalletRepository;

@RestController
@RequestMapping("/api/wallet")
class WalletController {

	@Autowired
	private WalletRepository walletRepository;

	@GetMapping
	public Iterable<?> findAll() {
		return walletRepository.findAll();
	}

	@ResponseBody
	public List<Wallet> getWallets(@RequestParam(name = "user_id", required = false) String userId) {
		try {
			List<Wallet> wallets = (List<Wallet>) walletRepository.findAll();
			Long userIdParsed = Long.parseLong(userId);
			wallets = wallets.stream().filter(wallet -> wallet.getHolder().getId().equals(userIdParsed))
					.collect(Collectors.toList());
			return wallets;
		} catch (RuntimeException e) {
			throw new UserNotFoundException("User does not exist with this id: " + userId, e);
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Wallet create(@RequestBody Wallet wallet) {
		return walletRepository.save(wallet);
	}

}
