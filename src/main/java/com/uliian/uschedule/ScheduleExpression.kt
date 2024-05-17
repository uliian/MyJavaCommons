package com.uliian.uschedule

import java.util.*

/**
 * M month of days: the days of month split by ',' example:M 1,2,10,30
 * W week of days: the days of week split by ',' example:W 3,5,7
 * MW week of month and days of week :the days of week and week of month split by ',' example: MW 3 2,4
 */
abstract class ScheduleExpression(private val expression: String) {
    companion object {
        fun createExpression(expression: String): IUSchedule {
            val prefix = expression.split(" ").first().uppercase(Locale.getDefault())
            return when (prefix) {
                ExpressionType.DaysOfMonth.ix -> DaysOfMonthExpression(expression)
                ExpressionType.DaysOfWeek.ix -> DaysOfWeekExpression(expression)
                ExpressionType.DaysOfWeekOfMonth.ix -> DaysOfWeekOfMonthExpression(expression)
                else -> throw IllegalArgumentException("$prefix is undefined prefix")
            }
        }
    }

    protected val payload: Array<String>

    init {
        val splits = expression.split(" ")
        payload = if (splits.size > 2) arrayOf(splits[1], splits[2]) else arrayOf(splits[1])
    }

    override fun toString(): String {
        return "expression :$expression"
    }
}