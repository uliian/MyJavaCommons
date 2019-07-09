package com.uliian.algorithm;

import org.apache.commons.lang3.Range;
import org.junit.Assert;
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

    @Test
    public void rangeTest() {
        Range<Integer> between = Range.between(1, 5);
        Assert.assertTrue(between.contains(1));
        Assert.assertTrue(between.contains(2));
        Assert.assertTrue(between.contains(5));
        between = Range.between(0, 0);
        Assert.assertTrue(between.contains(0));
        Assert.assertFalse(between.contains(3));
    }

    @Test
    public void shuffleListByWeightTest() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        for (int i = 0; i < 1000; i++) {
            List<Integer> result = Algorithms.shuffleListByWeight(integers, x -> x);
            System.out.println(result);
        }
    }

    @Test
    public void randomNumTest() {
        int maxNum = 0;
        int minNum = -999;
        for (int i = 0; i < 100000; i++) {

            int rm = (int) (Math.random() * (21));
            if(i == 0){
                minNum = rm;
            }
            if(rm>maxNum){
                maxNum = rm;
            }
            if(rm < minNum){
                minNum = rm;
            }
        }
        System.out.println(maxNum);
        System.out.println(minNum);
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
