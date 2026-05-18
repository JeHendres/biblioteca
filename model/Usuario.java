package biblioteca.model;

public class Usuario extends Pessoa {

    public Usuario(String cpf, String nome, String email, String nacionalidade) {
        super(cpf, nome, email, nacionalidade);
    }

    @Override
    public void exibirDados() {
        System.out.println("=== DADOS DO USUÁRIO ===");
        super.exibirDados();
    }

    public void infoUsuario() {
        System.out.println("Usuário ativo no sistema.");
    }

    public void retirar() {
        System.out.println("Livro retirado.");
    }

    public void devolver() {
        System.out.println("Livro devolvido.");
    }
}