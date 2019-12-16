package api;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import api.model.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserUnitTest {

	private static final String API_ROOT = "http://localhost:8081/api/user";

	@Test
	public void whenGetAllUsers_thenOK() {
		final Response response = RestAssured.get(API_ROOT);
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}

	@Test
	public void whenGetCreatedUserById_thenOK() {
		final User user = createRandomUser();
		String location = createUserAsUri(user);
		Response response = RestAssured.get(location);

		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		assertEquals(user.getName(), response.jsonPath().get("name"));
	}

	@Test
	public void whenGetUserByName_thenOK() {
		final User user = createRandomUser();
		createUserAsUri(user);

		final Response response = RestAssured.get(API_ROOT + "/name/" + user.getName());
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		assertTrue(response.as(List.class).size() > 0);
	}

	// POST
	@Test
	public void whenCreateNewUser_thenCreated() {
		final User user = createRandomUser();

		final Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(user)
				.post(API_ROOT);
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
	}

	@Test
	public void whenInvalidUser_thenError() {
		final User user = createRandomUser();
		user.setName(null);

		final Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(user)
				.post(API_ROOT);
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
	}

	@Test
	public void whenGetNotExistUserById_thenNotFound() {
		Response response = RestAssured.get(API_ROOT + "/" + randomNumeric(4));

		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private User createRandomUser() {
		User user = new User(randomAlphabetic(10), randomAlphabetic(10), randomAlphabetic(10), null);
		return user;
	}

	private String createUserAsUri(User user) {
		Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(user).post(API_ROOT);
		return API_ROOT + "/" + response.jsonPath().get("id");
	}
}
