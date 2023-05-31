package com.uliian.algorithm;

import org.junit.Assert;
import org.junit.Test;

public class NumberConvertTest {

    private final NumberConvert convert;

    public NumberConvertTest() {
        this.convert = new NumberConvert();
    }

    @Test
    public void to62Success(){

        String s = this.convert.convertToString(62L);
        Assert.assertEquals("10",s);
    }

    @Test
    public void to0Success(){
        String s = this.convert.convertToString(0L);
        Assert.assertEquals("0",s);
    }

    @Test
    public void to10Success(){
        String s = this.convert.convertToString(10L);
        Assert.assertEquals("a",s);
    }

    @Test
    public void to9566887Success(){
        String s = this.convert.convertToString(9566887L);
        Assert.assertEquals("E8MD",s);
    }

    @Test
    public void toE8MDtoDecimalSuccess(){
        long e8MD = this.convert.toDecimal("E8MD");
        Assert.assertEquals(e8MD,9566887L);
    }

    @Test
    public void randomConvertTest(){
        for (int i = 0; i < 1024; i++) {
            int r = (int) (Math.random() * Integer.MAX_VALUE);
            String s = this.convert.convertToString(r);
            long l = this.convert.toDecimal(s);
            Assert.assertEquals(r,l);
        }
    }
}
