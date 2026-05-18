package biblioteca.model;

public class Livro {

    private String isbn;
    private String titulo;
    private Autor autor;
    private Categoria categoria;
    private boolean disponivel;

    public Livro(String isbn, String titulo, Autor autor, Categoria categoria) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.disponivel = true;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void exibirDados() {
        System.out.println("ISBN: " + isbn);
        System.out.println("Livro: " + titulo);
        System.out.println("Autor: " + autor.getNome());
        System.out.println("Categoria: " + categoria.getNome());
        System.out.println("Disponivel? " + disponivel);
    }
}