package api.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import api.model.Wallet;

public interface WalletRepository extends CrudRepository<Wallet, Long> {
	List<Wallet> findByBalance(Long balance);

	Wallet findById(String id);
}
