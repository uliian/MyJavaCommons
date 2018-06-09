package com.uliian.algorithm;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AlgorithmsTest {

    @Test
    public void RouletteTest() {
        List<Item> items = Arrays.asList(new Item(10, "test1"), new Item(90, "test2"));

        int test1Count = 0;
        int test2Count = 0;
        for (int i = 0; i < 10000; i++) {
            Item result = Algorithms.Roulette(items, x -> x.getWeight());
            if (result.name.equals("test1")) {
                test1Count++;
            } else {
                test2Count++;
            }
        }
        System.out.println(test1Count);
        System.out.println(test2Count);
    }

    public static class Item {
        private int weight;
        private String name;

        public Item() {
        }

        public Item(int weight, String name) {

            this.weight = weight;
            this.name = name;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
