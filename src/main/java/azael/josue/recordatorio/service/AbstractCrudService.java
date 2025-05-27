package azael.josue.recordatorio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.EntityNotFoundException;

public abstract class AbstractCrudService<T, ID> implements crudService<T, ID> {

    protected final JpaRepository<T, ID> repository;

    protected AbstractCrudService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> getById(ID id) {
        return repository.findById(id);
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public T update(ID id, T entity) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("No existe el ID " + id);
        }
        return repository.save(entity);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}

