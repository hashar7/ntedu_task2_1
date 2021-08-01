package com.company;

import java.io.Serial;
import java.io.Serializable;

public class Node<T extends Serializable> implements Serializable {
    @Serial
    private static final long serialVersionUID = 777L;
    private T data;
    private Node<T> nextNode;
    public Node() {
        data = null;
        nextNode = null;
    }
    public Node(T data) {
        this.data = data;
        nextNode = null;
    }
    public Node(T data, Node<T> nextNode) {
        this.data = data;
        this.nextNode = nextNode;
    }
    public Node<T> getNextNode() {
        return nextNode;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }
}
