package br.com.financas.api.service; // Ou br.com.financas.api.security, dependendo de onde está a sua classe

import br.com.financas.api.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        // 1. Converte a string secreta em uma chave criptográfica segura
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        long duracaoEmMillis = 2 * 60 * 60 * 1000; // 2 horas
        Date dataExpiracao = new Date(System.currentTimeMillis() + duracaoEmMillis);

        return Jwts.builder()
                .setIssuer("API Financas.com")
                .setSubject(usuario.getLogin())
                .setIssuedAt(new Date())
                .setExpiration(dataExpiracao)
                .signWith(key) // 2. Usa o objeto de chave em vez da string
                .compact();
    }

    public String getSubject(String tokenJWT) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.parser()
                .verifyWith(key) // Usa a mesma chave para verificar
                .build()
                .parseSignedClaims(tokenJWT)
                .getPayload()
                .getSubject();
    }
}