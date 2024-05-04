package oop.stockexchangemanager.Database;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class Collections<T> {

   // m[10]=4;

    private Map<Integer, T> data = new HashMap<>();

    public Map<Integer, T> getData() {
        return data;
    }

    public T create(Integer key, T value) {
        if (!data.containsKey(key))
            return data.put(key, value);
        else
         throw new IllegalArgumentException("ID must be unique") ;    // Key already exists, handle this case (throw exception, return null, etc.)

    }

    public T read(Integer key) {
        return data.get(key);
    }
    public Collection<T> readAll() {
        return data.values();
    }

    public T update(Integer key, T value) {
        return data.put(key, value);
    }

    public boolean delete(Integer key) {
        return data.remove(key) != null;
    }
}
