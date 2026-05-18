package biblioteca;

import biblioteca.controller.BibliotecaController;
import biblioteca.controller.LivroController;
import biblioteca.controller.UsuarioController;
import biblioteca.model.*;
import biblioteca.repository.AluguelRepository;
import biblioteca.repository.LivroRepository;
import biblioteca.repository.UsuarioRepository;
import biblioteca.service.AluguelService;
import biblioteca.service.LivroService;
import biblioteca.service.UsuarioService;

public class Main {

    public static void main(String[] args) {

        System.out.println("========= SISTEMA DE BIBLIOTECA =========\n");

        /*
         * CRIANDO AUTORES
         */
        Autor autor1 = new Autor(
                "Machado de Assis",
                "Brasileiro"
        );

        Autor autor2 = new Autor(
                "Herman Melville",
                "Inglês"
        );

        /*
         * CRIANDO CATEGORIAS
         */
        Categoria categoria1 = new Categoria(
                "Romance",
                "Livros de romance"
        );

        Categoria categoria2 = new Categoria(
                "Ficção",
                "Livros de ficção"
        );

        /*
         * CRIANDO LIVROS
         */
        Livro livro1 = new Livro(
                "111",
                "Dom Casmurro",
                autor1,
                categoria1
        );

        Livro livro2 = new Livro(
                "123",
                "Moby Dick",
                autor2,
                categoria2
        );

        /*
         * CRIANDO USUÁRIOS
         */
        Usuario usuario1 = new Usuario(
                "12345678910",
                "João",
                "joao@email.com",
                "Brasileiro"
        );

        Usuario usuario2 = new Usuario(
                "109876546321",
                "Jeronimo",
                "Jeronimo@email.com",
                "Brasileira"
        );

        /*
         * CRIANDO FUNCIONÁRIO
         */
        Funcionario funcionario = new Funcionario(
                "12312312310",
                "Francis",
                "Francis@biblioteca.com",
                "Brasileiro"
        );

        /*
         * CRIANDO BIBLIOTECA
         */
        Biblioteca biblioteca = new Biblioteca("Biblioteca dos guri");

        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);

        biblioteca.adicionarUsuario(usuario1);
        biblioteca.adicionarUsuario(usuario2);

        /*
         * REPOSITORIES
         */
        LivroRepository livroRepository = new LivroRepository();
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        AluguelRepository aluguelRepository = new AluguelRepository();

        /*
         * SERVICES
         */
        LivroService livroService = new LivroService(livroRepository);
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        AluguelService aluguelService = new AluguelService(aluguelRepository);

        /*
         * CONTROLLERS
         */
        LivroController livroController = new LivroController(livroService);
        UsuarioController usuarioController = new UsuarioController(usuarioService);
        BibliotecaController bibliotecaController =
                new BibliotecaController(biblioteca);

        /*
         * CADASTRANDO LIVROS
         */
        System.out.println("========= CADASTRANDO LIVROS =========");

        livroController.criar(livro1);
        livroController.criar(livro2);

        funcionario.cadastrarLivro();

        /*
         * CADASTRANDO USUÁRIOS
         */
        System.out.println("\n========= CADASTRANDO USUÁRIOS =========");

        usuarioController.criar(usuario1);
        usuarioController.criar(usuario2);

        /*
         * EXIBINDO DADOS DOS LIVROS
         */
        System.out.println("\n========= DETALHES DOS LIVROS =========");

        livroController.detalhar("111");
        System.out.println();

        livroController.detalhar("123");

        /*
         * EXIBINDO DADOS DOS USUÁRIOS
         */
        System.out.println("\n========= DADOS DOS USUÁRIOS =========");

        usuario1.exibirDados();
        System.out.println();

        usuario2.exibirDados();

        /*
         * EXIBINDO DADOS DO FUNCIONÁRIO
         */
        System.out.println("\n========= DADOS DO FUNCIONÁRIO =========");

        funcionario.exibirDados();

        /*
         * EXIBINDO DADOS DA BIBLIOTECA
         */
        System.out.println("\n========= DADOS DA BIBLIOTECA =========");

        bibliotecaController.listarAcervo();

        /*
         * REALIZANDO ALUGUEL
         */
        System.out.println("\n========= ALUGUEL DE LIVRO =========");

        aluguelService.alugar(usuario1, livro1);

        /*
         * VERIFICANDO DISPONIBILIDADE
         */
        System.out.println("\n========= STATUS DO LIVRO =========");

        if (livro1.isDisponivel()) {
            System.out.println("Livro disponível.");
        } else {
            System.out.println("Livro indisponível.");
        }

        /*
         * POLIMORFISMO
         */
        System.out.println("\n========= POLIMORFISMO =========");

        Pessoa pessoa1 = usuario1;
        Pessoa pessoa2 = funcionario;

        pessoa1.exibirDados();
        System.out.println();

        pessoa2.exibirDados();

        /*
         * INTERFACE
         */
        System.out.println("\n========= INTERFACE GERENCIAVEL =========");

        livroService.cadastrarLivro();
        livroService.removerLivro();

        /*
         * REMOVENDO LIVRO
         */
        System.out.println("\n========= REMOVENDO LIVRO =========");

        livroController.remover(livro2);

        funcionario.removerLivro();

        /*
         * FINALIZAÇÃO
         */
        System.out.println("\n========= SISTEMA FINALIZADO =========");
    }
}