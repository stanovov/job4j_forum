package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.repository.AuthorityMem;

@Service
public class AuthorityService {

    private final AuthorityMem authorityRepository;

    public AuthorityService(AuthorityMem authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Authority findByName(String name) {
        return authorityRepository.findByName(name);
    }
}
