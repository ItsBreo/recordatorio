package azael.josue.recordatorio.service;

import java.util.List;
import java.util.Optional;


import azael.josue.recordatorio.model.note;

// Interface para el servicio de notas, extendiendo la interfaz CRUD genérica
// Esta interfaz define los métodos específicos para manejar notas
public interface noteService extends crudService<note, Long> {

    @Override
    default void deleteById(Long id) {
        deleteById(id);
    }

    @Override
    default List<note> getAll() {
        return getAll();
    }

    @Override
    default Optional<note> getById(Long id) {
        return getById(id);
    }

    @Override
    default note save(note note) {
        return save(note);
    }

    @Override
    default note update(Long id, note newNote) {
        return update(id, newNote);
    }

}
