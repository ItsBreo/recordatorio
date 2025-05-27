package azael.josue.recordatorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import azael.josue.recordatorio.model.user;

@Repository
public interface userRepository extends JpaRepository<user,Long> {
    
}
