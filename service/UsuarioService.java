package biblioteca.service;

import biblioteca.model.Usuario;
import biblioteca.repository.UsuarioRepository;

public class UsuarioService {

    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void cadastrarUsuario(Usuario usuario) {
        repository.save(usuario);
    }

    public void removerUsuario(Usuario usuario) {
        repository.delete(usuario);
    }
}