package com.inventario.vacunacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.inventario.vacunacion.security.JwtAuthorization;
import com.inventario.vacunacion.service.EmpleadoController;
import com.inventario.vacunacion.service.TokenController;
import com.inventario.vacunacion.service.VacunaController;

@SpringBootApplication
@ComponentScan(basePackageClasses = EmpleadoController.class)
@ComponentScan(basePackageClasses = VacunaController.class)
@ComponentScan(basePackageClasses = TokenController.class)
@EntityScan("com.inventario.vacunacion.entity")
@EnableJpaRepositories("com.inventario.vacunacion.repository")
public class MsInvVacunacionApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MsInvVacunacionApplication.class, args);
	}
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JwtAuthorization(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				//para generar token de acceso desde mi app
				.antMatchers(HttpMethod.POST, "/oauth2/token").permitAll()
				//empleado
			    .antMatchers(HttpMethod.POST,"/empleado/crear").authenticated()
			    .antMatchers(HttpMethod.PUT,"/empleado/actualizar").authenticated()
			    .antMatchers(HttpMethod.DELETE,"/empleado/eliminar").authenticated()
			    .antMatchers(HttpMethod.GET,"/empleado/consultar").permitAll()
			    .antMatchers(HttpMethod.GET,"/empleado/consultarPorId").permitAll()
			    .antMatchers(HttpMethod.GET,"/empleado/consultarPorEstadoVacunacion").permitAll()
			    //vacuna
			    .antMatchers(HttpMethod.POST,"/vacuna/empleado/crear").authenticated()
			    .antMatchers(HttpMethod.GET,"/vacuna/empleado/consulta").permitAll()
			    .antMatchers(HttpMethod.GET,"/vacuna/empleado").permitAll()
			    .antMatchers(HttpMethod.GET,"/vacuna/consultarPorTipoVacuna").permitAll();
		}
	}

}
