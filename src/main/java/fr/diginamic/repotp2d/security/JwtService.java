package fr.diginamic.repotp2d.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SignatureException;
import java.util.Date;

/**
 * Service pour opérations liées aux JWT
 */
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
    
    /**
     * Crée un jwt a partir d'un username
     * @param subject sujet
     * @return jwt token
     */
    public String getToken(String subject)
    {
        return Jwts.builder()
                   .setSubject(subject)
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + 1000 * EXPIRE_IN))
                   .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
                   .compact();
    }
    
    /**
     * Methode pour décrypter un token jwt
     * @param token token jwt
     * @return liste de claims liées au token
     * @throws SignatureException erreur token
     */
    public Claims decodeToken(String token) throws SignatureException
    {
        return Jwts.parser()
                   .setSigningKey(SECRET_KEY.getBytes())
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }
    
    /**
     * Valide si le token est déchiffrable par notre clef
     * @param token jwt token
     * @return true si le token est valide
     */
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
