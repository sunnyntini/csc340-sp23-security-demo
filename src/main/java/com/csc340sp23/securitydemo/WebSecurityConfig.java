package com.csc340sp23.securitydemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author sunny
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/", "/home").permitAll()
                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                .requestMatchers("/mod/**").hasAnyRole("MOD", "ADMIN")
                .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                .loginPage("/login")
                .permitAll()
                ).exceptionHandling((x) -> x.accessDeniedPage("/403"))
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user
                = User.withDefaultPasswordEncoder()
                        .username("youngling")
                        .password("password")
                        .roles("USER")
                        .build();

        UserDetails mod
                = User.withDefaultPasswordEncoder()
                        .username("padawan")
                        .password("modpass")
                        .roles("MOD")
                        .build();

        UserDetails admin
                = User.withDefaultPasswordEncoder()
                        .username("knight")
                        .password("adminpass")
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user, mod, admin);
    }
}
