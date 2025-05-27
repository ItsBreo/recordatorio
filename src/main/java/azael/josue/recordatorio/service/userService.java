package azael.josue.recordatorio.service;

import java.util.List;
import java.util.Optional;

import azael.josue.recordatorio.model.user;

public interface userService extends crudService<user, Long> {

    @Override
    default void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    default List<user> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    default Optional<user> getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    default user save(user entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    default user update(Long id, user entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
