package de.leandersteiner.producer.infrastructue.db.memory;

import de.leandersteiner.producer.app.UserRepository;
import de.leandersteiner.producer.domain.User;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private static final Map<Integer, User> users = new ConcurrentHashMap<>();

    @Override
    public Iterable<User> findAll() {
        return users.values();
    }

    @Override
    public Optional<User> findById(int id) {
        return exists(id) ? Optional.of(users.get(id)) : Optional.empty();
    }

    @Override
    public boolean exists(int id) {
        return users.get(id) != null;
    }

    @Override
    public User save(User user) {
        users.put(user.id(), user);
        return user;
    }

    @Override
    public User delete(int id) {
        return users.remove(id);
    }
}
