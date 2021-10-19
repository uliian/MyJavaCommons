package com.uliian.uschedule

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.*


open class DaysOfWeekOfMonthExpression(expression: String) : ScheduleExpression(expression), IUSchedule {
    override val expressionType: ExpressionType = ExpressionType.DaysOfWeekOfMonth
    protected val daysOfWeeks = BitSet(7)
    protected val weeksOfMonth = BitSet(5)

    init {
        if (super.payload.size != 2) {
            throw IllegalArgumentException("this expression $expression payload size need 2,but this has ${super.payload.size}")
        }
        val weekStrs = super.payload[0].split(",")
        val dayOfWeekStrs = super.payload[1].split(",")

        for (w in weekStrs) {
            val weekInt = w.toInt()
            if (weekInt < 1 || weekInt > 5) {
                throw IllegalArgumentException("week need gt 1 and lt 5 ,but is $weekInt")
            }
            this.weeksOfMonth.set(weekInt)
        }

        for (d in dayOfWeekStrs){
            val dInt = d.toInt()
            if (dInt<1 || dInt>7){
                throw IllegalArgumentException("day of week is not valid:$dInt")
            }
            this.daysOfWeeks.set(dInt)
        }
    }

    override fun isHit(date: LocalDate): Boolean {
        val weekFields = WeekFields.of(DayOfWeek.MONDAY, 1)

        val weekNum = date.get(weekFields.weekOfMonth())
        return this.weeksOfMonth.get(weekNum) && this.daysOfWeeks.get(date.dayOfWeek.value)
    }
}