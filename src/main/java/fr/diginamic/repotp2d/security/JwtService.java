package fr.diginamic.repotp2d.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SignatureException;
import java.util.Date;

@Component
public class JwtService
{
    /**
     * secretKey
     */
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    /**
     * expireIn
     */
    @Value("${jwt.expires_in}")
    private long EXPIRE_IN;
    
    public String getToken(String subject)
    {
        return Jwts.builder()
                   .setSubject(subject)
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + 1000 * EXPIRE_IN))
                   .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
                   .compact();
    }
    
    public Claims decodeToken(String token) throws SignatureException
    {
        return Jwts.parser()
                   .setSigningKey(SECRET_KEY.getBytes())
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }
    
    public boolean isTokenValid(String token)
    {
        try
        {
            Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
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
