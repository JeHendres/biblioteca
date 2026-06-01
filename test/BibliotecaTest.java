package biblioteca.test;

import biblioteca.model.*;
import biblioteca.repository.LivroRepository;
import biblioteca.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BibliotecaTest {

    private Biblioteca biblioteca;
    private Livro livro1;
    private Livro livro2;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        biblioteca = new Biblioteca("Biblioteca Municipal");

        Autor autor1 = new Autor("Machado de Assis", "Brasileiro");
        Categoria categoria1 = new Categoria("Romance", "Livros de romance");
        livro1 = new Livro("111", "Dom Casmurro", autor1, categoria1);

        Autor autor2 = new Autor("Herman Melville", "Inglês");
        Categoria categoria2 = new Categoria("Ficção", "Aventura no mar");
        livro2 = new Livro("123", "Moby Dick", autor2, categoria2);

        usuario = new Usuario("10123456789", "Jeronimo", "jeronimo@email.com", "Brasileiro");
    }

    @Test
    void deveAdicionarLivroNaBiblioteca() {
        biblioteca.adicionarLivro(livro1);

        // Verifica via exibirDados sem lançar exceção
        assertDoesNotThrow(() -> biblioteca.exibirDados(),
                "Deve exibir dados sem erros após adicionar livro");
    }

    @Test
    void deveAdicionarMultiplosLivros() {
        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);

        assertDoesNotThrow(() -> biblioteca.exibirDados(),
                "Deve exibir dados com múltiplos livros sem erros");
    }

    @Test
    void deveAdicionarUsuarioNaBiblioteca() {
        biblioteca.adicionarUsuario(usuario);

        assertDoesNotThrow(() -> biblioteca.exibirDados(),
                "Deve exibir dados sem erros após adicionar usuário");
    }

    @Test
    void livroDeveIniciarDisponivel() {
        assertTrue(livro1.isDisponivel(), "Livro deve iniciar disponível");
    }

    @Test
    void deveAlterarDisponibilidadeDeLivro() {
        livro1.setDisponivel(false);
        assertFalse(livro1.isDisponivel(), "Livro deve ficar indisponível");

        livro1.setDisponivel(true);
        assertTrue(livro1.isDisponivel(), "Livro deve voltar a ficar disponível");
    }
}
