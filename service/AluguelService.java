package biblioteca.service;

import biblioteca.model.Aluguel;
import biblioteca.model.Livro;
import biblioteca.model.Usuario;
import biblioteca.repository.AluguelRepository;

public class AluguelService {

    private AluguelRepository repository;

    public AluguelService(AluguelRepository repository) {
        this.repository = repository;
    }

    public void alugar(Usuario usuario, Livro livro) {
        if (livro.isDisponivel()) {
            Aluguel aluguel = new Aluguel(usuario, livro, 7);
            aluguel.realizarRetirada();
            repository.save(aluguel);
        } else {
            System.out.println("Livro indisponível.");
        }
    }

    public void devolver(Aluguel aluguel) {
        aluguel.devolverLivro();
        repository.delete(aluguel);
    }
}