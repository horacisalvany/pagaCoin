package api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import api.core.error.TransferIdMismatchException;
import api.core.error.TransferNotFoundException;
import api.core.error.TransferNotPendingException;
import api.core.error.TransferNotPositiveException;
import api.model.Transfer;
import api.repo.TransferRepository;
import api.service.WalletService;

@RestController
@RequestMapping("/api/transfer")
class TranferController {

	@Autowired
	private TransferRepository transferRepository;

	@Autowired
	private WalletService walletService;

	@GetMapping
	public Iterable<?> findAll() {
		return transferRepository.findAll();
	}

	@GetMapping("/{id}")
	public Transfer findById(@PathVariable Long id) {
		return transferRepository.findById(id).orElseThrow(() -> {
			throw new TransferNotFoundException("Transfer does not exist with this id: " + id);
		});
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Transfer createTransfer(@RequestBody Transfer transfer) {
		if (transfer.getAmount() <= 0) {
			throw new TransferNotPositiveException("A transfer must be a positive number amount");
		}
		// Anyway, when a transfer is created, must be in "Pending" State
		transfer.setState("pending");
		return transferRepository.save(transfer);
	}

	@Transactional
	@PutMapping("/commit/{id}")
	public Transfer commitTransfer(@RequestBody Transfer transferRequest, @PathVariable Long id) {
		Transfer transfer = transferRepository.findById(id).orElseThrow(
				() -> new TransferNotFoundException("No transfer found with this id pass as param: " + id));
		if (transfer.getId() != id)
			throw new TransferIdMismatchException("Id found in RequestBody does not match with id param");

		if (transfer.getAmount() <= 0)
			throw new TransferNotPositiveException("A transfer must be a positive number amount");

		if (!transfer.getState().equals("pending"))
			throw new TransferNotPendingException("A transfer must be on pending state");

		walletService.transfer(transfer.getSource().getId(), transfer.getDestination().getId(), transfer.getAmount());

		// Anyway, when a transfer is created, must be in "completed" State
		transfer.setState("completed");
		return transferRepository.save(transfer);
	}
}
