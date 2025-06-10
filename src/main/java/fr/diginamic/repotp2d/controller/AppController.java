package fr.diginamic.repotp2d.controller;

import fr.diginamic.repotp2d.entity.UserApp;
import fr.diginamic.repotp2d.exception.ProblemException;
import fr.diginamic.repotp2d.repository.UserAppRepository;
import fr.diginamic.repotp2d.security.JwtService;
import fr.diginamic.repotp2d.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController
{
    @Autowired
    private JwtService jwtService;
    @Autowired
    private CustomUserDetailService userDetailService;
    @Autowired
    private UserAppRepository repository;
    
    @GetMapping({"/hello"})
    public String sayHi()
    {
        return "hi";
    }
    
    @GetMapping({"/get-cookie"})
    public ResponseEntity<String> getCookie()
    {
        String cookieName = "tp2d";
        
        String jwt = jwtService.getToken("dev@fake.com");
        
        ResponseCookie tokenCookie = ResponseCookie.from(cookieName, jwt)
                                                   .build();
        
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, tokenCookie.toString()).body("miam");
    }
    
    @PostMapping({"/login"})
    public ResponseEntity<String> logUser(@RequestBody UserApp userApp) throws ProblemException
    {
        if (repository.findByUserName(userApp.getUserName()).isEmpty())
        {
            throw new ProblemException("aucun utilisateur trouvé");
        }
        
        String cookieName = "tp2d";
        String jwt = jwtService.getToken(userApp.getUserName());
        
        ResponseCookie tokenCookie = ResponseCookie.from(cookieName, jwt).build();
        
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, tokenCookie.toString())
                             .body("bonjour " + userApp.getUserName());
    }
    
    @PostMapping({"/register"})
    public ResponseEntity<String> registerUser(@RequestBody UserApp userApp) throws ProblemException
    {
        userDetailService.registerUser(userApp.getUserName(), userApp.getPassword());
        return ResponseEntity.ok().body("bienvenue");
    }
}
