package library.service;

import library.model.User;
import library.repository.UserRepository;

public class AuthenticationService {

    private UserRepository repository;

    public AuthenticationService(UserRepository repository) {
        this.repository = repository;
    }

    public boolean login(String username, String password) {

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException(
                    "Senha inválida"
            );
        }

        User user = repository.findByUsername(username);

        if (user == null) {
            return false;
        }

        return user.getPassword().equals(password);
    }
}