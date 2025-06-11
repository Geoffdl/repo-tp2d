package fr.diginamic.repotp2d.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/hello"})
public class GreetController
{
    /**
     * route accessible à tous les utilisateurs
     * @return "hi"
     */
    @GetMapping({"/public"})
    public String sayHiPublic()
    {
        return "hi";
    }
    
    /**
     * route uniquement accessible aux utilisateurs connectés
     * @return "hi"
     */
    @GetMapping({"/private"})
    public String sayHiPrivate(Authentication authentication)
    {
        String username = (String) authentication.getPrincipal();
        return "hi " + username;
    }
}
