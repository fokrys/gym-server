package com.fokrys.gym.security;

import javax.servlet.Filter;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.fokrys.gym.service.CustomUserDetailsService;
import com.fokrys.gym.service.GalleryService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final CustomUserDetailsService customUserDetailsService;
	
	
	@Autowired
	public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
		super();
		this.customUserDetailsService = customUserDetailsService;
	}
	


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and()
		.authorizeRequests().antMatchers("/").permitAll().and()
        .authorizeRequests().antMatchers("/console/**").permitAll().and()
        .authorizeRequests().antMatchers("/resource/upload-dir/**").permitAll()
        .antMatchers("/static/upload-dir/**").permitAll()
        .antMatchers("/upload-dir/**").permitAll()
        .antMatchers(HttpMethod.POST, "/sign_up").permitAll()
        .antMatchers("/eventInformations/findAll").hasAnyRole("ADMIN", "USER")
        .antMatchers("/eventInformations/save").hasAnyRole("ADMIN")
        .antMatchers("/typeOfTraining/save").hasAnyRole("ADMIN")
        .antMatchers("/typeOfTraining/findAll").hasAnyRole("ADMIN","USER")
		.and()
 		.addFilter(new JWTAuthenticationFilter(authenticationManager()))
  		.addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailsService));
 		
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(getPasswordEncoder());
	}
	
	
	private PasswordEncoder getPasswordEncoder() {
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence charSequence) {
				return charSequence.toString();
			}

			@Override
			public boolean matches(CharSequence charSequence, String s) {
				return s.equals(charSequence.toString());
			}
		};
	}
	
	 @Bean
	  CorsConfigurationSource corsConfigurationSource() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	    return source;
	  }
	
	
	 @Bean
	    ServletRegistrationBean<WebServlet> h2servletRegistration(){
	        ServletRegistrationBean<WebServlet> registrationBean = new ServletRegistrationBean<WebServlet>( new WebServlet());
	        registrationBean.addUrlMappings("/console/*");
	        return registrationBean;
	    }
	 
	 @Bean
	    CommandLineRunner init(GalleryService storageService) {
	        return (args) -> {
	        	storageService.deleteAll();
	            storageService.init();
	        };
	    }

}
