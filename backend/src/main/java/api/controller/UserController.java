package api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import api.core.error.UserIdMismatchException;
import api.core.error.UserNotFoundException;
import api.model.User;
import api.repo.UserRepository;

@RestController
@RequestMapping("/api/user")
class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public Iterable<?> findAll() {
		return userRepository.findAll();
	}

	@GetMapping("/{id}")
	public User findById(@PathVariable Long id) {
		return userRepository.findById(id).orElseThrow(() -> {
			throw new UserNotFoundException("User does not exist with this id: " + id);
		});
	}

	@GetMapping("/name/{userName}")
	public List<User> findByName(@PathVariable String userName) {
		try {
			/*
			 * DTO pattern finally not used
			 * 
			 * return
			 * userRepository.findByName(userName).stream().map(this::convertToDto).collect(
			 * Collectors.toList());
			 */
			return userRepository.findByName(userName);
		} catch (UserIdMismatchException e) {
			return null;
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody User user) {
		return userRepository.save(user);
	}

	/*
	 * DTO pattern finally not used cause project is too small but we should
	 * 
	 * private UserDto convertToDto(User user) { ModelMapper modelMapper = new
	 * ModelMapper(); UserDto userDto = modelMapper.map(user, UserDto.class); return
	 * userDto; }
	 */

}
