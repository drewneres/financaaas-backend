package br.com.financas.api.config;

import br.com.financas.api.model.Usuario;
import br.com.financas.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DadosIniciais implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Verifica se já existe algum usuário no banco
        if (usuarioRepository.count() == 0) {
            // Cria um novo usuário com senha codificada
            Usuario usuario = new Usuario();
            usuario.setLogin("user@email.com");
            usuario.setSenha(passwordEncoder.encode("123456")); // NUNCA salve senhas em texto puro

            usuarioRepository.save(usuario);
            System.out.println(">>> Usuário de teste criado com sucesso! Login: user@email.com, Senha: 123456");
        }
    }
}