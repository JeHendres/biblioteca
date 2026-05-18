package biblioteca.test;

import biblioteca.controller.BibliotecaController;
import biblioteca.model.Biblioteca;

public class BibliotecaControllerTest {

    public static void main(String[] args) {
        
        // Instanciando a entidade principal
        Biblioteca biblioteca = new Biblioteca("Biblioteca Municipal");
        
        // Injetando a dependência no controller
        BibliotecaController controller = new BibliotecaController(biblioteca);

        System.out.println("--- Iniciando Testes de BibliotecaController ---");

        // Testando listagem do acervo
        System.out.println("Testando: Listar Acervo...");
        controller.listarAcervo();

        // Testando listagem de usuários
        System.out.println("Testando: Listar Usuários...");
        controller.listarUsuarios();
        
        System.out.println("--- Testes finalizados ---");
    }
}