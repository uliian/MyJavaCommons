package com.uliian.algorithm;

import org.apache.commons.lang3.Range;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Algorithms {
    public static <T> T Roulette(List<T> source, Function<T,Integer> weightSelector){
        List<AbstractMap.SimpleEntry> routerTable = new ArrayList<>();
        int begin = 0;
        for (T item : source) {
            int end = begin+weightSelector.apply(item);
            Range<Integer> range = Range.between(begin, end);
            begin = end+1;
            AbstractMap.SimpleEntry<Range<Integer>, T> kvItem = new AbstractMap.SimpleEntry<>(range, item);
            routerTable.add(kvItem);
        }

        int rm = (int) (Math.random() * (begin));

        for (AbstractMap.SimpleEntry<Range<Integer>, T> item : routerTable) {
            if(item.getKey().contains(rm)){
                return item.getValue();
            }
        }
        throw new IllegalArgumentException();
    }
}
