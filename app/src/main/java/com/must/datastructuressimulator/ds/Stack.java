package com.must.datastructuressimulator.ds;

public interface Stack<T> {

    void push(T e);
    T pop();
    boolean isEmpty();
    int size();
    T peek();
    void clear();
    int getTopIndex();
}
