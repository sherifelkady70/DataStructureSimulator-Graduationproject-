package com.must.datastructuressimulator.ds;

public class ArrayBasedStack<T> implements Stack<T> {

    final private int MaxStack;
    int top;
    int size;
    Object []stack;

    private static int EMPTY = 0;

    public ArrayBasedStack(int MaxStack)
    {
        this.MaxStack = MaxStack;
        this.size = 0;
        stack = new Object[MaxStack];
        this.top = 0;
    }

    @Override
    public void push(T e) {
        if(this.size == MaxStack)
            throw new RuntimeException("Stack is Full");

        stack[top++] = e;
        this.size++;
    }

    @Override
    public T pop() {
        if(this.size == 0)
            throw new RuntimeException("Stack is Empty");

        T val = (T) stack[--top];
        this.size--;
        return val;
    }

    @Override
    public boolean isEmpty() {
        return this.size == EMPTY;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T peek() {
        if(this.size == 0)
            throw new RuntimeException("Stack is Empty");

        return (T) stack[top - 1];
    }

    public int getTopIndex()
    {
        return top;
    }
    @Override
    public void clear() {
        this.size = 0;
        this.top = 0;
    }
}
