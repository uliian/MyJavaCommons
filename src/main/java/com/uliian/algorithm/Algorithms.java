package com.uliian.algorithm;

import org.apache.commons.lang3.Range;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Algorithms {
    public static <T> T Roulette(List<T> source, Function<T, Integer> weightSelector) {
        List<AbstractMap.SimpleEntry> routerTable = new ArrayList<>();
        int begin = 0;

        for (T item : source) {
            int weight = weightSelector.apply(item);
            if (weight < 1) {
                throw new IllegalArgumentException();
            }
            int end = begin + weight-1;
            Range<Integer> range = Range.between(begin, end);
            begin = end + 1;
            AbstractMap.SimpleEntry<Range<Integer>, T> kvItem = new AbstractMap.SimpleEntry<>(range, item);
            routerTable.add(kvItem);
        }

        int rm = (int) (Math.random() * (begin));

        for (AbstractMap.SimpleEntry<Range<Integer>, T> item : routerTable) {
            if (item.getKey().contains(rm)) {
                return item.getValue();
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * 随机按权重洗牌
     *
     * @param source
     * @param weightSelector
     * @param <T>
     * @return
     */
    public static <T> List<T> shuffleListByWeight(List<T> source, Function<T, Integer> weightSelector) {
        ArrayList<T> sourceCopy = new ArrayList<>(source);

        ArrayList<T> result = new ArrayList<>(source.size());
        for (; sourceCopy.size() > 0; ) {
            T selectedItem = Roulette(sourceCopy, weightSelector);
            sourceCopy.remove(selectedItem);
            result.add(selectedItem);
        }
        return result;
    }
}
