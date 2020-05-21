package dz.shopy.loginservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dz.shopy.loginservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	User findByUsernameAndPassword(String username, String password);
}
