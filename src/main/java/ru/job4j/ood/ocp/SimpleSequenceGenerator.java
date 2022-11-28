package ru.job4j.ood.ocp;

import ru.job4j.ood.srp.SequenceGenerator;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SimpleSequenceGenerator implements SequenceGenerator<Integer> {

    @Override
    public List<Integer> generate(int size) {
        Random random = new Random();
        return IntStream.range(0, size)
                .map(i -> random.nextInt()).boxed()
                .collect(Collectors.toList());
    }
}
