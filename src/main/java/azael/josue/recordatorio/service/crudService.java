package azael.josue.recordatorio.service;

import java.util.List;
import java.util.Optional;


// Clase CRUD genérica para manejar operaciones básicas de entidades
public interface crudService<T, ID> {
    /**
     * List all entities.
     *
     * @return a list of all entities
     */
    List<T> getAll();
    
    /**
     * Get an entity by its ID.
     *
     * @param id the ID of the entity
     * @return an Optional containing the entity if found, or empty if not found
     */
    Optional<T> getById(ID id);
    
    /**
     * Save a new entity.
     *
     * @param entity the entity to save
     * @return the saved entity
     */
    T save(T entity);
    
    /**
     * Update an existing entity.
     *
     * @param id the ID of the entity to update
     * @param entity the updated entity
     * @return the updated entity
     * @throws EntityNotFoundException if the entity with the given ID does not exist
     */
    T update(ID id, T entity);
    
    /**
     * Delete an entity by its ID.
     *
     * @param id the ID of the entity to delete
     */
    void deleteById(ID id);
}