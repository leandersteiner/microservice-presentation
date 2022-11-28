package de.leandersteiner.producer.app.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(int id) {
        super("The user with id " + id + " was not found.");
    }
}
