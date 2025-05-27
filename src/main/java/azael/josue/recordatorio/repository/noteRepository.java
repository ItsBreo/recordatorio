package azael.josue.recordatorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import azael.josue.recordatorio.model.note;

@Repository
public interface noteRepository extends JpaRepository<note, Long> {

}
