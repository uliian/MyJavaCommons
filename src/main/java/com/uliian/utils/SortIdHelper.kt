package com.uliian.utils

enum class SortType {
    ASC, DESC
}

private enum class SortAction {
    Up,
    Down
}

class ChangeSort  {
    var id:Long? = -1
    var index : Int = -1
    override fun toString(): String {
        return "ChangeSort(id=$id, index=$index)"
    }
}

/**
 * 生成新的排序ID
 * 这个方法用于拖拽排序生成新的排序ID，基于场景使用Snowflake算法给出的旧排序ID，生成新的排序ID
 *
 * @param T
 * @param sortIdSelector 排序字段
 * @param changeSort 排序变更请求
 * @param sortType 排序类型
 * @param newId 如果是排到端上，给出的新ID（新的Snowflake ID）
 * @param idSelector 主键选择器
 * @return
 */
fun <T> List<T>.generateNewSortId(
        sortIdSelector: (T) -> kotlin.Long,
        changeSort: ChangeSort,
        sortType: SortType,
        newId: Long,
        idSelector: (T) -> Any
): Long {
    if (changeSort.index == 0 && sortType == SortType.DESC) {
        return newId
    }

    if(changeSort.index == this.size-1 && sortType == SortType.ASC){
        return newId
    }

    val changeItemIx = this.indexOfFirst { idSelector(it) == changeSort.id }
    val action = if (changeSort.index > changeItemIx) SortAction.Down else SortAction.Up

    val beforeId = if (sortType == SortType.DESC) {
        when {
            changeSort.index == 0 -> newId
            action == SortAction.Down -> sortIdSelector(this[changeSort.index])
            else -> sortIdSelector(
                    this[changeSort.index - 1]
            )
        }
    } else {
        when {
            changeSort.index == 0 -> 0
            action == SortAction.Down -> sortIdSelector(this[changeSort.index])
            else -> sortIdSelector(this[changeSort.index - 1])
        }
    }
    val lastId = if (sortType == SortType.DESC) {
        when {
            changeSort.index == this.size - 1 -> 0
            action == SortAction.Down -> sortIdSelector(this[changeSort.index + 1])
            else -> sortIdSelector(
                    this[changeSort.index]
            )
        }
    } else {
        when {
            changeSort.index == this.size - 1 -> newId
            action == SortAction.Down -> sortIdSelector(this[changeSort.index+1])
            else -> sortIdSelector(this[changeSort.index])
        }
    }

    return (beforeId + lastId) / 2
}