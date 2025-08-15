package br.com.financas.api.service;

import br.com.financas.api.model.Usuario; // Verifique se o import para Usuario está correto
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        return Jwts.builder()
                .setIssuer("API Financas.com") // Identificador de quem emitiu o token
                .setSubject(usuario.getLogin()) // Identifica o usuário do token
                .setIssuedAt(new Date()) // Data de emissão
                .setExpiration(dataExpiracao()) // Define o prazo de validade
                .signWith(SignatureAlgorithm.HS256, secret) // Assina o token com o algoritmo e a chave secreta
                .compact(); // Constrói a string do token
    }

    // O método para validar o token (vamos usar depois)
    public String getSubject(String tokenJWT) {
        return Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(tokenJWT)
                .getBody()
                .getSubject();
    }

    private Date dataExpiracao() {
        // Token válido por 2 horas a partir de agora
        long duracaoEmMillis = 2 * 60 * 60 * 1000; // 2 horas
        return new Date(System.currentTimeMillis() + duracaoEmMillis);
    }
}