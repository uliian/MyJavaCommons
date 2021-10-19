package com.uliian.uschedule

import org.junit.Assert
import org.junit.Test

class ScheduleExpressionTest {
    @Test
    fun expInitTest(){
        val monthExpression = ScheduleExpression.createExpression("M 1,3,5,20,21")
        Assert.assertEquals(monthExpression.expressionType,ExpressionType.DaysOfMonth)

        val weekExpression = ScheduleExpression.createExpression("W 1,2,3,4")
        Assert.assertEquals(weekExpression.expressionType,ExpressionType.DaysOfWeek)

        val mwe = ScheduleExpression.createExpression("MW 2 3,4,5")
        Assert.assertEquals(mwe.expressionType,ExpressionType.DaysOfWeekOfMonth)
    }

    @Test(expected = IllegalArgumentException::class)
    fun expInitFailTest(){
        val exp = ScheduleExpression.createExpression("SH 1,2,3")
    }

    @Test(expected = IllegalArgumentException::class)
    fun monthExpInitError_ThrowExp(){
        ScheduleExpression.createExpression("M 32")
    }

    @Test(expected = IllegalArgumentException::class)
    fun monthDaysExpErrorTest(){
        ScheduleExpression.createExpression("M 0,1,35")
    }

    @Test(expected = IllegalArgumentException::class)
    fun weekDaysExpErrTest(){
        ScheduleExpression.createExpression("W 8")
    }

    @Test(expected = IllegalArgumentException::class)
    fun monthWeekDaysExpWeekErrTest(){
        ScheduleExpression.createExpression("MW 6 2")
    }

    @Test(expected = IllegalArgumentException::class)
    fun monthWeekDaysExpDayErrTest(){
        ScheduleExpression.createExpression("MW 2 8")
    }
}