import library.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserRepositoryTest {
    UserRepository userRepository;

    @BeforeAll
    static void init() {
        IO.println("Começando testes de UserRepository");
    }

    @BeforeEach
    void initAttributes() {
        userRepository = new UserRepository();
        IO.println("Criando userRepository");
    }

    @Test
    @DisplayName("Testa buscar usuário existente")
    public void testFindExistingUser() {
        assertNotNull(userRepository.findByUsername("joao"));
    }

    @Test
    @DisplayName("Testa buscar usuário inexistente")
    public void testFindNonExistingUser() {
        assertNull(userRepository.findByUsername("Não existente"));
    }
}