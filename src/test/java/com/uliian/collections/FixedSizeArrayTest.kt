package com.uliian.collections

import org.junit.Assert
import org.junit.Test

class FixedSizeArrayTest {
    @Test
    fun testNotOverflow(){
        val collection = FixedSizeArray<String>(5)
        collection.add("H")
        collection.add("e")
        collection.add("l")
        collection.add("l")
        collection.add("o")
        Assert.assertEquals(collection.get(0),"H")
        Assert.assertEquals(collection.get(1),"e")
        Assert.assertEquals(collection.get(2),"l")
        Assert.assertEquals(collection.get(3),"l")
        Assert.assertEquals(collection.get(4),"o")
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun outOfRangeTest(){
        val collection = FixedSizeArray<String>(5)
        collection.get(5)
    }

    @Test
    fun firstTest(){
        val collection = FixedSizeArray<String>(3)
        collection.add("H")
        collection.add("e")
        collection.add("l")
       Assert.assertEquals(collection.first(),"H")
    }

    @Test
    fun firstTestOverflow(){
        val collection = FixedSizeArray<String>(3)
        collection.add("a")
        collection.add("b")
        collection.add("c")
        collection.add("d")
        Assert.assertEquals(collection.first(),"b")
        collection.add("e")
        Assert.assertEquals(collection.first(),"c")
        collection.add("f")
        Assert.assertEquals(collection.first(),"d")
        collection.add("g")
        Assert.assertEquals(collection.first(),"e")
        collection.add("h")
        Assert.assertEquals(collection.first(),"f")
        collection.add("i")
        Assert.assertEquals(collection.first(),"g")
        collection.add("j")
        Assert.assertEquals(collection.first(),"h")
        collection.add("k")
        Assert.assertEquals(collection.first(),"i")
        collection.add("l")
        Assert.assertEquals(collection.first(),"j")
        collection.add("m")
        Assert.assertEquals(collection.first(),"k")
        collection.add("n")
        Assert.assertEquals(collection.first(),"l")
    }

    @Test
    fun getTestOverflow(){
        val collection = FixedSizeArray<String>(3)
        collection.add("a")
        collection.add("b")
        collection.add("c")
        Assert.assertEquals("b",collection.get(1))
        collection.add("d")
        Assert.assertEquals("c",collection.get(1))
        collection.add("e")
        Assert.assertEquals("d",collection.get(1))
        collection.add("f")
        Assert.assertEquals("e",collection.get(1))
        collection.add("g")
        Assert.assertEquals("f",collection.get(1))
        collection.add("h")
        Assert.assertEquals("g",collection.get(1))
        collection.add("i")
        Assert.assertEquals("h",collection.get(1))
        Assert.assertEquals("i",collection.get(2))
    }

    @Test
    fun arrayEqualTest(){
        val collection = FixedSizeArray<String>(3)
        collection.add("a")
        collection.add("b")
        collection.add("c")
        Assert.assertTrue(collection.arrayEquals(arrayOf("a","b","c")))
        collection.add("d")
        Assert.assertTrue(collection.arrayEquals(arrayOf("b","c","d")))
        collection.add("e")
        Assert.assertTrue(collection.arrayEquals(arrayOf("c","d","e")))
    }
}