package com.uliian.uschedule

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

open class DaysOfMonthExpression(protected val expression: String) : ScheduleExpression(expression),IUSchedule {
    override val expressionType: ExpressionType = ExpressionType.DaysOfMonth
    protected val days = BitSet(31)
    init {
        val daysStr = super.payload[0].split(",")
        for (day in daysStr){
            val dayInt = day.toInt()
            if (dayInt < 1 || dayInt > 31){
                throw IllegalArgumentException("the expression:$expression,is no valid")
            }
            days.set(day.toInt())
        }
    }

    override fun isHit(date: LocalDate): Boolean {
        return days.get(date.dayOfMonth)
    }
}