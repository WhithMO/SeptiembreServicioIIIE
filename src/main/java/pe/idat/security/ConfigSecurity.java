package pe.idat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;

@Configuration										// con esto, decimos que esta clase sera de tipo configuracion.
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)	// aperturar todos los metodos de seguridad para poder usarlo dentro de esta clase.
public class ConfigSecurity extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailService service;
	
	@Autowired
	private TokenFilter filter;
	
	@Autowired
	private EntryPoint entrypoint;
	
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {		// este metodo y el siguiente se crea con click derecho / SOURCE /implement...
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		// En este caso no es necesario usar el @BEAN
//		auth.inMemoryAuthentication().withUser("PROFESOR").password(encriptado().encode("123")).roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("ALUMNO").password(encriptado().encode("123")).roles("USER");
		auth.userDetailsService(service).passwordEncoder(encriptado());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//			.antMatchers("/producto/v1/guardar").access("hasRole('ADMIN')")
//			.antMatchers("/producto/v1/listar").permitAll()
//			.and()
//			.httpBasic()
//			.and()
//			.csrf().disable();
		http.authorizeHttpRequests()
		.antMatchers("/crearToken").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(entrypoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
		.csrf().disable();
	}

	
	
	
//	@Bean
//	public UserDetailsService userDetail() {
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//		manager.createUser(
//					User.withUsername("profesor")
//					.password(encriptado().encode("123"))
//					.roles("ADMIN")
//					.build()			// el BUILD es para guardar en el contenedor.
//				);
//		return manager;
//	}
//	
	@Bean			// BEAN:  Sirve Guardar en el contenedor de SPRING (por inyeccion de dependencias)
	public PasswordEncoder encriptado()  {
		return new BCryptPasswordEncoder();
	}
//	
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		
//		http.authorizeRequests()
//		.antMatchers("/producto/v1/*").access("hasRole('ADMIN')")							// esta entarda acepta a METODOS o URLs (Paths)
//		.and()
//		.httpBasic()
//		.and()
//		.csrf().disable();		// el CSRF es la seguridad que se pone para las peticiones, en este caso lo desactivamos para poder hacer pruebas en local.
//		
//		return http.build();
//	}
}
