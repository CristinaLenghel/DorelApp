package ro.sci.gr14.data;

import java.util.List;

public interface RepositoryInterface<T> {

    List<T> getAll();

    T getById (long Id);

    T save (T customer);

    T delete (long Id);
}