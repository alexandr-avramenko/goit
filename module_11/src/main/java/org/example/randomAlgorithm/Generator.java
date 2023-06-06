package org.example.randomAlgorithm;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator {
    private final long seed;

    public Generator(long seed) {
        this.seed = seed;
    }

    public Stream<Long> generateRandom (long a, long c, long m) {
        return Stream.iterate(this.seed, x -> randomPseudo(a, c, m, x));
    }

    private static long randomPseudo(long a, long c, long m, Long x) {
        return (a * x + c) % m;
    }

    public static void main(String[] args) {
        Generator g = new Generator(5);
        List<Long> l;
        l = g.generateRandom(4,5,12).limit(5).peek(System.out::println).collect(Collectors.toList());
    }
}