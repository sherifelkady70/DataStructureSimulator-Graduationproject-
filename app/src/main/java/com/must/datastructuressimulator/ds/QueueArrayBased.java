package com.must.datastructuressimulator.ds;

public class QueueArrayBased<T> implements Queue<T> {

    final private int MaxQueue;
    private int size;

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }

    private int front;
    private int rear;
    private final Object []queue;
    public QueueArrayBased(int MaxQueue)
    {
        this.MaxQueue = MaxQueue;
        this.size = 0;
        queue = new Object[MaxQueue];
        front = 0;
        rear = 0;
    }

    @Override
    public void enqueue(T e) {
        if(this.size == MaxQueue)
            throw new RuntimeException("Queue is Full");

        queue[rear] = e;
        rear = (rear + 1) % MaxQueue;
        this.size++;

    }


    @Override
    public T dequeue(){
        if (this.size == 0)
            throw new RuntimeException("Queue is Empty");

        T ret = (T) queue[front];
        front = (front + 1) % MaxQueue;
        this.size--;

        return ret;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T Front() {

        if (size == 0)
            throw new RuntimeException("Queue is Empty");

        return (T)queue[front];
    }

    @Override
    public void clear() {
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }


}
