package biblioteca.repository;

import biblioteca.model.Aluguel;
import biblioteca.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class AluguelRepository {

    private List<Aluguel> alugueis = new ArrayList<>();

    public void save(Aluguel aluguel) {
        alugueis.add(aluguel);
    }

    public void delete(Aluguel aluguel) {
        alugueis.remove(aluguel);
    }

    public List<Aluguel> findAll() {
        return alugueis;
    }

    public List<Aluguel> findByUsuario(Usuario usuario) {
        List<Aluguel> lista = new ArrayList<>();

        for (Aluguel aluguel : alugueis) {
            if (aluguel.getUsuario().equals(usuario)) {
                lista.add(aluguel);
            }
        }

        return lista;
    }
}