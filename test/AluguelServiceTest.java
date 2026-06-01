package biblioteca.test;

import biblioteca.model.*;
import biblioteca.repository.AluguelRepository;
import biblioteca.service.AluguelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AluguelServiceTest {

    private AluguelService service;
    private AluguelRepository repository;
    private Usuario usuario;
    private Livro livro;

    @BeforeEach
    void setUp() {
        repository = new AluguelRepository();
        service = new AluguelService(repository);

        usuario = new Usuario("10123456789", "Jeronimo", "jeronimo@email.com", "Brasileiro");

        Autor autor = new Autor("Herman Melville", "Inglês");
        Categoria categoria = new Categoria("Ficção", "Aventura no mar");
        livro = new Livro("123", "Moby Dick", autor, categoria);
    }

    @Test
    void deveAlugarLivroDisponivel() {
        service.alugar(usuario, livro);

        assertFalse(livro.isDisponivel(), "Livro deve ficar indisponível após aluguel");
        assertEquals(1, repository.findAll().size(), "Deve haver 1 aluguel registrado");
    }

    @Test
    void naoDeveAlugarLivroIndisponivel() {
        livro.setDisponivel(false);

        service.alugar(usuario, livro);

        assertEquals(0, repository.findAll().size(), "Não deve registrar aluguel de livro indisponível");
    }

    @Test
    void deveDevolverLivro() {
        service.alugar(usuario, livro);
        Aluguel aluguel = repository.findAll().get(0);

        service.devolver(aluguel);

        assertTrue(livro.isDisponivel(), "Livro deve ficar disponível após devolução");
        assertEquals(0, repository.findAll().size(), "Aluguel deve ser removido após devolução");
    }

    @Test
    void deveBuscarAluguelPorUsuario() {
        service.alugar(usuario, livro);

        var alugueis = repository.findByUsuario(usuario);

        assertEquals(1, alugueis.size(), "Deve encontrar 1 aluguel para o usuário");
        assertEquals(livro, alugueis.get(0).getLivro(), "O livro do aluguel deve ser o correto");
    }
}
