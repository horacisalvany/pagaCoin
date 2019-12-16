package api.core.error;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ UserNotFoundException.class, TransferNotFoundException.class, WalletNotFoundException.class })
	protected ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ UserIdMismatchException.class, TransferIdMismatchException.class,
			ConstraintViolationException.class, DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST,
				request);
	}

	@ExceptionHandler({ WalletSourceNotEnoughCashException.class, TransferNotPositiveException.class,
			TransferNotPendingException.class })
	public ResponseEntity<Object> handleForbiddenRequest(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.FORBIDDEN, request);
	}

}
