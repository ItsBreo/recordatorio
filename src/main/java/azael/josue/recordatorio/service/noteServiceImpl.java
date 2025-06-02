package azael.josue.recordatorio.service;

import azael.josue.recordatorio.repository.noteRepository;

import org.springframework.stereotype.Service;

import azael.josue.recordatorio.model.note;

@Service
public class noteServiceImpl extends AbstractCrudService<note, Long> implements noteService {

    // Constructor que recibe el repositorio de notas
    public noteServiceImpl(noteRepository repository) {
        super(repository);
    }
    // Implementación del método update de la interfaz noteService
    @Override
    public note update(Long id, note updatedNote) {
        note existingNote = getById(id).orElseThrow(() -> 
            new RuntimeException("Nota no encontrada")
        );

        existingNote.setTitle(updatedNote.getTitle());
        existingNote.setContent(updatedNote.getContent());
        existingNote.setDateCreated(updatedNote.getDateCreated());

        if (updatedNote.getUser() != null) {
            existingNote.setUser(updatedNote.getUser());
        }

        return save(existingNote);
    }
}