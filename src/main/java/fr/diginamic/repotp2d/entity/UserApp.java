package fr.diginamic.repotp2d.entity;

import jakarta.persistence.*;

/**
 * Entité JPA utilisateur
 */
@Entity
@Table(name = "user_app")
public class UserApp
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String userName;
    private String password;
    
    public UserApp()
    {
    }
    
    public UserApp(String email, String password)
    {
        this.userName = email;
        this.password = password;
    }
    
    /**
     * Getter
     * @return id
     */
    public Long getId()
    {
        return id;
    }
    
    /**
     * Setter
     * @param id sets value
     */
    public void setId(Long id)
    {
        this.id = id;
    }
    
    /**
     * Getter
     * @return userName
     */
    public String getUserName()
    {
        return userName;
    }
    
    /**
     * Setter
     * @param userName sets value
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    /**
     * Getter
     * @return password
     */
    public String getPassword()
    {
        return password;
    }
    
    /**
     * Setter
     * @param password sets value
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
}
