# MyJavaCommons
我个人的java常用工具


## 算法部分

所在包：`com.uliian.algorithm`

赌轮盘算法：`com.uliian.algorithm.Algorithms.Roulette`
基于赌轮盘算法的权重洗牌算法：`com.uliian.algorithm.Algorithms.shuffleListByWeight`
进制转换（默认62进制）：`com.uliian.algorithm.NumberConvert`


## ID生成器部分
SnowFlake变形算法：`com.uliian.idGenerate.EasyGenerator.EasyGenerator`
够造函数两个入参，nodeId:节点ID,timeWait:能容忍的时间回拨秒数

帮助方法：`com.uliian.idGenerate.Helper.generateTimeId`这个方法用来生成在某个时间点生成的第0个ID，可用于某些和createTime相关的查询上


## 集合帮助类
`com.uliian.collections.CollectionExteionsKt.diff(java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>)`
该类为Kotlin扩展类用于计算两个集合的不同，算出新增item和移除的item，其重载方法
`com.uliian.collections.CollectionExteionsKt.diff(java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>, kotlin.jvm.functions.Function2<? super T,? super T,java.lang.Boolean>)`
第三个参数用于自定义判断元素是否相等的逻辑

`com.uliian.collections.JoinHelperKt`
这个包下的所有kotlin方法均为内存集合join帮助类，如果用kotlin可以直接调用其扩展方法，支持一对一、一对多、多对多相关join操作

`com.uliian.collections.MuitiTreeKt`
这个包下的所有方法为Kotlin下的多叉树相关操作方法，可以将线性表转为多叉树，多叉树转为线性表，多叉树内的元素搜索