package fr.diginamic.repotp2d.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class JwtFilter extends OncePerRequestFilter
{
    private final String SECRET = "maSuperCleSecrete123maSuperCleSecrete123";
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
          FilterChain filterChain) throws ServletException, IOException
    {
        if (request.getCookies() != null)
        {
            Stream.of(request.getCookies())
                  .filter(cookie -> cookie.getName().equals("tp2d"))
                  .map(Cookie::getValue)
                  .forEach(token ->
                           {
                               try
                               {
                                   Claims claims = Jwts.parser()
                                                       .setSigningKey(SECRET.getBytes())
                                                       .build()
                                                       .parseClaimsJws(token)
                                                       .getBody();
                                   
                                   String username = claims.getSubject();
                                   UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                                         username,
                                         null,
                                         List.of(new SimpleGrantedAuthority("ROLE_USER")));
                                   SecurityContextHolder.getContext().setAuthentication(auth);
                               }
                               catch (Exception e)
                               {
                               
                               }
                           });
        }
        filterChain.doFilter(request, response);
    }
}
