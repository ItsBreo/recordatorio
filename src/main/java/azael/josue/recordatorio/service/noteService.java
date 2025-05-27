package azael.josue.recordatorio.service;

import java.util.List;
import java.util.Optional;


import azael.josue.recordatorio.model.note;

public interface noteService extends crudService<note, Long> {

    @Override
    default void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    default List<note> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    default Optional<note> getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    default note save(note entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    default note update(Long id, note entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
