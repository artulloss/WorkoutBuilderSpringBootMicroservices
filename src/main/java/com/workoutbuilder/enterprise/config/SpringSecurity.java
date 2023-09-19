package com.workoutbuilder.enterprise.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Configuration class for Spring Security settings.
 * This class contains configurations related to web security such as authentication, authorization,
 * and password encoding. It configures URL patterns for protected routes and public routes.
 * The class also handles form-based login, success handlers, and logout settings.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Bean definition for password encoding using BCryptPasswordEncoder.
     *
     * @return A new instance of BCryptPasswordEncoder.
     */
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    /**
     * Configures authorized requests, form login, and logout settings.
     * @param http The HttpSecurity object to be modified.
     * @return The modified HttpSecurity object.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/signup/**").permitAll()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/error").permitAll()
                                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                                .requestMatchers("{id}/dashboard").hasRole("USER")
                                .requestMatchers("{id}/settings").hasRole("USER")
                                .anyRequest().authenticated()
                )
                .formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .successHandler(customAuthenticationSuccessHandler)
                                .permitAll()
                )
                .logout(
                        logout -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout")
                                .permitAll()
                )
                .exceptionHandling(
                        e -> e
                                .accessDeniedPage("/error")
                                .defaultAuthenticationEntryPointFor(
                                        (request, response, authException) -> {
                                            response.sendRedirect("/error");
                                        },
                                        new AntPathRequestMatcher("/**")
                                )
                );
        return http.build();
    }


    /**
     * Sets the UserDetailsService and password encoder for authentication purposes.
     * @param auth The AuthenticationManagerBuilder to modify.
     * @throws Exception if an error occurs during configuration.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}

