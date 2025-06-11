package fr.diginamic.repotp2d.repository;

import fr.diginamic.repotp2d.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository des users enregistrés en base
 */
@Repository
public interface UserAppRepository extends JpaRepository<UserApp, Long>
{
    Optional<UserApp> findByUserName(String userName);
}
