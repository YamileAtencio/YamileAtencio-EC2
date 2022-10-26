package com.idat.EvaC2.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity 
@EnableGlobalMethodSecurity(prePostEnabled = true) 
public class ConfigSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailService service;
	@Autowired
	private TokenFilter filter;
	@Autowired
	private EntryPoint entrypoint;
	
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(encriptado());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/crearToken").permitAll() //TODAS LAS PETICIONES QUE ENTREN EN ESTE ENDPOINT PUEDAN ACCEDER
	    .anyRequest().authenticated() // AL RESTO NO Y PARA ACCEDAS AL RESTO DE PETICIONES DEBES ESTAR AUTENTICADO
	    .and().exceptionHandling() //QUIERO QUE SE MANEJEN EXCEPCIONES
	    .authenticationEntryPoint(entrypoint)// DE AUTENTICACION
	    .and()
	    .sessionManagement()//YA NO SE HARA USO DE LA ADMINISTRACION DE LAS SESIONES
	    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)// SINO QUE CREARE POLITICA EN LAS SESIONES
	    .and()
	    .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
	    .csrf().disable(); 
	}
	
	
	@Bean
	public PasswordEncoder encriptado() { 
		return new BCryptPasswordEncoder();
	}
	
	
	

}
