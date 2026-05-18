package biblioteca.test;

import biblioteca.model.Usuario;
import biblioteca.repository.UsuarioRepository;
import biblioteca.service.UsuarioService;

public class UsuarioServiceTest {

    public static void main(String[] args) {
        
        Usuario usuario = new Usuario(
                "10123456789", 
                "Jeronimo", 
                "Jeronimo@email.com", 
                "Brasileiro"
        );

        UsuarioRepository repository = new UsuarioRepository();
        UsuarioService service = new UsuarioService(repository);

        System.out.println("--- Iniciando Testes de UsuarioService ---");

        // Testando Cadastro
        System.out.println("Testando: Cadastrar Usuário...");
        service.cadastrarUsuario(usuario);

        // Testando Remoção
        System.out.println("Testando: Remover Usuário...");
        service.removerUsuario(usuario);
        
        System.out.println("--- Testes finalizados ---");
    }
}