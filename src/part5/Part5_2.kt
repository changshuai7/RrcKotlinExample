package part5

import kotlin.collections.Iterable
/**
 * 【集合的使用】
 */
fun main() {


    /**
     * 重点提示：
     * 1、提示1：Kotlin的集合要面向可变集合、不可变集合编程，不要面向具体的集合实现类来编程
     * 2、提示2：以set为例，如果想使用LinkedHashSet，建议使用linkedSetOf。
     *      虽然使用setOf、mutableSetOf、都返回了LinkedHashSet，但这个是由Kotlin内部实现的，依然具有不可控的因素。
     *      list和map同理
     *
     */


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
     * in T：逆变（写入安全，读取不安全）
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
     * Kotlin 中并未真正实现HashSet 、LinkedHashSet 、HashMap 、LinkedHashMap 、ArrayList、
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

    /**
     * List 同样提供了与Set 相似的集合操作方法。通常来说， Set 支持的操作， List 一般都能支持，它还增加了通过索引操作集合元素的方法。
     *
     * get ： 带operator 修饰的方法，因此可用“［］“运算符访问集合元素。
     * indexOf ： 返回集合元素在List中的索引。
     * lastIndexOf： 返回集合元素在List中最后一次的出现位置。
     * sublist ： 返回List集合的 截断部分（from --> to）的子集合。
     */
    listFun2()

    /**
     * 使用mutableListOf()、arrayListOf()函数返回的List 集合都是可变
     * 接下来就可以对该List 的元素执行添加、插入、删除、替换等操作了。
     * 原来在可变的Set 中介绍的方法，可变的List 也完全支持，它还增加了一些根据索引执行插入、删除、替换的方法。
     *
     */
    listFun3()

    /**
     * 与Java 相同的是， Kotlin 的Map 集合同样用于保存key-value 对；
     * 与Java 不同的是， Kotlin的Map 集合也被分为可变的和不可变的。
     */
    mapFun1()


    /**
     * 使用Map的方法
     */
    mapFun2()

    /**
     * 遍历Map
     * 遍历Map 集合时既可通过对key-value 对进行遍历，也可先遍历key ，再通过key 来获取对应的value 进行遍历。
     * Kotlin 的Map 提供了operator 修饰的get（）方法，因此可通过“［］”运算符根据key 来获取value 。
     * 此外， Map 也可直接用for-in 循环进行遍历，这时循环变量的类型是Entry （即key-value对）。
     */
    mapFun3()

    /**
     * 可变Map
     * 使用mutableMapOf（）、hashMapOf（）、linkedMapOf（）、sortedMapOf（）函数返回的集合都是可变的，
     * 其中后面三个函数返回的集合类型都是明确的，依次是HashMap 、LinkedHashMap 、TreeMap
     *
     * clear（）： 清空所有的key-value 对。
     * put(key: K, value: V ）： 放入key-value 对。如果原来己有该key，那么新放入的value会覆盖原有的value 。
     * putAll(from: Map<out K, V＞）：批量放入多个key-value 对。
     * remove(key: K）：删除key-value 对
     *
     *
     */
    mapFun4()


}


fun setFun1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    //不可变Set。本质是LinkedHashSet的实例（维护了存储顺序）
    val set1 = setOf("a", "b", null)//集合元素按添加顺序排列
    //setOf去除null值
    val set11 = setOfNotNull("a", "b", null)
    //可变Set。本质是LinkedHashSet的实例（维护了存储顺序）
    val set2 = mutableSetOf<String>("a", "b", "c")
    //可变Set，本质是HashSet的实例（顺序取决于hashCode）
    val set3 = hashSetOf<String>("a", "b", "c")
    //可变Set，本质是LinkedHashSet的实例（维护了存储顺序）
    val set4 = linkedSetOf("a", "b", "c")
    //可变Set，本质是TreeSet的实例（顺序取决于自然排序、比较器排序等因素）
    val set5 = sortedSetOf("b", "a", "c")
    //可变Set，本质是TreeSet的实例（这里传入了比较器的lambda）
    val set6 = sortedSetOf<String>({ o1, o2 -> o1.length - o2.length }, "aaaa", "bb", "cccccc")



    println(set1.javaClass)
    println(set2.javaClass)
    println(set3.javaClass)
    println(set4.javaClass)
    println(set5.javaClass)

    println("set1的集合为：$set1")
    println("set11的集合为：$set11")
    println("set5的集合为：$set5")
    println("set6的集合为：$set6")

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
    val list4 = arrayListOf("a", "b", "c")

    println(list1)
    println(list2)


    println("listOf的类型：${list1.javaClass} \n" +
            "listOfNotNull的类型：${list2.javaClass} \n" +
            "mutableListOf的类型：${list3.javaClass}\n" +
            "arrayListOf的类型：${list4.javaClass}")

}


