package com.uliian.collections;


import kotlin.Pair;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class JoinHelperTest {

    @Test
    public void joinTest() {
        List<Integer> lst1 = Arrays.asList(1, 2, 3);
        List<Integer> lst2 = Arrays.asList(2, 3, 4);
        List<Pair<Integer, Integer>> pairs = JoinHelperKt.innerJoinFirst(lst1, lst2, (a, b) -> a.equals(b));
        System.out.println(pairs);
    }
}
