package com.must.datastructuressimulator.ds;

public interface Queue<T> {

    void enqueue(T e);
    T dequeue();
    boolean isEmpty();
    int size();

    T Front();
    void clear();
}
