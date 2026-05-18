package biblioteca.test;

import biblioteca.model.Autor;
import biblioteca.model.Categoria;
import biblioteca.model.Livro;
import biblioteca.repository.LivroRepository;
import biblioteca.service.LivroService;

public class LivroServiceTest {

    public static void main(String[] args) {

        Autor autor = new Autor("Machado de Assis", "Brasileiro");
        Categoria categoria = new Categoria("Romance", "Livros de romance");

        Livro livro = new Livro(
                "123",
                "Dom Casmurro",
                autor,
                categoria
        );

        LivroRepository repository = new LivroRepository();
        LivroService service = new LivroService(repository);

        service.cadastrarLivro(livro);

        Livro resultado = service.buscarPorIsbn("123");

        if (resultado != null) {
            resultado.exibirDados();
        }
    }
}