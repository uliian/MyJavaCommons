package com.uliian.collections

/**
 * 线性列表转为多叉树
 *
 * @param T
 * @param TKey
 * @param keySelector key选择器
 * @param parentKeySelector 父key选择器
 * @return 多叉树，按KEY排序，如果存在多个根，则第一个根即是关键根，如果要去掉那些中间节点断掉的根，则取结果的第一个元素即可。
 */
fun <T : IMultiTreeItem<T>, TKey : Comparable<TKey>> Iterable<T>.toMultiTree(keySelector: (T) -> TKey, parentKeySelector: (T) -> TKey): ArrayList<T> {
    val map = this.associateBy { keySelector(it) }.toSortedMap()
    val rootList = arrayListOf<T>()
    map.forEach {
        val parentKey = parentKeySelector(it.value)
        val parent = map[parentKey]
        if (parent != null) {
            parent.children.add(it.value)
        } else {
            rootList.add(it.value)
        }
    }

    return rootList
}

/**
 * 多叉树转线性列表
 *
 * @param T
 * @param outList
 * @return
 */
fun <T : IMultiTreeItem<T>> Iterable<T>.multiTreeToList(outList:ArrayList<T> = arrayListOf()):List<T>{
    for (item in this){
        if(item.children.isNotEmpty()){
            item.children.multiTreeToList(outList)
            item.children.clear()
            outList.add(item)
        }else{
            outList.add(item)
        }
    }
    return outList
}

/**
 * 在多叉树中查找符合条件的数据
 *
 * @param T
 * @param condition
 */
fun <T : IMultiTreeItem<T>> Iterable<T>.searchItems(condition:(T)->Boolean, outList: ArrayList<T> = arrayListOf()): ArrayList<T> {
    for (item in this){
        if(item.children.isNotEmpty()){
            item.children.searchItems(condition, outList)
            item.children.clear()
            if(condition(item)){
                outList.add(item)
            }
        }else{
            if(condition(item)){
                outList.add(item)
            }
        }
    }
    return outList
}