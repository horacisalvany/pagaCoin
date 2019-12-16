package api.repo;

import org.springframework.data.repository.CrudRepository;

import api.model.Transfer;

public interface TransferRepository extends CrudRepository<Transfer, Long> {
	Transfer findById(long id);
}
