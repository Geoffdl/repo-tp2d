package fr.diginamic.repotp2d.service;

import fr.diginamic.repotp2d.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Deprecated
public class CookieService
{
    @Autowired
    private JwtService jwtService;
    
    public ResponseEntity<String> getCookies()
    {
        String cookieName = "tp2d";
        
        String jwt = jwtService.getToken("dev@fake.com");
        
        ResponseCookie tokenCookie = ResponseCookie.from(cookieName, jwt)
                                                   .build();
        
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, tokenCookie.toString()).body("miam");
    }
}
