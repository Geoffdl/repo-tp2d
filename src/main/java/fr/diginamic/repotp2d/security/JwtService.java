package fr.diginamic.repotp2d.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.SignatureException;
import java.util.Date;

@Component
public class JwtService
{
    private static final String SECRET = "maSuperCleSecrete123maSuperCleSecrete123";
    
    public String getToken(String subject)
    {
        return Jwts.builder()
                   .setSubject(subject)
                   .setIssuedAt(new Date())
                   .signWith(SignatureAlgorithm.HS256, SECRET.getBytes())
                   .compact();
    }
    
    public Claims decodeToken(String token) throws SignatureException
    {
        return Jwts.parser()
                   .setSigningKey(SECRET.getBytes())
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }
    
    public boolean isTokenValid(String token)
    {
        try
        {
            Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .build()
                .parseClaimsJws(token);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
