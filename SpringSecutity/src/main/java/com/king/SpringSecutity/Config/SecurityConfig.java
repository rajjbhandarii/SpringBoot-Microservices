package com.king.SpringSecutity.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.king.SpringSecutity.Service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtFilter JwtFilter;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtFilter JwtFilter) {
        this.userDetailsService = userDetailsService;
        this.JwtFilter = JwtFilter;
    }

    // this method so Spring Security knows exactly how to secure your app.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrfCustomizer -> csrfCustomizer.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/register", "/login")// execpt this other URL are secured
                        .permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(JwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // This method is implemented to tell Spring Security how to verify username and
    // password.
    // * We are using DaoAuthenticationProvider which uses our own
    // UserDetailsServiceImpl to load user details and BCryptPasswordEncoder to
    // encode the password.
    // * @return AuthenticationProvider
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);// our own user details
                                                                                               // service
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;
    }

    // need to expose AuthenticationManager as a bean so that we can use it in our
    // UserService to authenticate users during login.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
