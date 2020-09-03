package part5


/**
 * 【集合的使用】
 */
fun main() {

    /**
     *
     * Kotlin 的集合类同样由两个接口派生： Collection 和Map
     * Collection 和 Map 是Java 集合框架的根接口，这两个接口又包含了一些子接口或实现类。
     *
     * Kotlin 集合与Java 集合不同， Java 集合都是可变集合一一开发者可以向集合中添加、删除、修改元素
     * 但Kotlin 的集合被分成两大类： 可变集合和不可变集合。只有可变集合才能添加、删除、修改元素，不可变集合只能读取元素。
     *
     *
     * --------------------------
     *
     * Kotlin 为Collection 接口派生了一个子接口， 即MutableCollection
     * 该子接口又包含了两个子接口： MutableSet 和MutableList ， 这一分支就代表了Kotlin 的可变集合。
     *
     * 而Collection 直接派生的Set 、List 接口则代表了不可变集合。
     *
     * 因此，Kotlin 的Collection 集合体系由6 个接口组成，其中3 个是不可变的集合接口：另外3 个子接口代表了可变集合。
     *
     *
     * --------------------------
     *
     * Collections.kt
     *
     * Java不支持协变逆变，但是Kotlin支持
     *
     * out T :协变（读取安全，写入不安全）
     * int T：逆变（写入安全，读取不安全）
     *
     * 声明处型变/逆变、使用处型变/逆变
     *
     * 注意（难点）：对于不可变集合的原因，是因为这样的集合类是一个协变声明的类。只可以读取数据，不可以写入数据。
     *
     * Collection<out E>
     *      --MutableCollection
     *      --List<out E>
     *          --MutableList
     *              --ArrayList
     *      --Set<out E>
     *          --MutableSet
     *              --HashSet
     *                  --LinkedHashSet
     *
     *
     * Map<K, out V>
     *      --MutableMap
     *          --HashMap
     *              --LinkedHashMap
     *
     * --------------------------
     *
     * 纵观Kotlin集合体系，不难发现Kotlin只提供了
     * HashSet 、LinkedHashSet 、
     * HashMap 、LinkedHashMap 、
     * ArrayList、
     * 这5 个集合实现类，而且它们都是可变集合。
     * 那么说好的不可变集合呢？ Kotlin 的不可变集合类并没有暴露出来，我们只能通过函数来创建不可变集合，随后会介绍
     *
     * --------------------------
     *
     * Kotlin 在只币4 平台上并未真正实现HashSet 、LinkedHashSet 、HashMap 、LinkedHashMap 、ArrayList、
     * 只是指定了一个类型别名而己，这样就直接借用了Java 的这些集合类。此外，Kotlin为这些集合类扩展了更多的方法，因此用起来更方便。
     *
     */
    setFun1()

    /**
     * 与前面介绍的Array类相似，除Java 原生Set 的各种方法之外
     * Kotlin 的Set 还扩展了大量方法，在Set 集合中有很多方法与Array 的方法功能相似
     */
    setFun2()

    /**
     * 遍历set：for..in/forEach
     *
     * 使用for-in 循环遍历Set 元素时无须获得Set 元素的个数，也无须根据索引来访问Set元素
     * for-in 循环自动迭代Set 的每个元素，
     *
     * Set 集合继承了Iterable ，因此可使用该接口中定义的forEach（）方法来遍历集合。
     * 该方法需要接受一个Lambda 表达式作为参数，该Lambda 表达式负责对集合元素进行处理。：
     */
    setFun3()

    /**
     * 可变Set
     *
     * 使用mutableSetOf（）、hashSetOf（）、linkedSetOf（）、sortedSetOf（）函数返回的集合都是可变的，
     * 其中后面三个函数返回的集合类型都是明确的，依次是HashSet 、LinkedHashSet 、TreeSet 。
     *
     * add(element: E)  添加元素
     * addAll(elements: Collection<E ＞）  批量添加多个元素。
     * remove(element ：曰：删除指定元素，删除成功则返回true
     * removeAll(elements: Collection<E ＞）：批量删除Set 集合中的多个元素。
     * retainAll(elements: Collection<E ＞）： 只保留Set 集合中与elements 集合共有的元素（交集）。
     * clear（）： 清空集合。
     *
     *
     */
    setFun4()

    /**
     * 此外Set 和MutableSet 都包含一个iterator()，
     * 但普通Set 的iterator()方法返回的是Iterator 对象，该Iterator 对象只有hasNext()和next()两个方法
     * 而MutableSet 的iterator()方法返回的是MutableIterator 对象，该对象除hasNext()和next()两个方法之外，还提供了一个remove()方法，该方法可用于在遍历时删除元素。
     */
    setFun5()

    /**
     * 正如前面所介绍的， Kotlin 同样并未真正实现List 集合，它只是通过别名借用了Java 体系中的ArrayList 集合
     */
    listFun1()


}


