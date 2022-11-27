package Interfaces;

import models.BaseModel;

public interface ICRUD<T extends BaseModel> {

    <T extends BaseModel> T add(T entity);

    <T extends BaseModel> T update(T entity);

    void delete(long id);

}
