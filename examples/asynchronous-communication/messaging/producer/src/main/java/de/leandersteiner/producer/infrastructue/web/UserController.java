package de.leandersteiner.producer.infrastructue.web;

import de.leandersteiner.producer.app.UserService;
import de.leandersteiner.producer.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Iterable<User> get() {
        return userService.viewUserList();
    }

    @GetMapping("{id}")
    public User getById(@PathVariable int id) {
        return userService.viewUserDetails(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User post(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        userService.removeUser(id);
    }

    @PutMapping("{id}")
    public User put(@PathVariable int id, @RequestBody User user) {
        return userService.editUser(id, user);
    }
}
