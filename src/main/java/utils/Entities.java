package utils;

import java.util.List;

public interface Entities<T> {

    T getById(int id);
    List<T> getAll();

}
