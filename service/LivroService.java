package biblioteca.service;

import biblioteca.interfaces.Gerenciavel;
import biblioteca.model.Livro;
import biblioteca.repository.LivroRepository;

public class LivroService implements Gerenciavel {

    private LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    @Override
    public void cadastrarLivro() {
        System.out.println("Livro cadastrado via service.");
    }

    public void cadastrarLivro(Livro livro) {
        repository.save(livro);
    }

    @Override
    public void removerLivro() {
        System.out.println("Livro removido via service.");
    }

    public void removerLivro(Livro livro) {
        repository.delete(livro);
    }

    public Livro buscarPorIsbn(String isbn) {
        return repository.findByIsbn(isbn);
    }
}