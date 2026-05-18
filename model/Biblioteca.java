package biblioteca.model;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private String nome;
    private List<Livro> livros;
    private List<Usuario> usuarios;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void exibirDados() {
        System.out.println("Biblioteca: " + nome);
        System.out.println("Quantidade de livros: " + livros.size());
        System.out.println("Quantidade de usuários: " + usuarios.size());
    }
}