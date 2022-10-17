package ru.mal.lesson02;

import java.util.Comparator;
import java.util.List;

public class NotebookMain {

    public static void main(String[] args) {

        List<Notebook> notebooks = Generator.generateNotebooks();

        notebooks.stream().sorted(Comparator.comparing(Notebook::getPrice)
                        .thenComparing(Notebook::getRam)
                        .thenComparingInt(o -> o.getBrand().ordinal()))
                .forEach(System.out::println);
    }

}
