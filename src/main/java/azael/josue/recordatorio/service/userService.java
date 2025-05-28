package azael.josue.recordatorio.service;

import java.util.List;
import java.util.Optional;

import azael.josue.recordatorio.model.user;

public interface userService extends crudService<user, Long> {

    @Override
    default void deleteById(Long id) {
        deleteById(id);
    }

    @Override
    default List<user> getAll() {
        return getAll();
    }

    @Override
    default Optional<user> getById(Long id) {
        return getById(id);
    }

    @Override
    default user save(user user) {
        return save(user);
    }

    @Override
    default user update(Long id, user newUser) {
        return update(id, newUser);
    }

}
