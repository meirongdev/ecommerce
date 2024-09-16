package dev.meirong.ecommerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final UserDetailsService userDetailsService;
	private final AuthenticationFilter authenticationFilter;
	private final AuthEntryPoint exceptionHandler;

	public SecurityConfig(UserDetailsService userDetailsService,
			AuthenticationFilter authenticationFilter, AuthEntryPoint exceptionHandler) {
		this.userDetailsService = userDetailsService;
		this.authenticationFilter = authenticationFilter;
		this.exceptionHandler = exceptionHandler;
	}

	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)
			throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf) -> csrf.disable())
				.sessionManagement((sessionManagement) -> sessionManagement
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
						.requestMatchers(HttpMethod.POST, "/login").permitAll().anyRequest()
						.authenticated())
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling((exceptionHandling) -> exceptionHandling
						.authenticationEntryPoint(exceptionHandler));
		return http.build();
	}
}
