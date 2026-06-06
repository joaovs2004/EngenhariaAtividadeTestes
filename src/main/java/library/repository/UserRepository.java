package library.repository;

import library.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private Map<String, User> users = new HashMap<>();

    public UserRepository() {

        users.put(
                "admin",
                new User("admin", "123", false)
        );

        users.put(
                "joao",
                new User("joao", "abc", true)
        );

        users.put(
                "maria",
                new User("maria", "456", false)
        );
    }

    public User findByUsername(String username) {
        return users.get(username);
    }
}