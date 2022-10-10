package ru.mal.lesson04;

public class IteratorMain {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedListImpl<>();

        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);
        linkedList.addLast(6);
        linkedList.addLast(7);
        linkedList.addLast(8);
        linkedList.addLast(9);

        for (Integer element :
                linkedList) {
            System.out.println(element);
        }

    }
}