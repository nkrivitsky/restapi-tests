package dao;

import java.util.List;

public interface IDao<T> {

    List<T> get(long id);

    List<T> getAll();

    void insert(T t);

    void insertList(List<T> t);

    void update(long id, T t);

    void delete(T t);

    void deleteList(List<T> t);

    void deleteAll();
}
