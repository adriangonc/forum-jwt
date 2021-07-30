package br.com.jwt.forum.config.security;

import java.util.Date;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.jwt.forum.modelo.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {
		Usuario usuarioLogado = null;
		try {
			usuarioLogado = (Usuario) authentication.getPrincipal();
		} catch (Exception e) {
			System.out.println("Erro ao buscar usuário");
			e.printStackTrace();
		}
		Date dataGeracaoToken = new Date();
		Date dataExpiracao = new Date(dataGeracaoToken.getTime() + Long.parseLong(expiration));
		
		try {
			return Jwts.builder()
					.setIssuer("API cliente forum")
					.setSubject(usuarioLogado.getId().toString())
					.setIssuedAt(dataGeracaoToken)
					.setExpiration(dataExpiracao)
					.signWith(SignatureAlgorithm.HS256, secret)
					.compact();
		} catch (Exception e) {
			System.out.println("Erro ao gerar token");
			e.printStackTrace();
		}
		return null;
	}

	public boolean isTokenValido(String token){
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e){
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
}
