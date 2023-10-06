package com.uliian.utils

enum class SortType {
    ASC, DESC
}

private enum class SortAction {
    Up,
    Down
}

class ChangeSort<TKey> {
    var id: TKey? = null
    var slotIndex: Int = -1
    override fun toString(): String {
        return "ChangeSort(id=$id, slotIndex=$slotIndex)"
    }
}

class ChangeSortByIndex<TKey> {
    var id: TKey? = null
    var newIndex: Int = -1
    var oldIndex: Int = -1
    override fun toString(): String {
        return "ChangeSortByIndex(id=$id, newIndex=$newIndex, oldIndex=$oldIndex)"
    }

}

fun <T, TKey> List<T>.generateNewSortId(
    changeSort: ChangeSort<TKey>,
    sortType: SortType,
    sortIdSelector: (T) -> Long,
    newId: Long
): Long {
    val newSortIdPair:Pair<Long,Long> = when (sortType) {
        SortType.ASC -> {
            when (changeSort.slotIndex) {
                0 -> Pair(0L, sortIdSelector(this[0]))
                this.size -> Pair(sortIdSelector(this[this.size-1]),newId)
                else -> Pair(sortIdSelector(this[changeSort.slotIndex-1]),sortIdSelector(this[changeSort.slotIndex]))
            }
        }
        SortType.DESC -> {
            when (changeSort.slotIndex) {
                0 -> Pair(newId,sortIdSelector(this[0]))
                this.size -> Pair(sortIdSelector(this[this.size-1]),0L)
                else -> Pair(sortIdSelector(this[changeSort.slotIndex-1]),sortIdSelector(this[changeSort.slotIndex]))
            }
        }
    }
    return (newSortIdPair.first + newSortIdPair.second)/2
}