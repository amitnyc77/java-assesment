package pk.futurenostics.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pk.futurenostics.user.User;

public interface UserRepo extends JpaRepository<User, Integer> {
      Optional<User> findByEmail(String email);
}
