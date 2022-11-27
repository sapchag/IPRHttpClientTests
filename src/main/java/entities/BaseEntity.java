package entities;

import Interfaces.IEntities;
import Interfaces.ICRUD;
import models.BaseModel;

import java.util.List;

public abstract class BaseEntity<T extends BaseModel> {

    protected IEntities<T> iDbEntities;
    protected IEntities<T> iApiEntities;
    protected ICRUD<T> iCrud;

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

    public <T extends BaseModel> T add(T entity) {
        return iCrud.add(entity);
    }

    public <T extends BaseModel> T update(T entity) {
        return (T) iCrud.update(entity);
    }

    public void delete(long id) {
        iCrud.delete(id);
    }
}
