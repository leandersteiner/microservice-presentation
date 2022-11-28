package de.leandersteiner.producer.app.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(int id) {
        super("The user with id " + id + " already exists.");
    }
}
