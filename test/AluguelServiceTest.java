package biblioteca.test;

import biblioteca.model.*;
import biblioteca.repository.AluguelRepository;
import biblioteca.service.AluguelService;

public class AluguelServiceTest {

    public static void main(String[] args) {

        Usuario usuario = new Usuario(
                "10123456789",
                "Jeronimo",
                "Jeronimo@email.com",
                "Brasileiro"
        );

        Autor autor = new Autor("Herman Melville", "Inglês");
        Categoria categoria = new Categoria("Ficção", "O gigante cachalote branco");

        Livro livro = new Livro(
                "123",
                "Moby Dick",
                autor,
                categoria
        );

        AluguelRepository repository = new AluguelRepository();
        AluguelService service = new AluguelService(repository);

        System.out.println("--- Iniciando Testes de AluguelService ---");

        // Teste: Alugar Livro
        System.out.println("Testando: Alugar Livro...");
        service.alugar(usuario, livro);

        // Teste: Devolver Livro
        System.out.println("Testando: Devolver Livro...");
        service.devolver(usuario, livro);
        
        System.out.println("--- Testes finalizados ---");
    }
}