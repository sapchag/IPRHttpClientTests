package Interfaces;

import java.util.List;

public interface IEntities<T> {

    T get(long id);
    List<T> getAll();

}
