package utils;

public interface Operations<T> {

    T add(T entity);
    T update(T entity);
    void delete(int id);

}
