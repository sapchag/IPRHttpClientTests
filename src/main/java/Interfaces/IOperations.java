package Interfaces;

public interface IOperations<T extends Object> {

    T add(Object entity);

    T update(Object entity);

    void delete(long id);

}
