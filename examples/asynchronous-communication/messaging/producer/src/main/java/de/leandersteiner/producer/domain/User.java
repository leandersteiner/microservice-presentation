package de.leandersteiner.producer.domain;

public record User(
    int id,
    String firstName,
    String lastName,
    String email
){}
