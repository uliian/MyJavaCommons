package com.uliian.collections

data class DataChanges<T>(val removeItems: List<T>, val newItems: List<T>)

private interface WrapperData<T> {
    val data: T
}

/**
 * 计算两个基合的不同（利用HashCode方法，效率高）
 *
 * @param T
 * @param newList
 * @return 比较结果
 */
fun <T> Iterable<T>.diff(newList: Iterable<T>): DataChanges<T> {
    return this.diff(newList) { a, b -> a == b }
}

/**
 * 计算两个集合的不同（利用compare方法，效率低）
 *
 * @param T
 * @param newList
 * @param compare 比较两个元素是否相同
 * @return
 */
fun <T> Iterable<T>.diff(newList: Iterable<T>, compare: (T, T) -> Boolean): DataChanges<T> {
    val removeItems = this.filter { o -> !newList.any { n -> compare(o, n) } }
    val newItems = newList.filter { n -> !this.any { o -> compare(o, n) } }
    return DataChanges(removeItems, newItems)
}

