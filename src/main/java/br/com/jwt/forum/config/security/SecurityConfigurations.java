package br.com.jwt.forum.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	private static final String URL_TOPICOS = "/topicos";

	/** Configurações de autenticação **/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	}

	/** Configurações de autorização **/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, URL_TOPICOS).permitAll()
		.antMatchers(HttpMethod.GET, URL_TOPICOS + "/*").permitAll()
		.anyRequest().authenticated()
		.and().formLogin(); //Utiliza formulario de login padrao do spring
	}

	/** Configurações de recursos staticos, imgs, js, css etc... **/
	@Override
	public void configure(WebSecurity web) throws Exception {

	}

}
