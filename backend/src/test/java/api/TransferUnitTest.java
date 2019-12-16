package api;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import api.model.Transfer;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TransferUnitTest {

	private static final String API_ROOT = "http://localhost:8081/api/transfer";

	@Test
	public void whenGetAllTransfers_thenOK() {
		final Response response = RestAssured.get(API_ROOT);
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}

	@Test
	public void whenGetCreatedTransferById_thenStateIsPending() {
		final Transfer transfer = createRandomTransfer();
		String location = createTransferAsUri(transfer);
		Response response = RestAssured.get(location);

		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		assertEquals("pending", response.jsonPath().get("state").toString());
	}

	// POST
	@Test
	public void whenCreateNewTransfer_thenCreated() {
		final Transfer transfer = createRandomTransfer();

		final Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(transfer)
				.post(API_ROOT);
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
	}

	@Test
	public void whenGetNotExistTransferById_thenNotFound() {
		Response response = RestAssured.get(API_ROOT + "/" + randomNumeric(4));

		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private Transfer createRandomTransfer() {
		Transfer transfer = new Transfer(null, null, Long.valueOf(randomNumeric(4)), randomAlphabetic(10));
		return transfer;
	}

	private String createTransferAsUri(Transfer transfer) {
		Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(transfer)
				.post(API_ROOT);
		return API_ROOT + "/" + response.jsonPath().get("id");
	}
}
