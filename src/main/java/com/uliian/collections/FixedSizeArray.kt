package com.uliian.collections

import java.util.concurrent.atomic.AtomicInteger

class FixedSizeArray<T>(val size: Int) {
    private val array: ArrayList<T> = ArrayList(size)

    private val count = AtomicInteger(0)

    fun add(item: T) {
        val (ix, count) = getWriteIndex()
        if (count < size) {
            array.add(item)
        } else {
            array[ix] = item
        }
    }

    private fun getReadIndex(ix: Int): Int {
        return (this.firstIndex()+ix)%size
    }

    fun first(): T {
        return array[firstIndex()]
    }

    private fun firstIndex(): Int {
        val currentCount = count.get()
        if (currentCount < size) {
            return 0
        }
        return currentCount % size
    }

    private fun getWriteIndex(): Pair<Int, Int> {
        val count = count.getAndIncrement()
        return Pair(count % size, count)
    }

    fun get(ix: Int): T {
        if (ix >= size) {
            throw IndexOutOfBoundsException()
        }
        return array[getReadIndex(ix)]
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