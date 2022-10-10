package ru.mal.lesson04;

import java.util.Iterator;

public class LinkedListImpl<T> implements LinkedList<T> {

    protected class Node<T> {
        protected T value;
        protected Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    protected Node<T> head;

    public LinkedListImpl() {

    }

    protected Node<T> getLastNode() {
        if (head == null) {
            return null;
        }

        Node<T> last = head;
        while (last.next != null) {
            last = last.next;
        }
        return last;
    }

    @Override
    public void addLast(T element) {
        Node<T> newLastNode = new Node<>(element);
        if (head == null) {
            head = newLastNode;
            return;
        }

        Node<T> last = getLastNode();
        last.next = newLastNode;
    }

    @Override
    public void addFirst(T element) {
        Node<T> newHead = new Node<>(element);
        if (head == null) {
            head = newHead;
            return;
        }

        newHead.next = head;
        head = newHead;
    }

    @Override
    public T getFirst() {
        return extractNodeValue(head);
    }

    @Override
    public T getLast() {
        return extractNodeValue(getLastNode());
    }

    protected T extractNodeValue(Node<T> node) {
        return node == null ? null : node.value;
    }

    protected Node<T> getNode(int index) {
        if (head == null) {
            throw new IllegalArgumentException("Index " + index + " out of bounds");
        }

        int counter = 0;
        Node<T> currentNode = head;
        while (counter != index && currentNode.next != null) {
            currentNode = currentNode.next;
            counter++;
        }

        if (counter != index) {
            throw new IllegalArgumentException("Index " + index + " out of bounds");
        }

        return currentNode;
    }

    @Override
    public void add(T element, int index) {
        if (index == 0) {
            addFirst(element);
        }

        Node<T> node = getNode(index - 1); // 2
        Node<T> newNextNode = node.next; // 3
        Node<T> newNode = new Node<>(element, newNextNode); // new node
        node.next = newNode;
    }

    @Override
    public T get(int index) {
        return extractNodeValue(getNode(index));
    }

    @Override
    public void delete(int index) {
        if (index == 0) {
            if (head == null) {
                throw new IllegalArgumentException("Index " + index + " out of bounds");
            }

            head = head.next;
            return;
        }

        Node<T> node = getNode(index - 1); // 2
        Node<T> nodeForRemove = node.next; // 3
        if (nodeForRemove == null) {
            throw new IllegalArgumentException("Index " + index + " out of bounds");
        }
        Node<T> newNextNode = nodeForRemove.next; // 4
        node.next = newNextNode; // 2 -> 4
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {

        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T value = current.value;
            current = current.next;
            return value;
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        Node<T> iterator = head;
        while (iterator != null) {
            output.append(iterator.value).append(" -> ");
            iterator = iterator.next;
        }

        if (output.length() > 4) {
            output.deleteCharAt(output.length() - 1);
            output.deleteCharAt(output.length() - 1);
            output.deleteCharAt(output.length() - 1);
            output.deleteCharAt(output.length() - 1);
        }


        return "[" + output + "]";
    }
}
