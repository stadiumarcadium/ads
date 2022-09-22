package ru.mal.lesson02;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Generator {
    private static final List<Double> prices = List.of(100.00, 200.00, 300.00, 400.00, 500.00, 600.00, 700.00);
    private static final List<Integer> rams = List.of(4, 8, 16, 20, 24);
    private static final List<Notebook.Brand> brands = List.of(Notebook.Brand.values());

    public static List<Notebook> generateNotebooks() {
        return IntStream.range(0, 10000).mapToObj(o -> generateNotebook()).toList();
    }

    private static Notebook generateNotebook() {
        return new Notebook(
                randomizer(prices),
                randomizer(rams),
                randomizer(brands)
        );
    }

    private static <T> T randomizer(List<T> list) {
        return list.get(ThreadLocalRandom.current().nextInt(0, list.size()));
    }
}