fun listFun2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val list = listOf("a", "b", "c", "b", null)
    println(list.get(0))//a
    println(list[0])//a
    println(list.indexOf("b"))//1
    println(list.lastIndexOf("b"))//3
    println(list.subList(3, list.size))//[b,null]

    for (i in list.indices) {
        print("${list[i]}\t")
    }
}


fun listFun3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val list = arrayListOf<String>("a", "b", "c", "rrc", "ddd")
    list.add("hello")
    list.add(0, "kotlin")
    println("添加完元素：$list")
    list.removeAt(list.size - 1)
    println("删除完元素：$list")
    list[list.size - 1] = "good"
    println("修改完元素：$list")
    list.clear()
    println("清空list：$list")
}


fun mapFun1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    // vararg pairs: Pair<K, V>  ：Pair就是 a to b的形式，varargs是可变参数

    // mapOf()、mutableMapOf)、linkedMapOf()创建的Map集合能维护元素的添加顺序，
    // sortedMapOf() 函数创建的Map 集合会按照key 大小对key-value 对排序。
    // 如果真正希望Map 集合不保证key-value 对的顺序，只有通过hashMapOf()函数创建Map集合才行。
    // 这也是由Java 集合框架提供的HashMap 、LinkedHashMap 、TreeMap 实现类的特 征所决定的。

    //该函数返回【不可变】的Map集合，内存中实际上是LinkedHashMap的实例
    val map1 = mapOf("a" to 1, "b" to 2)
    //该函数返回可变的Map集合，内存中实际上是LinkedHashMap的实例
    val map2 = mutableMapOf("a" to 1, "b" to 2)

    // 但从目前的实现来看，上面两个方法返回的Map 集合都会记住key-value 对的添加顺序->这是由LinkedHashMap 的特征所决定的。

    //该函数返回可变的Map集合，内存中实际上是HashMap的实例
    val map3 = hashMapOf("a" to 1, "b" to 2)
    //该函数返回可变的Map集合，内存中实际上是LinkedHashMap的实例
    val map4 = linkedMapOf("a" to 1, "b" to 2)
    //该函数返回可变的Map集合，内存中实际上是TreeMap的实例
    val map5 = sortedMapOf("a" to 1, "b" to 2)
    val map6 = sortedMapOf({ o1, o2 -> o1.length - o2.length }, "aaa" to 1, "bb" to 2)

    println(map1.javaClass)
    println(map2.javaClass)
    println(map3.javaClass)
    println(map4.javaClass)
    println(map5.javaClass)

    println(map1)
    println(map2)
    println(map3)
    println(map4)
    println(map5)
    println(map6)

}

fun mapFun2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    var map = mapOf("Java" to 86, "Kotlin" to 92, "Go" to 76)
    println(map.all { it.key.length > 4 && it.value > 80 })//false
    println(map.any { it.key.length > 2 && it.value > 80 })//true
    println(map.filter { it.key.length > 4 })
    println("Java" in map)//operator contains方法 决定

    println("-----map遍历-----")
    val list = map.map {
        println("${it.key} -- ${it.value}")
        "${it.key}-${it.value}"
    }
    println(list)


    // 由于Map 集合提供了 operator 修饰的plus 、minus 方法，因此可使用＋、-运算符操作集合。
    val map1 = mapOf("a" to 1, "b" to 2, "c" to 3)
    val map2 = mapOf("c" to 3, "d" to 4, "e" to 5)

    //求并集
    println(map1 + map2)


}


fun mapFun3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val map = mapOf<String, Int>("a" to 1, "b" to 2, "c" to 3)

    //map.iterator() //Iterator<Map.Entry<K, V>> = entries.iterator()
    //  Map.Entry<K, V>
    //  public val key: K
    //  public val value: V


    println("------map.entries------")
    for (item in map.entries) {
        println("key = ${item.key} value = ${item.value}")
    }

    println("------map迭代器------")
    for (item in map) {
        println("key = ${item.key} value = ${item.value}")
    }

    println("-----map.keys-------")
    for (key in map.keys) {
        println("key = $key value = ${map[key]}")
    }


    println("------解构赋值------")
    for ((key, value) in map) {
        println("key = $key value = $value")
    }

}

fun mapFun4() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val map = mutableMapOf<String, Int>("a" to 1, "b" to 2, "c" to 3)

    map.put("d", 4)
    map["e"] = 5
    map.putAll(mapOf("ff" to 6, "gg" to 7))

    println(map)

    map.remove("gg")
    println(map)

    map.clear()
    println(map)
}









