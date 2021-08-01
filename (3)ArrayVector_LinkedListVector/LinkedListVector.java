package com.company;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class LinkedListVector<T extends Serializable> implements Serializable {
    @Serial
    private static final long serialVersionUID = 777L;
    private Node<T> root;
    public LinkedListVector() {
        root = null;
    }
    public LinkedListVector(T data) {
        root = new Node<>(data);
    }
    public LinkedListVector(Node<T> root) {
        this.root = root;
    }
    @SafeVarargs
    public LinkedListVector(T ... o) {
        this(o[0]);
        int bound = o.length;
        Node<T> tmpNode = this.root;
        for (int i = 1; i < bound; i++) {
            tmpNode.setNextNode(new Node<>(o[i]));
            tmpNode = tmpNode.getNextNode();
        }
    }
    public Node<T> getRoot() {
        return root;
    }
    public void setRoot(Node<T> root) {
        this.root = root;
    }
    public T getDataByIndex(int index) throws IllegalArgumentException {
        if (index < 0) {
            throw new IllegalArgumentException();
        }
        Node<T> tmpNode = this.root;
        checkException(tmpNode);
        for (int i = 0; i < index; i++) {
            tmpNode = tmpNode.getNextNode();
            checkException(tmpNode);
        }
        return tmpNode.getData();
    }
    public void add(T data) {
        Node<T> tmpNode = this.root;
        if (tmpNode == null) {
            this.root = new Node<>(data);
            return;
        }
        while(tmpNode.getNextNode() != null) {
            tmpNode = tmpNode.getNextNode();
        }
        tmpNode.setNextNode(new Node<>(data));
    }
    public void replace(T o, T n) throws IllegalArgumentException {
        Node<T> tmpNode = this.root;
        checkException(tmpNode);
        while (!tmpNode.getData().equals(o)) {
            tmpNode = tmpNode.getNextNode();
            checkException(tmpNode);
        }
        tmpNode.setData(n);
    }
    public void insertOnPosition(int pos, T data) throws IllegalArgumentException {
        if (pos < 0) {
            throw new IllegalArgumentException();
        }
        Node<T> tmpNode = this.root;
        Node<T> prevNode = tmpNode;
        if (pos == 0) {
            this.root = new Node<>(data);
            this.root.setNextNode(tmpNode);
            return;
        }
        for (int i = 0; i < pos; i++) {
            checkException(tmpNode);
            prevNode = tmpNode;
            tmpNode = tmpNode.getNextNode();
        }
        prevNode.setNextNode(new Node<>(data));
        prevNode = prevNode.getNextNode();
        prevNode.setNextNode(tmpNode);
    }
    public void insertBefore(T data, T ins) throws IllegalArgumentException {
        Node<T> tmpNode = this.root;
        checkException(tmpNode);
        if (tmpNode.getData().equals(data)) {
            this.root = new Node<>(ins);
            this.root.setNextNode(tmpNode);
        } else {
            Node<T> prevNode = tmpNode;
            tmpNode = tmpNode.getNextNode();
            checkException(tmpNode);
            while (!tmpNode.getData().equals(data)) {
                prevNode = tmpNode;
                tmpNode = tmpNode.getNextNode();
                checkException(tmpNode);
            }
            prevNode.setNextNode(new Node<>(ins));
            prevNode = prevNode.getNextNode();
            prevNode.setNextNode(tmpNode);
        }
    }
    public void insertAfter(T data, T ins) throws IllegalArgumentException{
        Node<T> tmpNode = this.root;
        checkException(tmpNode);
        while (!tmpNode.getData().equals(data)) {
            tmpNode = tmpNode.getNextNode();
            checkException(tmpNode);
        }
        Node<T> nextNode = tmpNode.getNextNode();
        tmpNode.setNextNode(new Node<>(ins, nextNode));
    }
    public void delete(T data) throws IllegalArgumentException {
        Node<T> tmpNode = this.root;
        checkException(tmpNode);
        if (tmpNode.getData().equals(data)) {
            this.root = tmpNode.getNextNode();
        } else {
            tmpNode = tmpNode.getNextNode();
            checkException(tmpNode);
            Node<T> prevNode = tmpNode;
            while (!tmpNode.getData().equals(data)) {
                prevNode = tmpNode;
                tmpNode = tmpNode.getNextNode();
                checkException(tmpNode);
            }
            prevNode.setNextNode(tmpNode.getNextNode());
            tmpNode.setNextNode(null);
        }
    }
    public void deleteOnPosition(int pos) throws IllegalArgumentException {
        if (pos < 0) {
            throw new IllegalArgumentException();
        }
        Node<T> tmpNode = this.root;
        Node<T> prevNode = tmpNode;
        if (pos == 0) {
            this.root = this.root.getNextNode();
            return;
        }
        for (int i = 0; i < pos; i++) {
            checkException(tmpNode);
            prevNode = tmpNode;
            tmpNode = tmpNode.getNextNode();
        }
        prevNode.setNextNode(tmpNode.getNextNode());
        tmpNode.setNextNode(null);
    }
    private void checkException(Node<T> node) throws IllegalArgumentException {
        if (node == null) {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> tmpNode = this.root;
        while (tmpNode != null) {
            sb.append(tmpNode.getData().toString()).append(" ");
            tmpNode = tmpNode.getNextNode();
        }
        return sb.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedListVector<?> that = (LinkedListVector<?>) o;
        return Objects.equals(this.toString(), that.toString());
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }
}