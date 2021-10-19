package com.uliian.uschedule

import java.time.LocalDate
import java.time.LocalDateTime

interface IUSchedule {
    val expressionType: ExpressionType

    fun isHit(date:LocalDate):Boolean

    fun isHit(dateTime:LocalDateTime):Boolean{
        return this.isHit(dateTime.toLocalDate())
    }

    fun nextDate():LocalDate{
        return this.nextDate(LocalDate.now())
    }

    fun nextDate(date:LocalDate):LocalDate{
        return this.nextDate(date,1)[0]
    }

    fun nextDate(date:LocalDate,count:Int):List<LocalDate>{
        val result = arrayListOf<LocalDate>()
        var findedCount = 0
        for (i in 1..Int.MAX_VALUE){
            val targetDate = date.plusDays(i.toLong())
            if (this.isHit(targetDate)){
                result.add(targetDate)
                findedCount++
                if (count == findedCount){
                    break
                }
            }
        }
        return result
    }
}