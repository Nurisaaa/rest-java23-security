package peaksoft.school.restjava23.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.school.restjava23.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsUserByEmail(String email);
}
