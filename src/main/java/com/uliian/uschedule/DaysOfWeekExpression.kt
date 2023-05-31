package com.uliian.uschedule

import java.time.LocalDate
import java.util.*

open class DaysOfWeekExpression(expression: String) : ScheduleExpression(expression), IUSchedule {
    override val expressionType: ExpressionType = ExpressionType.DaysOfWeek
    protected val days = BitSet(7)
    init {
        val dayStrs = super.payload[0].split(",")
        for (day in dayStrs){
            val dayInt = day.toInt()
            if (dayInt<1 || dayInt >7){
                throw IllegalArgumentException("the expression:$expression,is no valid")
            }
            days.set(dayInt)
        }
    }

    override fun isHit(date: LocalDate): Boolean {
        return this.days.get(date.dayOfWeek.value)
    }
}