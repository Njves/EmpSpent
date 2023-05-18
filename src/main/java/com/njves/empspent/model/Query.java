package com.njves.empspent.model;

import java.util.List;

public abstract class Query<T> {
    protected Database database = Database.getInstance();

    public abstract List<T> select();
    public abstract List<T> select(T param);
    public abstract T selectObject(T param);
    public abstract void insert(T object);
    public abstract void update(T object);

}
