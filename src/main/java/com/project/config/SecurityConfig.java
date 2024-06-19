package com.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.security.JwtAuthenticationEntryPoint;
import com.project.security.JwtAuthenticationFilter;
import com.project.security.MyUserDetailService;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	 public static final String[] PUBLIC_URLS = {"/api/user/auth/login", "/v3/api-docs/**", "/v2/api-docs/**",
	            "/swagger-resources/**", "/swagger-ui/**","/webjars/**","/api/user/register"

	    };
	@Autowired
	private MyUserDetailService myUserDetailService;
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		 http.csrf(AbstractHttpConfigurer::disable)
         .authorizeHttpRequests(request -> request.requestMatchers(PUBLIC_URLS)
                 .permitAll()
                 .anyRequest()
                 .authenticated())
         .exceptionHandling((exception)-> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint)
         .accessDeniedPage("/error/accedd-denied"))
         .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
         .authenticationProvider(daoAuthenticationProvider())
         .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
     
     return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.myUserDetailService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public AuthenticationManager authernticationManagerBean(AuthenticationConfiguration configuration)throws Exception  {
		
		return configuration.getAuthenticationManager();
	}	
}
