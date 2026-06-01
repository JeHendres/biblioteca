package biblioteca.test;

import biblioteca.model.Usuario;
import biblioteca.repository.UsuarioRepository;
import biblioteca.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioServiceTest {

    private UsuarioService service;
    private UsuarioRepository repository;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        repository = new UsuarioRepository();
        service = new UsuarioService(repository);

        usuario = new Usuario("10123456789", "Jeronimo", "jeronimo@email.com", "Brasileiro");
    }

    @Test
    void deveCadastrarUsuario() {
        service.cadastrarUsuario(usuario);

        Usuario encontrado = repository.findByCpf("10123456789");

        assertNotNull(encontrado, "Usuário deve ser encontrado após cadastro");
        assertEquals("Jeronimo", encontrado.getNome(), "Nome deve ser o correto");
    }

    @Test
    void deveRemoverUsuario() {
        service.cadastrarUsuario(usuario);
        service.removerUsuario(usuario);

        Usuario encontrado = repository.findByCpf("10123456789");

        assertNull(encontrado, "Usuário removido não deve ser encontrado");
    }

    @Test
    void deveRetornarNullParaCpfInexistente() {
        Usuario encontrado = repository.findByCpf("00000000000");

        assertNull(encontrado, "Deve retornar null para CPF não cadastrado");
    }

    @Test
    void deveCadastrarMultiplosUsuarios() {
        Usuario usuario2 = new Usuario("98765432100", "João", "joao@email.com", "Brasileiro");

        service.cadastrarUsuario(usuario);
        service.cadastrarUsuario(usuario2);

        assertNotNull(repository.findByCpf("10123456789"), "Primeiro usuário deve existir");
        assertNotNull(repository.findByCpf("98765432100"), "Segundo usuário deve existir");
    }
}
