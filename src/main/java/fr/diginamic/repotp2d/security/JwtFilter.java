package fr.diginamic.repotp2d.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

/**
 * Filter utilisé dans @SecurityConfig, vérifie les JWT
 */
@Service
public class JwtFilter extends OncePerRequestFilter
{
    
    /**
     * cookie name
     */
    @Value("${jwt.cookie}")
    private String COOKIE_NAME;
    
    /**
     * jwt service
     */
    @Autowired
    private JwtService jwtService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response,
          @NonNull FilterChain filterChain) throws ServletException, IOException
    {
        if (request.getCookies() != null)
        {
            Stream.of(request.getCookies())
                  .filter(cookie -> cookie.getName().equals(COOKIE_NAME))
                  .map(Cookie::getValue)
                  .forEach(token ->
                           {
                               if (jwtService.isTokenValid(token))
                               {
                                   Claims claims = jwtService.decodeToken(token);
                                   
                                   String username = claims.getSubject();
                                   UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                                         username,
                                         null,
                                         List.of(new SimpleGrantedAuthority("ROLE_USER"))
                                   );
                                   SecurityContextHolder.getContext().setAuthentication(auth);
                               }
                           });
        }
        filterChain.doFilter(request, response);
    }
}
