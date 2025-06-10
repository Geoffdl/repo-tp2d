package fr.diginamic.repotp2d.config;

import fr.diginamic.repotp2d.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig
{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter filter) throws Exception
    {
        http
              .csrf(csrf -> csrf.disable())
              .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
              .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
              .authorizeHttpRequests(auth -> auth
                                           .requestMatchers("/login", "/register", "/get-cookie", "/h2-console/**").permitAll()
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
