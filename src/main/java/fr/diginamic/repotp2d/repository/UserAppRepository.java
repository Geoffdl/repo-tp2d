package fr.diginamic.repotp2d.repository;

import fr.diginamic.repotp2d.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository des users enregistrés en base
 */
public interface UserAppRepository extends JpaRepository<UserApp, Long>
{
    Optional<UserApp> findByUserName(String userName);
}
