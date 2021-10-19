package com.uliian.uschedule

import org.junit.Assert
import org.junit.Test
import java.time.LocalDate

class DaysOfWeekOfMonthExpressionTest {
    val express = ScheduleExpression.createExpression("MW 1,2 1,3,5")

    @Test
    fun hisTestSuccess(){
        Assert.assertTrue(express.isHit(LocalDate.of(2021,10,1)))
        Assert.assertTrue(express.isHit(LocalDate.of(2021,10,6)))
        Assert.assertFalse(express.isHit(LocalDate.of(2021,10,20)))
    }

    @Test
    fun generateTestSuccess(){
        val days = express.nextDate(LocalDate.of(2021,10,3),10)
        Assert.assertEquals(days.size,10)
        Assert.assertEquals(days[0],LocalDate.of(2021,10,4))
        Assert.assertEquals(days[1],LocalDate.of(2021,10,6))
        Assert.assertEquals(days[2],LocalDate.of(2021,10,8))
        Assert.assertEquals(days[3],LocalDate.of(2021,11,1))
        Assert.assertEquals(days[4],LocalDate.of(2021,11,3))
        Assert.assertEquals(days[5],LocalDate.of(2021,11,5))
        Assert.assertEquals(days[6],LocalDate.of(2021,11,8))
    }
}