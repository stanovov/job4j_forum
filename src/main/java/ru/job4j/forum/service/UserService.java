package ru.job4j.forum.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final AuthorityService authorityService;

    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, AuthorityService authorityService, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.authorityService = authorityService;
        this.encoder = encoder;
    }

    public void save(User user) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorityService.findByName("ROLE_USER"));
        userRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
