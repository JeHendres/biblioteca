package biblioteca.test;

import biblioteca.controller.BibliotecaController;
import biblioteca.model.Biblioteca;

public class BibliotecaControllerTest {

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca("Biblioteca dos guri");

        BibliotecaController controller = new BibliotecaController(biblioteca);

        controller.listarAcervo();
    }
}