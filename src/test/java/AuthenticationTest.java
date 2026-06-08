import library.repository.UserRepository;
import library.service.AuthenticationService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationTest {
    AuthenticationService authenticationService;
    UserRepository userRepository;

    @BeforeEach
    void initAttributes() {
        userRepository = new UserRepository();
        authenticationService = new AuthenticationService(userRepository);
        IO.println("Criando authenticationService");
    }

    @BeforeAll
    static void init() {
        IO.println("Começando testes de autenticação");
    }

    @AfterAll
    static void finish() {
        IO.println("Finalizando testes de autenticação");
    }

    @Test
    @DisplayName("Teste de login bem sucedido")
    public void testCorrectLogin() {
        boolean loginUser = authenticationService.login("joao", "abc");
        assertTrue(loginUser);
    }

    @Test
    @DisplayName("Teste de senha incorreta")
    public void testIncorrectPassword() {
        boolean loginUser = authenticationService.login("joao", "123");
        assertFalse(loginUser);
    }

    @Test
    @DisplayName("Teste de login invalido")
    public void testIncorrectLogin() {
        boolean loginUser = authenticationService.login("abc", "123");
        assertFalse(loginUser);
    }

    @Test
    @DisplayName("Teste de senha null")
    public void testNullPassword() {
        assertThrows(IllegalArgumentException.class, () -> authenticationService.login("joao", null));
    }

    @Test
    @DisplayName("Teste de senha vazia")
    public void testEmptyPassword() {
        assertThrows(IllegalArgumentException.class, () -> authenticationService.login("joao", ""));
    }

    @ParameterizedTest
    @CsvSource({
            "joao, abc, true",
            "joao, 123, false"
    })
    @DisplayName("Teste com multiplos logins")
    public void testMultipleLogins(String login, String password, boolean esperado) {
        assertEquals(esperado, authenticationService.login(login, password));

    }
}