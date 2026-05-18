package biblioteca.test;

import biblioteca.model.*;
import biblioteca.repository.LivroRepository;
import biblioteca.service.LivroService;

public class LivroServiceTest {

    public static void main(String[] args) {
        
        Autor autor = new Autor("J.R.R. Tolkien", "Britânico");
        Categoria categoria = new Categoria("Fantasia", "Mundo mágico e aventura");
        
        Livro livro = new Livro(
                "9788533613379", 
                "O Senhor dos Anéis", 
                autor, 
                categoria
        );

        LivroRepository repository = new LivroRepository();
        LivroService service = new LivroService(repository);

        System.out.println("--- Iniciando Testes de LivroService ---");

        // Testando Cadastro
        System.out.println("Testando: Cadastrar Livro...");
        service.cadastrarLivro(livro);

        // Testando Busca
        System.out.println("Testando: Buscar Livro por ISBN...");
        Livro encontrado = service.buscarPorIsbn("9788533613379");
        if (encontrado != null) {
            System.out.println("Livro encontrado: " + encontrado.getTitulo());
        }

        // Testando Remoção
        System.out.println("Testando: Remover Livro...");
        service.removerLivro(livro);
        
        System.out.println("--- Testes finalizados ---");
    }
}