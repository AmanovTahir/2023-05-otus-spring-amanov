package ru.otus.domain;

public enum Messages {
    HELLO("Hello user! Whats your full name?"),

    RESULT("Your score: "),

    LOOSE("We're sorry but you didn't get a passing grade"),

    WIN("Congratulations, you have received a passing score");
    private final String str;

    Messages(String str) {
        this.str = str;
    }

    public String getMessage() {
        return str;
    }
}
