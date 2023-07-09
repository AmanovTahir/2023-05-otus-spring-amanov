package ru.otus.service;


import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.otus.domain.User;

@Component
@Scope("prototype")
@NoArgsConstructor
public class AuthorisationProcessor {
    private boolean isAuthorisation;

    private User user;

    public boolean isAuthorisation() {
        return isAuthorisation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.isAuthorisation = true;
    }
}
