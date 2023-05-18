package com.njves.empspent.model;

import java.util.List;

public abstract class Query<T> {
    protected Database database = Database.getInstance();

    abstract List<T> select();
    abstract List<T> select(T param);
    abstract T selectObject(T param);
    abstract void insert(T object);
    abstract void update(T object);

}
