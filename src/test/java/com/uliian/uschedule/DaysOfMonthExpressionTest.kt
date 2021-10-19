package com.uliian.uschedule

import org.junit.Assert
import org.junit.Test
import java.time.LocalDate

class DaysOfMonthExpressionTest {
    val express:IUSchedule = ScheduleExpression.createExpression("M 10,20,25")

    @Test
    fun hitTestSuccess(){
        Assert.assertTrue(express.isHit(LocalDate.of(2021,10,10)))
        Assert.assertTrue(express.isHit(LocalDate.of(2021,1,20)))
        Assert.assertTrue(express.isHit(LocalDate.of(2021,1,25)))
        Assert.assertFalse(express.isHit(LocalDate.of(2021,11,11)))
    }

    @Test
    fun nextDaysTestSuccess(){
        val nextDates = express.nextDate(LocalDate.of(2021,1,1),10)
        Assert.assertEquals(nextDates.size,10)
        Assert.assertEquals(nextDates[0],LocalDate.of(2021,1,10))
        Assert.assertEquals(nextDates[1],LocalDate.of(2021,1,20))
        Assert.assertEquals(nextDates[2],LocalDate.of(2021,1,25))
        Assert.assertEquals(nextDates[3],LocalDate.of(2021,2,10))
        Assert.assertEquals(nextDates[4],LocalDate.of(2021,2,20))
        Assert.assertEquals(nextDates[5],LocalDate.of(2021,2,25))
        Assert.assertEquals(nextDates[6],LocalDate.of(2021,3,10))
        Assert.assertEquals(nextDates[7],LocalDate.of(2021,3,20))
        Assert.assertEquals(nextDates[8],LocalDate.of(2021,3,25))
        Assert.assertEquals(nextDates[9],LocalDate.of(2021,4,10))
    }
}