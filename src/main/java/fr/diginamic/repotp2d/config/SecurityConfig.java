package fr.diginamic.repotp2d.config;

import fr.diginamic.repotp2d.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration Spring security
 */
@Configuration
public class SecurityConfig
{
    /**
     * Configuration des accès
     * @param http   base de notre web serveur
     * @param filter filter JWT
     * @return configuration http
     * @throws Exception en cas d'erreur
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter filter) throws Exception
    {
        http
              .csrf(AbstractHttpConfigurer::disable)
              .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
              .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
              .authorizeHttpRequests(auth -> auth
                                           .requestMatchers("/login", "/register", "/h2-console/**").permitAll()
                                           .anyRequest().authenticated()
                                    )
              .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
    
    /**
     * Encodeur de mots de passes
     * @return implémentation de PasswordEncoder
     */
    @Bean
    public static PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
