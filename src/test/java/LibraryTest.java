import library.model.User;
import library.repository.UserRepository;
import library.service.AuthenticationService;
import library.service.LibraryService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    UserRepository userRepository;
    LibraryService libraryService;

    @BeforeAll
    static void init() {
        IO.println("Começando testes de Biblioteca");
    }

    @BeforeEach
    void initAttributes() {
        userRepository = new UserRepository();
        libraryService = new LibraryService();
        IO.println("Criando libraryService");
    }

    @AfterEach
    void finish() {
        IO.println("Terminando teste");
    }

    @Test
    @DisplayName("Teste de emprestimo bem sucedido")
    public void testSucessBorrow() {
        User user = userRepository.findByUsername("admin");
        assertTrue(libraryService.borrowBook(user, "Senhor dos aneis"));
    }

    @Test
    @DisplayName("Teste de usuario bloqueado")
    public void testBlockedUser() {
        User user = userRepository.findByUsername("joao");
        assertFalse(libraryService.borrowBook(user, "Senhor dos aneis"));
    }

    @Test
    @DisplayName("Teste de livro indisponivel")
    public void testUnavailableBook() {
        User user = userRepository.findByUsername("admin");
        assertFalse(libraryService.borrowBook(user, "Java Avançado"));
    }

    @Test
    @DisplayName("Teste de limite de emprestimo")
    public void testMaxBookBorrow() {
        User user = userRepository.findByUsername("admin");
        libraryService.borrowBook(user, "Senhor dos aneis");
        libraryService.borrowBook(user, "O Silmarillion");
        libraryService.borrowBook(user, "As Crônicas de Gelo e Fogo");
        assertFalse(libraryService.borrowBook(user, "O Hobbit"));
    }

    @Test
    @DisplayName("Teste de titulo do livro vazio")
    public void testEmptyTitle() {
        User user = userRepository.findByUsername("admin");
        assertThrows(IllegalArgumentException.class, () -> libraryService.borrowBook(user, ""));
    }

    @Test
    @DisplayName("Teste de titulo do livro nulo")
    public void testNullTitle() {
        User user = userRepository.findByUsername("admin");
        assertThrows(IllegalArgumentException.class, () -> libraryService.borrowBook(user, null));
    }

    @ParameterizedTest
    @CsvSource({
            "Senhor dos aneis, true",
            "As Crônicas de Gelo e Fogo, true",
            "Java Avançado, false",
    })
    @DisplayName("Teste com multiplos titulos")
    public void testMultipleLogins(String titulo, boolean esperado) {
        User user = userRepository.findByUsername("admin");
        assertEquals(esperado, libraryService.borrowBook(user, titulo));

    }
}