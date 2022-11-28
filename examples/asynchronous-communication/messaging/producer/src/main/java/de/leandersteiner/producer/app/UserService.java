package de.leandersteiner.producer.app;

import de.leandersteiner.producer.app.exceptions.UserAlreadyExistsException;
import de.leandersteiner.producer.app.exceptions.UserNotFoundException;
import de.leandersteiner.producer.domain.User;
import de.leandersteiner.producer.infrastructue.messaging.MessageProducer;
import de.leandersteiner.producer.infrastructue.messaging.nats.NatsMessageProducer;
import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final MessageProducer messageProducer;

    public UserService(UserRepository userRepository) throws IOException, InterruptedException {
        this.userRepository = userRepository;
        this.messageProducer = new NatsMessageProducer("nats://localhost:4222");
    }

    public Iterable<User> viewUserList() {
        return userRepository.findAll();
    }

    public User viewUserDetails(int id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User addUser(User user) {
        if (userRepository.exists(user.id())) {
            throw new UserAlreadyExistsException(user.id());
        }
        var createdUser = userRepository.save(user);
        messageProducer.send("user.created", user.email());
        return createdUser;
    }

    public User removeUser(int id) {
        return userRepository.delete(id);
    }

    public User editUser(int id, User user) {
        return userRepository.findById(id)
            .map(existingUser -> {
                var userToUpdate = new User(
                    existingUser.id(),
                    user.firstName(),
                    user.lastName(),
                    user.email()
                );
                return userRepository.save(userToUpdate);
            }).orElseGet(() -> addUser(user));
    }

}
