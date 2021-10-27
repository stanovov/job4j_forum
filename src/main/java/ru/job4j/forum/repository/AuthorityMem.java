package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Authority;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AuthorityMem {

    private final Map<Integer, Authority> authorities = new HashMap<>();

    private final AtomicInteger authoritiesIds = new AtomicInteger(0);

    public void save(Authority authority) {
        if (authority.getId() == 0) {
            authority.setId(authoritiesIds.incrementAndGet());
        }
        authorities.put(authority.getId(), authority);
    }

    public Authority findByName(String name) {
        return authorities.values().stream()
                .filter(authority -> Objects.equals(authority.getName(), name))
                .findFirst().orElse(null);
    }
}
