package br.com.jwt.forum.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.jwt.forum.modelo.Usuario;
import br.com.jwt.forum.repository.UsuarioRepository;

//Essa service e chamada automaticamente pelo spring, essa service não tem um controle implementato manualmente
@Service
public class AutenticacaoService implements UserDetailsService{
	
	private static final String USUARIO_INVALIDO = "Usuário inválido!";
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
		if(usuario.isPresent()) {
			return usuario.get();
		}
		throw new UsernameNotFoundException(USUARIO_INVALIDO);
	}

}
