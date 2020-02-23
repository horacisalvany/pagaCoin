package api;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import api.model.Transfer;
import api.model.User;
import api.model.Wallet;
import api.repo.TransferRepository;
import api.repo.UserRepository;
import api.repo.WalletRepository;;

@EnableJpaRepositories("api.repo")
@EntityScan("api.model")

@SpringBootApplication
public class SpringBootWebApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringBootWebApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository, WalletRepository walletRepository,
			TransferRepository transferRepository) {
		return (args) -> {

			// save a few users
			User bob = new User("Bob", "Parker", "bob@pagacoin.com", null);
			User john = new User("John", "Smith", "john@pagacoin.com", null);
			userRepository.save(bob);
			userRepository.save(john);

			Wallet wallet1 = new Wallet(10l, bob);
			Wallet wallet2 = new Wallet(0l, john);
			Wallet wallet3 = new Wallet(0l, john);

			walletRepository.save(wallet1);
			walletRepository.save(wallet2);
			walletRepository.save(wallet3);

			List<Wallet> setWallet1 = new ArrayList<Wallet>();
			setWallet1.add(wallet1);
			bob.setWallets(setWallet1);

			List<Wallet> setWallet2 = new ArrayList<Wallet>();
			setWallet2.add(wallet2);
			setWallet2.add(wallet3);
			john.setWallets(setWallet2);
			userRepository.save(bob);
			userRepository.save(john);

			// State state;
			Transfer transfer = new Transfer(wallet1, wallet2, 10L);
			// TODO: delete this instiante of state
			// state = new ReadyState(transfer);
			transferRepository.save(transfer);

			// fetch all users
			log.info("users found with findAll():");
			log.info("-------------------------------");
			userRepository.findAll().forEach(user -> {
				log.info(user.toString());
				log.info(user.getWallets().get(0).getId());
				// log.info(Long.toString(user.getWallets().get(0).getId()));
			});
			log.info("");

			// fetch all Wallet
			log.info("Wallet found with findAll():");
			log.info("-------------------------------");
			walletRepository.findAll().forEach(wallet -> log.info(wallet.toString()));
			log.info("");

			// fetch all Transfers
			log.info("Transfers found with findAll():");
			log.info("-------------------------------");
			transferRepository.findAll().forEach(transferr -> log.info(transferr.toString()));
			log.info("");

			// fetch an individual user by ID
			User user = userRepository.findById(1L);
			log.info("user found with findById(1L):");
			log.info("--------------------------------");
			log.info(user.toString());
			log.info("");

			// fetch user by last name
			log.info("User found with findByLastName('John'):");
			log.info("--------------------------------------------");
			userRepository.findByName("John").forEach(user1 -> {
				log.info(user1.toString());
			});
			log.info("");
		};
	}
}