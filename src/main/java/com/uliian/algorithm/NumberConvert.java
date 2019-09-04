package com.uliian.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义进制转换
 */
public class NumberConvert {

    private String charsets = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final private int length;

    public NumberConvert() {
        this.length = charsets.length();
    }

    public NumberConvert(String charsets) {
        this.charsets = charsets;
        this.length = charsets.length();
    }

    public String convertToString(long number) {
        List<Character> result = new ArrayList<>();
        long t = number;

        if (number == 0) {
            return String.valueOf(this.charsets.charAt(0));
        }

        while (t > 0) {
            long mod = t % length;
            t = Math.abs(t / length);
            char character = this.charsets.charAt((int) mod);
            result.add(0, character);
        }

        char[] chars = new char[result.size()];
        for (int i = 0; i < result.size(); i++) {
            chars[i] = result.get(i);
        }
        return String.valueOf(chars);
    }

    public long toDecimal(String number) {
        long result = 0;
        int j = 0;
        char[] chars = new StringBuilder(number).reverse().toString().toCharArray();
        char[] chars1 = this.charsets.toCharArray();
        for (char ch : chars) {
            int ix = -1;
            for (int i = 0; i < chars1.length; i++) {
                if (chars1[i] == ch) {
                    ix = i;
                    break;
                }
            }
            if (ix >= 0) {
                result += ix * ((long) Math.pow(this.length, j));
                j++;
            } else {
                throw new IllegalArgumentException(String.valueOf(ch));
            }
        }
        return result;
    }
}
