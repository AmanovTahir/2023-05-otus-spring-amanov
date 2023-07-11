package ru.otus.service;


import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.otus.domain.User;

@Component
@Scope("prototype")
@NoArgsConstructor
public class AuthorizationProcessor {
    private boolean isAuthorized;

    private User user;

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.isAuthorized = true;
    }
}
