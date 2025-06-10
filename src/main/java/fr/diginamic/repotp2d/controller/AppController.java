package fr.diginamic.repotp2d.controller;

import fr.diginamic.repotp2d.entity.UserApp;
import fr.diginamic.repotp2d.exception.ProblemException;
import fr.diginamic.repotp2d.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController
{
    @Autowired
    private AuthService authService;
    
    @GetMapping({"/hello"})
    public String sayHi()
    {
        return "hi";
    }
    
    @PostMapping({"/login"})
    public ResponseEntity<String> logUser(@RequestBody UserApp userApp) throws ProblemException
    {
        return authService.loginUser(userApp);
    }
    
    @PostMapping({"/register"})
    public ResponseEntity<String> registerUser(@RequestBody UserApp userApp) throws ProblemException
    {
        return authService.registerUser(userApp);
    }
}
