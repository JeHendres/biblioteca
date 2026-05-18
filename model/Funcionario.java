package biblioteca.model;

import biblioteca.interfaces.Gerenciavel;

public class Funcionario extends Pessoa implements Gerenciavel {

    public Funcionario(String cpf, String nome, String email, String nacionalidade) {
        super(cpf, nome, email, nacionalidade);
    }

    @Override
    public void exibirDados() {
        System.out.println("=== DADOS DO FUNCIONÁRIO ===");
        super.exibirDados();
    }

    @Override
    public void cadastrarLivro() {
        System.out.println("Livro cadastrado pelo funcionário.");
    }

    @Override
    public void removerLivro() {
        System.out.println("Livro removido pelo funcionário.");
    }
}