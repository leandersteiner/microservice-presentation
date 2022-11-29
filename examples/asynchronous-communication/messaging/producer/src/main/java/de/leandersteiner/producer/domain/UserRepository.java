package de.leandersteiner.producer.domain;

import de.leandersteiner.producer.domain.User;
import java.util.Optional;

public interface UserRepository {
    Iterable<User> findAll();
    Optional<User> findById(int id);
    boolean exists(int id);
    User save(User user);
    User delete(int id);
}
