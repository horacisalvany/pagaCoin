package api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import api.core.error.WalletSourceNotEnoughCashException;
import api.model.Wallet;
import api.repo.WalletRepository;

@Component
public class WalletService {

	private final static Logger logger = LoggerFactory.getLogger(WalletService.class);

	@Autowired
	private WalletRepository walletRepository;

	@Transactional
	public void transfer(String idSource, String idDestination, Long amount) {
		Wallet source = walletRepository.findById(idSource);
		Wallet destination = walletRepository.findById(idDestination);
		logger.info("Transfering " + amount + " from this wallet " + source.getId() + " to " + destination.getId());
		if (source.getBalance() - amount >= 0) {
			source.setBalance(source.getBalance() - amount);
			destination.setBalance(destination.getBalance() + amount);
			walletRepository.save(source);
			walletRepository.save(destination);
		} else
			throw new WalletSourceNotEnoughCashException(
					"The source wallet does not have sufficient balance to perform the transfer.");
	}

}
