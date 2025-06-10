package fr.diginamic.repotp2d.service;

import fr.diginamic.repotp2d.entity.UserApp;
import fr.diginamic.repotp2d.exception.ProblemException;
import fr.diginamic.repotp2d.repository.UserAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service des opérations utilisateur
 */
@Service
public class CustomUserDetailService implements UserDetailsService
{
    /**
     * user jpa repository
     */
    @Autowired
    private UserAppRepository repository;
    /**
     * encryption de mot de passe
     */
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * Charge un utilisateur via son identifiant de connection (ici userName)
     * @param userName the userName identifying the user whose data is required.
     * @return Une entité User avec les identifiants de connection de l'utilisateur
     * @throws UsernameNotFoundException utilisateur absent en base
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
    {
        UserApp user = repository.findByUserName(userName)
                                 .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        return User.builder()
                   .username(user.getUserName())
                   .password(user.getPassword())
                   .roles("USER")
                   .build();
    }
    
    /**
     * Inscrit un utilisateur
     * @param username l'identifiant de connexion de l'utilisateur
     * @param password mot de passe
     * @return entité managée du nouvel utilisateur
     * @throws ProblemException l'utilisateur existe déjà
     */
    @Transactional
    public UserApp registerUser(String username, String password) throws ProblemException
    {
        if (repository.findByUserName(username).isPresent())
        {
            throw new ProblemException("User with username " + username + " already exists");
        }
        
        UserApp newUser = new UserApp(username, passwordEncoder.encode(password));
        return repository.save(newUser);
    }
    
    /**
     * Vérifie sur le mdp fourni correspond en base (avec encryption)
     * @param userDetails utilisateur accédant à la requête
     * @param userApp     entite JPA
     * @throws ProblemException erreur en cas de mismatch mdp
     */
    public void verifyPassword(UserDetails userDetails, UserApp userApp) throws ProblemException
    {
        if (!passwordEncoder.matches(userApp.getPassword(), userDetails.getPassword()))
        {
            throw new ProblemException("mot de passe incorrect");
        }
    }
}
