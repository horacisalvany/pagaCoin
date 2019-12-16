package api;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import api.model.Wallet;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WalletUnitTest {

	private static final String API_ROOT = "http://localhost:8081/api/wallet";

	@Test
	public void whenGetAllWallets_thenOK() {
		final Response response = RestAssured.get(API_ROOT);
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}

	// POST
	@Test
	public void whenCreateNewWallet_thenCreated() {
		final Wallet wallet = createRandomWallet();

		final Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(wallet)
				.post(API_ROOT);
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
	}

	@Test
	public void whenGetNotExistWalletById_thenNotFound() {
		Response response = RestAssured.get(API_ROOT + "/" + randomNumeric(4));
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private Wallet createRandomWallet() {
		Wallet wallet = new Wallet(Long.valueOf(randomNumeric(10)), null);
		return wallet;
	}

}
