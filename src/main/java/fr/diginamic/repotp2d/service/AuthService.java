package fr.diginamic.repotp2d.service;

import fr.diginamic.repotp2d.entity.UserApp;
import fr.diginamic.repotp2d.exception.ProblemException;
import fr.diginamic.repotp2d.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService
{
    @Autowired
    CustomUserDetailService userDetailsService;
    @Autowired
    JwtService jwtService;
    
    public ResponseEntity<String> loginUser(UserApp userApp) throws ProblemException
    {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userApp.getUserName());
        userDetailsService.verifyPassword(userDetails, userApp);
        
        String cookieName = "tp2d";
        String jwt = jwtService.getToken(userApp.getUserName());
        
        ResponseCookie tokenCookie = ResponseCookie.from(cookieName, jwt).build();
        
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, tokenCookie.toString())
                             .body("bonjour " + userApp.getUserName());
    }
    
    public ResponseEntity<String> registerUser(UserApp userApp) throws ProblemException
    {
        userDetailsService.registerUser(userApp.getUserName(), userApp.getPassword());
        return ResponseEntity.ok().body("bienvenue");
    }
}
