package fr.diginamic.repotp2d.controller;

import fr.diginamic.repotp2d.entity.UserApp;
import fr.diginamic.repotp2d.exception.ProblemException;
import fr.diginamic.repotp2d.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/auth"})
public class AuthController
{
    /**
     * service d'authentification
     */
    @Autowired
    private AuthService authService;
    
    /**
     * route de login
     * Si les identifiants correspondent aux identifiants en base, un cookie de validation est retourné dans le header de la requête.
     * @param userApp json au format UserApp (username, mdp)
     * @return réponse serveur
     * @throws ProblemException erreur en cas d'identifiant mismatch
     */
    @PostMapping({"/login"})
    public ResponseEntity<String> logUser(@RequestBody UserApp userApp) throws ProblemException
    {
        return authService.loginUser(userApp);
    }
    
    /**
     * route d'inscription
     * @param userApp json au format UserApp (username, mdp)
     * @return réponse serveur
     * @throws ProblemException erreur si l'identifiant existe déjà en base
     */
    @PostMapping({"/register"})
    public ResponseEntity<String> registerUser(@RequestBody UserApp userApp) throws ProblemException
    {
        return authService.registerUser(userApp);
    }
}
