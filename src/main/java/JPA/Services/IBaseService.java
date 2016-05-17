package JPA.Services;

import java.util.List;

/**
 * Created by Study on 17.05.2016.
 */
public interface IBaseService<T> {

    public T add(T item);

    public void delete(int id);

    public T get(int id);

    public void update(T item);

    public List<T> getAll();
}
