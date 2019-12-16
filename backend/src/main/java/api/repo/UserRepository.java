package api.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import api.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findByName(String name);

	User findById(long id);
}
