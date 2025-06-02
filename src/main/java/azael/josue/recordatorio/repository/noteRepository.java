package azael.josue.recordatorio.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import azael.josue.recordatorio.model.note;

@Repository
public interface noteRepository extends JpaRepository<note, Long> {

    List<note> findByUserId(Long userId, Sort sort);

}
