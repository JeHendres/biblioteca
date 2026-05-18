package biblioteca.model;

import java.time.LocalDate;

public class Aluguel {

    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
    private Usuario usuario;
    private Livro livro;

    public Aluguel(Usuario usuario, Livro livro, int dias) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataRetirada = LocalDate.now();
        this.dataDevolucao = dataRetirada.plusDays(dias);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void realizarRetirada() {
        livro.setDisponivel(false);
        System.out.println("Livro alugado com sucesso!");
    }

    public void devolverLivro() {
        livro.setDisponivel(true);
        System.out.println("Livro devolvido.");
    }
}