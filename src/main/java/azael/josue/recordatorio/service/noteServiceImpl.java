package azael.josue.recordatorio.service;

import azael.josue.recordatorio.repository.noteRepository;

import org.springframework.stereotype.Service;

import azael.josue.recordatorio.model.note;

@Service
public class noteServiceImpl extends AbstractCrudService<note, Long> implements noteService {

    public noteServiceImpl(noteRepository repository) {
        super(repository);
    }
    
}
