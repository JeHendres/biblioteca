package biblioteca.controller;

import biblioteca.model.Livro;
import biblioteca.service.LivroService;

public class LivroController {

    private LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    public void criar(Livro livro) {
        service.cadastrarLivro(livro);
    }

    public void remover(Livro livro) {
        service.removerLivro(livro);
    }

    public void detalhar(String isbn) {
        Livro livro = service.buscarPorIsbn(isbn);

        if (livro != null) {
            livro.exibirDados();
        }
    }
}