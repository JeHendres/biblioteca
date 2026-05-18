package biblioteca.controller;

import biblioteca.model.Biblioteca;

public class BibliotecaController {

    private Biblioteca biblioteca;

    public BibliotecaController(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void listarAcervo() {
        biblioteca.exibirDados();
    }

    public void listarUsuarios() {
        biblioteca.exibirDados();
    }
}