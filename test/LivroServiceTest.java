package biblioteca.test;

import biblioteca.model.Autor;
import biblioteca.model.Categoria;
import biblioteca.model.Livro;
import biblioteca.repository.LivroRepository;
import biblioteca.service.LivroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LivroServiceTest {

    private LivroService service;
    private LivroRepository repository;
    private Livro livro;

    @BeforeEach
    void setUp() {
        repository = new LivroRepository();
        service = new LivroService(repository);

        Autor autor = new Autor("J.R.R. Tolkien", "Britânico");
        Categoria categoria = new Categoria("Fantasia", "Mundo mágico e aventura");
        livro = new Livro("9788533613379", "O Senhor dos Anéis", autor, categoria);
    }

    @Test
    void deveCadastrarLivro() {
        service.cadastrarLivro(livro);

        assertEquals(1, repository.listarTodos().size(), "Deve haver 1 livro cadastrado");
    }

    @Test
    void deveBuscarLivroPorIsbn() {
        service.cadastrarLivro(livro);

        Livro encontrado = service.buscarPorIsbn("9788533613379");

        assertNotNull(encontrado, "Livro deve ser encontrado pelo ISBN");
        assertEquals("O Senhor dos Anéis", encontrado.getTitulo(), "Título deve ser o correto");
    }

    @Test
    void deveRetornarNullParaIsbnInexistente() {
        Livro encontrado = service.buscarPorIsbn("0000000000000");

        assertNull(encontrado, "Deve retornar null para ISBN não cadastrado");
    }

    @Test
    void deveRemoverLivro() {
        service.cadastrarLivro(livro);
        service.removerLivro(livro);

        assertEquals(0, repository.listarTodos().size(), "Lista deve estar vazia após remoção");
        assertNull(service.buscarPorIsbn("9788533613379"), "Livro removido não deve ser encontrado");
    }

    @Test
    void livroDeveComecarDisponivel() {
        service.cadastrarLivro(livro);

        Livro encontrado = service.buscarPorIsbn("9788533613379");

        assertTrue(encontrado.isDisponivel(), "Livro recém cadastrado deve estar disponível");
    }
}
