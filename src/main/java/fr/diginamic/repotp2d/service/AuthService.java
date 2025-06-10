package fr.diginamic.repotp2d.service;

import fr.diginamic.repotp2d.entity.UserApp;
import fr.diginamic.repotp2d.exception.ProblemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Service dédié aux authentifications
 */
@Service
public class AuthService
{
    /**
     * user detail service (spring security impl)
     */
    @Autowired
    private CustomUserDetailService userDetailsService;
    
    @Autowired
    private CookieService cookieService;
    
    /**
     * Login un user
     * @param userApp entité JPA
     * @return réponse serveur
     * @throws ProblemException erreur à la validation de l'utilisateur
     */
    public ResponseEntity<String> loginUser(UserApp userApp) throws ProblemException
    {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userApp.getUserName());
        userDetailsService.verifyPassword(userDetails, userApp);
        
        ResponseCookie tokenCookie = cookieService.getCookie(userApp.getUserName());
        
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, tokenCookie.toString())
                             .body("bonjour " + userApp.getUserName());
    }
    
    /**
     * Enregistre un user
     * @param userApp entité JPA
     * @return réponse serveur
     * @throws ProblemException erreur à l'inscription
     */
    public ResponseEntity<String> registerUser(UserApp userApp) throws ProblemException
    {
        userDetailsService.registerUser(userApp.getUserName(), userApp.getPassword());
        return ResponseEntity.ok().body("bienvenue");
    }
}
