package com.uliian.utils

import org.junit.Assert
import org.junit.Test

data class ItemData(val id:String,val title:String,val sortId:Long)
class SortIdHelperKtTest{
    @Test
    fun newSortIdGenerate_Asc_test(){
        val lst = listOf(
            ItemData("000303","test2",201203798885007360),
            ItemData("000308","test6",201203798885007380),
            ItemData("000307","test7",553310458744930304),
            ItemData("000301","test3",905417118604853248),
            ItemData("000306","test4",1207222805121204224)
        )
        var changeSort = ChangeSort<String>()
        changeSort.id = "000301"
        changeSort.slotIndex = 1
        var newId = lst.generateNewSortId(changeSort,SortType.ASC,{it.sortId},1208222805121204224)
        Assert.assertEquals((201203798885007360+201203798885007380)/2,newId)

        changeSort.id = "000301"
        changeSort.slotIndex = 0
        newId = lst.generateNewSortId(changeSort,SortType.ASC,{it.sortId},1208222805121204224)
        Assert.assertEquals((201203798885007360+0)/2,newId)

        changeSort.id = "000301"
        changeSort.slotIndex = 5
        newId = lst.generateNewSortId(changeSort,SortType.ASC,{it.sortId},1208222805121204224)
        Assert.assertEquals((1207222805121204224+1208222805121204224)/2,newId)
    }

    @Test
    fun newSortIdGenerate_desc_test(){
        val lst = listOf(
            ItemData("000303","test2",201203798885007360),
            ItemData("000308","test6",201203798885007380),
            ItemData("000307","test7",553310458744930304),
            ItemData("000301","test3",905417118604853248),
            ItemData("000306","test4",1207222805121204224)
        )
        var changeSort = ChangeSort<String>()
        changeSort.id = "000301"
        changeSort.slotIndex = 1
        var newId = lst.generateNewSortId(changeSort,SortType.DESC,{it.sortId},1208222805121204224)
        Assert.assertEquals((201203798885007360+201203798885007380)/2,newId)

        changeSort.id = "000301"
        changeSort.slotIndex = 0
        newId = lst.generateNewSortId(changeSort,SortType.DESC,{it.sortId},1208222805121204224)
        Assert.assertEquals((201203798885007360+1208222805121204224)/2,newId)

        changeSort.id = "000301"
        changeSort.slotIndex = 5
        newId = lst.generateNewSortId(changeSort,SortType.DESC,{it.sortId},1208222805121204224)
        Assert.assertEquals((1207222805121204224+0)/2,newId)

    }
}