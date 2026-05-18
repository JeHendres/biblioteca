package biblioteca.controller;

import biblioteca.model.Usuario;
import biblioteca.service.UsuarioService;

public class UsuarioController {

    private UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    public void criar(Usuario usuario) {
        service.cadastrarUsuario(usuario);
    }

    public void remover(Usuario usuario) {
        service.removerUsuario(usuario);
    }
}