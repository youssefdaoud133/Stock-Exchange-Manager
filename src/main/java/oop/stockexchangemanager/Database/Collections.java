package oop.stockexchangemanager.Database;

public abstract class Collections<T> {
    abstract  T create();

    abstract  T read();

    abstract  T update();

    abstract  boolean delete();
}
