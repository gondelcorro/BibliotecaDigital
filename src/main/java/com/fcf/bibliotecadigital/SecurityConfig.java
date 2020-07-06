package com.fcf.bibliotecadigital;

import com.fcf.bibliotecadigital.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration //Indicarle a spring q este va a ser un archivo de configuracion
@EnableWebSecurity //Indicarle a Spring q permita proteger las peticiones http
@EnableGlobalMethodSecurity(prePostEnabled=true)// Permitir proteger todo el contexto de la app con Spring Security
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//VARIABLES Q TRAEN LOS VALORES DEL PROPERTIES
	@Value("${security.signing-key}")
	private String signingKey;
	
	@Value("${security.encoding-strength}")
	private Integer encodingStrength;
	
	@Value("${security.security-realm}")
	private String securityRealm;
	
	//ES EL SERVICEIMPL Q ES LA Q IMPLEMENTA USERDETAILSSERVICE
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	//Instancia del manejador
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception{
		return super.authenticationManager();
	}
	//Sobreescribo la configuracion de Spring Security
	//Desde Spring 5 por defecto solicita un PasswordEncoder (un mécanismo para codificar la clave)
	//Se puede dejar desactivado o activarlo pero SIEMPRE tanto en la clave de usuario como en el clientSecret (AuthorizationServerConfig)
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		//PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		//auth.inMemoryAuthentication().withUser("fcf").password(encoder.encode("123")).roles("USER"); //con encoder en memoria
		//auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); //con encoder en BD (no me anda)
		auth.userDetailsService(usuarioService).passwordEncoder(NoOpPasswordEncoder.getInstance()); //sin encoder en bd
	}
	
	//configurar el comportamiento de los tokens
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)//SIN ESTADO
		.and()
		.httpBasic()
		.realmName(securityRealm)
		.and()
		.csrf() // Q SPRING NO GENERE TOKENS XQ LOS VA A GENERAR JWT
		.disable();
	}

	//converter contiene la instancia para crear tokens
	@Bean
	public JwtAccessTokenConverter accesTokenconverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(signingKey);
		return converter;
	}
	
	//Defino el bean (inicializacion q crea una nueva instancia es como un new)
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accesTokenconverter());
	}
	
	@Bean
	@Primary //que use este mecanismo como primario, puede haber otro pero no es el caso
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);//Q EL USUARIO PUEDA VOLVER A PEDIR TOKENS
		defaultTokenServices.setReuseRefreshToken(false);//Q EL USUARIO NO PUEDA REUTILIZAR TOKENS, OBLIGA A Q EL USUARIO SE LOGUE PASADO UN TPO
		return defaultTokenServices;
	}

}
