package com.uliian.collections

import java.util.concurrent.atomic.AtomicInteger

/**
 * 定长数组，固定数组长度，先进先出，通常用去边接字符串检测，如HTTP中的\r\n，boundary检测
 */
class FixedSizeArray<T>(val size: Int) {
    private val array = arrayOfNulls<Any>(size)

    private val count = AtomicInteger(0)

    fun add(item: T) {
        val ix = getWriteIndex()
        array[ix] = item
    }

    private fun getReadIndex(ix: Int): Int {
        return (this.firstIndex() + ix) % size
    }

    fun first(): T? {
        return array[firstIndex()] as T
    }

    private fun firstIndex(): Int {
        val currentCount = count.get()
        if (currentCount < size) {
            return 0
        }
        return currentCount % size
    }

    private fun getWriteIndex(): Int {
        val count = count.getAndIncrement()
        return count % size
    }

    fun get(ix: Int): T? {
        if (ix >= size) {
            throw IndexOutOfBoundsException()
        }
        return array[getReadIndex(ix)] as T
    }

    fun arrayEquals(data: Array<T>): Boolean {
        if (data.size != this.size) {
            return false
        }
        var result = true
        for ((index, value) in data.withIndex()) {
            val inValue = this.array.get(this.getReadIndex(index))
            result = result.and(inValue == value)
        }
        return result
    }
}