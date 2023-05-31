package com.uliian.collections

import org.junit.Assert
import org.junit.Test

data class Data(val id: Long, val name: String)
data class TreeData(val id: String, val name: String,override val children: ArrayList<TreeData> = arrayListOf()) : IMultiTreeItem<TreeData>

class CollectionTests {

    @Test
    fun intersectTest() {
        val t = listOf(1, 2, 3).intersect(listOf(2, 3, 4))
        println(t)
    }

    @Test
    fun minusTest() {
        var t = listOf(1, 2, 3).minus(listOf(2, 3, 4))
        println(t)
        t = listOf(2, 3, 4).minus(listOf(1, 2, 3))
        println(t)
    }

    @Test
    fun diffTest_Meta_Type() {
        val old = listOf(1, 2, 3, 4)
        val new = listOf(3, 4, 5, 6)
//        val (removed,added) = old.diff(new)
        val (removed, added) = old.diff(new)
        Assert.assertEquals(listOf(1, 2), removed)
        Assert.assertEquals(listOf(5, 6), added)
    }

    @Test
    fun diffTest_complex() {
        val old = listOf(Data(1, "a"), Data(2, "2"), Data(3, "3"))
        val new = listOf(Data(2, "2"), Data(3, "3"), Data(4, "4"))

        val (removed, added) = old.diff(new) { a, b -> a.id == b.id }
        Assert.assertEquals(removed[0].id, 1)
        Assert.assertEquals(added[0].id, 4)
    }

    @Test
    fun multiTree_generate_Test() {
        val listData = listOf(
                TreeData("00",name = "00"),
                TreeData("0001",name = "0001"),
                TreeData("0002","0002"),
                TreeData("000101","000101"),
                TreeData("01","01")
        )
        val tree = listData.toMultiTree({it.id},{it.id.substring(0,it.id.length-2)})
        Assert.assertEquals((tree[0].children[0].children[0]).id,"000101")
        println(tree)
        val list = tree.multiTreeToList()
        Assert.assertTrue(list.size == 5)
        Assert.assertTrue(list.none { it.children.isNotEmpty() })
    }

    @Test
    fun multiTree_search_test(){
        val listData = listOf(
                TreeData("00",name = "00"),
                TreeData("0001",name = "0001"),
                TreeData("0002","0002"),
                TreeData("000101","000101"),
                TreeData("01","01")
        )
        val tree = listData.toMultiTree({it.id},{it.id.substring(0,it.id.length-2)})
        val result = tree.searchItems({it.id=="000101"})
        Assert.assertTrue(result.size == 1)
        Assert.assertTrue(result[0].id == "000101")
    }
}