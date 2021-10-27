package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserMem {

    private final Map<Integer, User> users = new HashMap<>();

    private final AtomicInteger usersIds = new AtomicInteger(0);

    public void save(User user) {
        if (user.getId() == 0) {
            user.setId(usersIds.incrementAndGet());
        }
        users.put(user.getId(), user);
    }

    public boolean existsByUsername(String username) {
        return users.values().stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }

    public User findByUsername(String username) {
        return users.values().stream()
                .filter(user -> Objects.equals(user.getUsername(), username))
                .findFirst()
                .orElse(null);
    }
}
