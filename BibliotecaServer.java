package biblioteca;

import biblioteca.model.*;
import biblioteca.repository.AluguelRepository;
import biblioteca.repository.LivroRepository;
import biblioteca.repository.UsuarioRepository;
import biblioteca.service.AluguelService;
import biblioteca.service.LivroService;
import biblioteca.service.UsuarioService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class BibliotecaServer {

    // Repositórios
    static LivroRepository livroRepository = new LivroRepository();
    static UsuarioRepository usuarioRepository = new UsuarioRepository();
    static AluguelRepository aluguelRepository = new AluguelRepository();

    // Services
    static LivroService livroService = new LivroService(livroRepository);
    static UsuarioService usuarioService = new UsuarioService(usuarioRepository);
    static AluguelService aluguelService = new AluguelService(aluguelRepository);

    public static void main(String[] args) throws IOException {

        // Populando dados iniciais
        Autor autor1 = new Autor("Machado de Assis", "Brasileiro");
        Categoria categoria1 = new Categoria("Romance", "Livros de romance");
        livroService.cadastrarLivro(new Livro("111", "Dom Casmurro", autor1, categoria1));

        Autor autor2 = new Autor("Herman Melville", "Inglês");
        Categoria categoria2 = new Categoria("Ficção", "Aventura no mar");
        livroService.cadastrarLivro(new Livro("123", "Moby Dick", autor2, categoria2));

        Autor autor3 = new Autor("J.R.R. Tolkien", "Britânico");
        Categoria categoria3 = new Categoria("Fantasia", "Mundo mágico");
        livroService.cadastrarLivro(new Livro("456", "O Senhor dos Anéis", autor3, categoria3));

        usuarioService.cadastrarUsuario(new Usuario("10123456789", "Jeronimo", "jeronimo@email.com", "Brasileiro"));
        usuarioService.cadastrarUsuario(new Usuario("98765432100", "João", "joao@email.com", "Brasileiro"));

        // Criando servidor na porta 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // GET /livros — lista todos os livros
        server.createContext("/livros", (HttpExchange exchange) -> {
            if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {
                responder(exchange, 405, "{\"erro\": \"Método não permitido\"}");
                return;
            }

            StringBuilder json = new StringBuilder("[");
            var livros = livroRepository.listarTodos();
            for (int i = 0; i < livros.size(); i++) {
                Livro l = livros.get(i);
                json.append("{")
                    .append("\"isbn\": \"").append(l.getIsbn()).append("\", ")
                    .append("\"titulo\": \"").append(l.getTitulo()).append("\", ")
                    .append("\"autor\": \"").append(l.getAutor().getNome()).append("\", ")
                    .append("\"categoria\": \"").append(l.getCategoria().getNome()).append("\", ")
                    .append("\"disponivel\": ").append(l.isDisponivel())
                    .append("}");
                if (i < livros.size() - 1) json.append(", ");
            }
            json.append("]");

            responder(exchange, 200, json.toString());
        });

        // POST /alugar — realiza aluguel de um livro
        // Body esperado: isbn=123&cpf=10123456789
        server.createContext("/alugar", (HttpExchange exchange) -> {
            if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                responder(exchange, 405, "{\"erro\": \"Método não permitido\"}");
                return;
            }

            // Lê o body
            String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            String isbn = extrairParam(body, "isbn");
            String cpf = extrairParam(body, "cpf");

            if (isbn == null || cpf == null) {
                responder(exchange, 400, "{\"erro\": \"Parâmetros isbn e cpf são obrigatórios\"}");
                return;
            }

            Livro livro = livroService.buscarPorIsbn(isbn);
            Usuario usuario = usuarioRepository.findByCpf(cpf);

            if (livro == null) {
                responder(exchange, 404, "{\"erro\": \"Livro não encontrado\"}");
                return;
            }

            if (usuario == null) {
                responder(exchange, 404, "{\"erro\": \"Usuário não encontrado\"}");
                return;
            }

            if (!livro.isDisponivel()) {
                responder(exchange, 409, "{\"erro\": \"Livro indisponível\"}");
                return;
            }

            aluguelService.alugar(usuario, livro);
            responder(exchange, 200, "{\"mensagem\": \"Aluguel realizado com sucesso\", \"livro\": \"" + livro.getTitulo() + "\", \"usuario\": \"" + usuario.getNome() + "\"}");
        });

        server.start();
        System.out.println("========= SERVIDOR INICIADO =========");
        System.out.println("Porta: 8080");
        System.out.println("Endpoints disponíveis:");
        System.out.println("  GET  http://localhost:8080/livros");
        System.out.println("  POST http://localhost:8080/alugar");
        System.out.println("      body: isbn=123&cpf=10123456789");
        System.out.println("=====================================");
    }

    static void responder(HttpExchange exchange, int status, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(status, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    static String extrairParam(String body, String chave) {
        for (String par : body.split("&")) {
            String[] kv = par.split("=");
            if (kv.length == 2 && kv[0].trim().equals(chave)) {
                return kv[1].trim();
            }
        }
        return null;
    }
}