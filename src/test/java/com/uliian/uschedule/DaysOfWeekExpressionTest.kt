package com.uliian.uschedule

import org.junit.Assert
import org.junit.Test
import java.time.LocalDate

class DaysOfWeekExpressionTest {
    val express = ScheduleExpression.createExpression("W 1,3,5")

    @Test
    fun hitSuccessTest(){
        Assert.assertTrue(express.isHit(LocalDate.of(2021,10,20)))
        Assert.assertTrue(express.isHit(LocalDate.of(2021,10,18)))
        Assert.assertTrue(express.isHit(LocalDate.of(2021,10,22)))
        Assert.assertFalse(express.isHit(LocalDate.of(2021,10,21)))
    }

    @Test
    fun generate5DaysSuccess(){
        val beginDate = LocalDate.of(2021,10,1)
        val newDays = express.nextDate(beginDate,5)
        Assert.assertEquals(newDays.size,5)
        Assert.assertEquals(newDays[0],LocalDate.of(2021,10,4))
        Assert.assertEquals(newDays[1],LocalDate.of(2021,10,6))
        Assert.assertEquals(newDays[2],LocalDate.of(2021,10,8))
        Assert.assertEquals(newDays[3],LocalDate.of(2021,10,11))
        Assert.assertEquals(newDays[4],LocalDate.of(2021,10,13))
    }
}