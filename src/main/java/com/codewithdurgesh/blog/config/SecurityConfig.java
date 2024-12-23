
  package com.codewithdurgesh.blog.config;

import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.codewithdurgesh.blog.config.jwtFilter;
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//	  
//	@Autowired
//	private UserDetailsService userdetailservice;
//	
//	
//	@Bean//this is for passwqord encryption and decryption it does decryption of database pasword and matvhes with 
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new  BCryptPasswordEncoder();
//		}
//	
//	
//	  
//	  @Bean
//	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		  http.csrf(customizer->customizer.disable());
//		  http.authorizeHttpRequests(request->request.requestMatchers("/api/v1/auth/login").permitAll()
//				 .anyRequest().authenticated());
//		  http.formLogin(form -> form.disable());
//		  
//		  return http.build();
//	  }
//	  
//	  @Bean//for database level cheking authentication we use daoauthentication
//	  public DaoAuthenticationProvider authenticationProvider() {
//		  
//		  DaoAuthenticationProvider daoauthenticationProvider=new DaoAuthenticationProvider();
//		  daoauthenticationProvider.setPasswordEncoder(passwordEncoder());
//		  daoauthenticationProvider.setUserDetailsService(userdetailservice);		  
//		  return daoauthenticationProvider;
//	  }
//	  
//	  
//	  @Bean
//	  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
//		  return configuration.getAuthenticationManager();
//	  }
//	  
//	  
//	  
//	  
//	  
//	  
//	  
//	  
//  }


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebMvc
public class SecurityConfig {

    @Autowired
    private UserDetailsService userdetailservice;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
    @Autowired
    private jwtFilter jwtFilter;
    
    
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())  // Disable CSRF for stateless API
            .authorizeHttpRequests(request -> request
                .requestMatchers("/api/v1/auth/**").permitAll()  // Allow unauthenticated access to login
              .requestMatchers("/docs").permitAll()
                .anyRequest().permitAll())  // Require authentication for all other requests
          .httpBasic(Customizer.withDefaults())
          .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
          .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);//for session so that no password and username should be saved

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoauthenticationProvider = new DaoAuthenticationProvider();
        daoauthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoauthenticationProvider.setUserDetailsService(userdetailservice);
        return daoauthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
