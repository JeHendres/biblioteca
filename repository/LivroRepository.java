package biblioteca.repository;

import biblioteca.model.Livro;

import java.util.ArrayList;
import java.util.List;

public class LivroRepository {

    private List<Livro> livros = new ArrayList<>();

    public void save(Livro livro) {
        livros.add(livro);
    }

    public void delete(Livro livro) {
        livros.remove(livro);
    }

    public Livro findByIsbn(String isbn) {
        for (Livro livro : livros) {
            if (livro.getIsbn().equals(isbn)) {
                return livro;
            }
        }
        return null;
    }

    public List<Livro> listarTodos() {
        return livros;
    }
}