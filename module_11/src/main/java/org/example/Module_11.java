package org.example;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Module_11 {

    public String getOddNames(String... names) {

        Stream<Integer> indeces = IntStream.range(0, names.length).boxed();

        return indeces
                .filter(index -> index % 2 == 0)
                .map(index -> names[index])
                .collect(Collectors.joining(" "));

    }

    public List<String> upperAndSortNames(String... names) {
        List<String> namesList = Arrays.asList(names);
        return namesList.stream()
                .map(String::toUpperCase)
                .sorted(((s1, s2) -> s1.compareToIgnoreCase(s2)))
                .collect(Collectors.toList());
    }

    public String getDigits(String[] strings) {

        return Stream.of(strings)
                .flatMap(it -> Stream.of(it.split(", ")))
                .peek(System.out::println)
                .sorted(String::compareToIgnoreCase)
                .collect(Collectors.joining());

    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        List<T> elements = new ArrayList<>();

        Iterator<T> firstIterator = first.iterator();
        Iterator<T> secondIterator = second.iterator();

        while (firstIterator.hasNext() && secondIterator.hasNext()) {
            elements.add(firstIterator.next());
            elements.add(secondIterator.next());
        }

        return elements.stream();
    }

    private Integer generator(int previous, int value) {
        return null;
    }

    public static void main(String[] args) {
        Module_11 indexNames = new Module_11();
        String[] strings = new String[]{"1, 2, 0", "4, 5", "0"};

        String oddNames = indexNames.getOddNames("Alex", "Igor", "Tate", "Vova", "Dima");
//        System.out.println(indexNames.upperAndSortNames("Alex", "Igor", "Tate", "Amigo", "Vova", "Dima"));

        System.out.println(indexNames.getDigits(strings));

    }
}
