package fr.diginamic.repotp2d.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
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
    
    private SecretKey getSecuredKey()
    {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
    
    /**
     * Crée un jwt a partir d'un username
     * @param subject sujet
     * @return jwt token
     */
    public String getToken(String subject)
    {
        return Jwts.builder()
                   .subject(subject)
                   .issuedAt(new Date())
                   .expiration(new Date(System.currentTimeMillis() + 1000 * EXPIRE_IN))
                   .signWith(getSecuredKey())
                   .compact();
    }
    
    /**
     * Methode pour décrypter un token jwt
     * @param token token jwt
     * @return liste de claims liées au token
     */
    public Claims decodeToken(String token)
    {
        return Jwts.parser()
                   .verifyWith(getSecuredKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();
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
                .verifyWith(getSecuredKey())
                .build()
                .parseSignedClaims(token);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
