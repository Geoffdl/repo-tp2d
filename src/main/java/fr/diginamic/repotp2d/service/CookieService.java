package fr.diginamic.repotp2d.service;

import fr.diginamic.repotp2d.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service

public class CookieService
{
    /**
     * jwt service
     */
    @Autowired
    private JwtService jwtService;
    
    /**
     * cookie name
     */
    @Value("${jwt.cookie}")
    private String COOKIE_NAME;
    
    /**
     * Genere un cookie pour un utilisateur se connectant
     * @param userName nom utilisateur
     * @return cookie d'identification
     */
    public ResponseCookie getCookie(String userName)
    {
        String jwt = jwtService.getToken(userName);
        return ResponseCookie.from(COOKIE_NAME, jwt)
                             .build();
    }
}
