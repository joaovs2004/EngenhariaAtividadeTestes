import library.repository.UserRepository;
import library.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationTest {
    AuthenticationService authenticationService;
    UserRepository userRepository;

    @BeforeEach
    void init() {
        userRepository = new UserRepository();
        authenticationService = new AuthenticationService(userRepository);
        IO.println("Criando authenticationService");
    }

    @Test
    public void testIncorrectLogin() {
        boolean loginUser = authenticationService.login("abc", "123");
        assertFalse(loginUser);
    }

    @Test
    public void testIncorrectPassword() {
        boolean loginUser = authenticationService.login("joao", "123");
        assertFalse(loginUser);
    }

    @Test
    public void testEmptyPassword() {
        assertThrows(IllegalArgumentException.class, () -> authenticationService.login("joao", ""));
    }

    @Test
    public void testCorrectLogin() {
        boolean loginUser = authenticationService.login("joao", "abc");
        assertTrue(loginUser);
    }
}