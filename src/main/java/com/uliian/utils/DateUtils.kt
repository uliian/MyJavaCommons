package com.uliian.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


fun LocalDateTime.toDate(): Date {
    return Date.from(this.atZone(ZoneId.systemDefault()).toInstant())
}


fun LocalTime.toDate(): Date {
    return Date(this.toSecondOfDay().toLong())
}

fun Date.toLocalTime(): LocalTime {
    val calendar = GregorianCalendar.getInstance()
    calendar.time = this
    return LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND))
}

fun Date.toSimpleString(): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    return formatter.format(this)
}

fun LocalDate.toDate(): Date {
    return Date.from(this.atStartOfDay(ZoneId.systemDefault()).toInstant())
}

fun LocalDateTime.toSimpleString(): String {
    val df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return df.format(this)
}

fun Date.toLocalDate(): LocalDate {
    return this.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
}