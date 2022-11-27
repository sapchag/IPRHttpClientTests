package entities;

import Interfaces.IEntities;
import Interfaces.IOperations;

import java.util.List;

public abstract class BaseEntity<T> {

    protected IEntities<T> iDbEntities;
    protected IEntities<T> iApiEntities;
    protected IOperations<T> iOperations;

    public T dbGet(long id) {
        return iDbEntities.get(id);
    }

    public List<T> dbGetAll() {
        return iDbEntities.getAll();
    }

    public T apiGet(long id) {
        return iApiEntities.get(id);
    }

    public List<T> apiGetAll() {
        return iApiEntities.getAll();
    }

    public T add(T entity) {
        return iOperations.add(entity);
    }

    public T update(T entity) {
        return iOperations.update(entity);
    }

    public void delete(long id) {
        iOperations.delete(id);
    }
}
