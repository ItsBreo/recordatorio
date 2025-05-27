package azael.josue.recordatorio.service;

import azael.josue.recordatorio.repository.noteRepository;
import azael.josue.recordatorio.model.note;

public class noteServiceImpl extends AbstractCrudService<note, Long> implements noteService {

    public noteServiceImpl(noteRepository repository) {
        super(repository);
    }
    
}
