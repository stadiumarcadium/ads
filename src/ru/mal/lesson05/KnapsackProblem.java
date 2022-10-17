package ru.mal.lesson05;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KnapsackProblem {
    public static void main(String[] args) {
        new KnapsackProblem().solve();
    }

    public void solve() {
        int weight = 10;
        Bag bag = new Bag(weight);
        System.out.println("Max Weight = " + weight);
        System.out.println();
        List<Thing> things = generate(5);

        System.out.println("Things:");
        things.forEach(System.out::println);
        System.out.println();

        List<Thing> best = findBest(bag, things);
        System.out.println("Best:");
        System.out.println(best);
        System.out.println("Amount = " + best.size());
        System.out.println("P = " + sumBy(best, Thing::getP));
        System.out.println("Q = " + sumBy(best, Thing::getQ));

    }

    private List<Thing> findBest(Bag bag, List<Thing> things) {
        int weightOfThings = sumBy(things, Thing::getP);
        if (weightOfThings <= bag.x) {
            return List.copyOf(things);
        }

        List<List<Thing>> candidates = new ArrayList<>();
        List<Thing> copy = new ArrayList<>(things);
        for (int i = 0; i < copy.size(); i++) {
            Thing thing = copy.get(i);
            copy.remove(thing);
            candidates.add(findBest(bag, copy));
            copy.add(i, thing);
        }

        return candidates.stream()
                .max(Comparator.comparingInt(l -> sumBy(l, Thing::getQ)))
                .orElse(List.of());
    }

    private <T> int sumBy(List<T> list, ToIntFunction<T> mapper) {
        return list.stream().mapToInt(mapper).sum();
    }

    private static List<Thing> generate(int n) {
        return IntStream.range(0, n)
                .mapToObj(it -> new Thing(generateRandomInt(), generateRandomInt()))
                .collect(Collectors.toList());
    }

    private static int generateRandomInt() {
        return 1 + ThreadLocalRandom.current().nextInt(10);
    }

    @AllArgsConstructor
    class Bag {
        private int x;

    }

    @Getter
    @AllArgsConstructor
    static class Thing {
        private int p, q;

        @Override
        public String toString() {
            return "Thing {" +
                   "p=" + p +
                   ", q=" + q +
                   '}';
        }
    }
}