fun setFun1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    //不可变Set。本质是LinkedHashSet的实例（维护了存储顺序）
    val set1 = setOf<String>("a", "b", "c")//集合元素按添加顺序排列
    //可变Set。本质是LinkedHashSet的实例（维护了存储顺序）
    val set2 = mutableSetOf<String>("a", "b", "c")
    //可变Set，本质是HashSet的实例（顺序取决于hashCode）
    val set3 = hashSetOf<String>("a", "b", "c")
    //可变Set，本质是LinkedHashSet的实例（维护了存储顺序）
    val set4 = linkedSetOf("a", "b", "c")
    //可变Set，本质是TreeSet的实例（顺序取决于自然排序、比较器排序等因素）
    val set5 = sortedSetOf("b", "a", "c")

    println(set1.javaClass)
    println(set2.javaClass)
    println(set3.javaClass)
    println(set4.javaClass)
    println(set5.javaClass)

    println("se1的集合为：$set1")
    println("se5的集合为：$set5")

}

fun setFun2() {

    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    var set = setOf<String>("Java", "Kotlin", "Go")
    //1.全部满足返回true，否则false
    println(set.all { it.length >= 4 })//false
    //2.只要有一个满足就返回true，否则返回false
    println(set.any { it.length >= 4 })//true
    //3.遍历set，返回自定义的map
    println(set.associateBy({ "$it-key" }, { "$it-value" }))
    //4.contains方法
    println("Java" in set)
    //5.按照条件过滤
    println(set.filter { it.length >= 4 })
    //6.查找符合条件的首个元素
    println(set.find { it.length >= 4 })
    //7.同Array，这里是要把所有的字符串拼接在一起
    println(set.fold("结果：", { a, b -> a + b }))
    //8.查找某个元素出现的index位置
    println(set.indexOf("Go"))
    //9.将每个集合元素映射成新值，返回所有新值组成的Set 集合
    println(set.map { "new-$it" })
    //10.获取最大值(自然排序/比较器排序决定)
    println("max = ${set.max()} , min = ${set.min()}")
    //11.反转集合，返回反转后的集合
    println(set.reversed())
    //12.取交集/取并集
    var set1 = setOf("a", "b", "c")
    var set2 = setOf("c", "d", "e")
    println(set1 intersect set2)
    println(set1 union set2)
    //13.plus/minus加减运算,operator广义运算
    println(set1 + set2)
    println(set1 - set2)

}


fun setFun3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    var set = setOf<String>("a", "b", "c")

    println("\n------通过for..in遍历--------")

    for (item in set) {
        print("$item\t")
    }

    println("\n------通过forEach遍历--------")

    set.forEach {
        print("$it\t")
    }

    println("\n------通过索引遍历--------")

    // 由于setOf()方法返回的Set 集合是有序的，是有序的（重要的事情再说一次）！
    // 因此程序可以通过索引来遍历Set 集合， Set 集合提供了indices 方法返回其索引的区间

    for (index in set.indices) {

        //看源码：if (this is List) return get(index)
        print("${set.elementAt(index)}\t")
    }
}

fun setFun4() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    var set = mutableSetOf("a", "b", "c")
    set.add("d")
    set.addAll(mutableSetOf("rrc1", "rrc2", "rrc3"))
    println(set)


    var set1 = mutableSetOf("q", "w", "t", "r", "d", "c")
    set1.remove("q")
    set1.removeAll(setOf("w", "t"))
    println(set1)

    var set2 = mutableSetOf("a", "b", "c")
    var set3 = mutableSetOf("b", "c", "d")
    set2.retainAll(set3)
    println(set2)

    set2.clear()
    println(set2)

}


fun setFun5() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    var set = mutableSetOf("a", "b", "c")
    val iterator = set.iterator()
    while (iterator.hasNext()) {
        var e = iterator.next()
        println(e)
        iterator.remove()
    }
    println(set)
}


fun listFun1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    //该函数返回不可变的List 集合.内存中实际上是：java.util.Arrays$ArrayList 的实例
    val list1 = listOf("a", "b", null)
    // 该函数返回不可变的List 集合。该函数与前一个函数的唯一区别是， 该函数会自动去掉传入的一系列参数中的null 值。
    // 简而言之，该函数返回的List 集合 不包含null 值。
    // 内存中实际上是java.util.ArrayList的实例
    val list2 = listOfNotNull("a", "b", null)
    // 该函数返回可变的MutableList 集合 ，内存中实际上是java.util.ArrayList的实例
    val list3 = mutableListOf<String>("a", "b", "c")


//    println("${list1.javaClass} -- ${list2.javaClass} -- ${list3.javaClass} -- ")

}







