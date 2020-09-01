package part5

/**
 * 【五、数组与集合】
 *
 */
fun main() {

    /**
     * Kotlin 为数组增加了一个Array 类，为元素是基本类型的数组增加了XxxArray 类（其中Xxx 可以是Byte 、Short 、Int 等基本类型）
     * 因此开发者完全可用面向对象的语法来使用Kotlin的数组，包括创建数组对象、调用数组对象的属性和方法等
     *
     * Array<T> --> 引用类型
     * Kotlin 既可允许通过Array<T>类的构造器来创建实例，也可通过arrayOf()等工具函数来创建实例。
     *
     */

    /**
     * Kotlin 创建数组大致有如下两种方式。
     *  -使用arrayOf()、arrayOfNulls()、emptyArray()工具函数。
     *  -使用Array(size: Int, init: (Int) -> T）构造器。
     */
    arrayFun1()


    /**
     * Kotlin 专门提供了ByteArray 、ShortArray 、IntArray、LongArray 、CharArray 、FloatArray、DoubleArray 、BooleanArray
     * 分别用于映射Java 的byte[]、short[]、int[]、long[]、char[]、float[]、double[] 、boolean[] 这8 种基本类型的数组。
     * 创建XxxArray 对象的方式与前面介绍的方式大致相同。
     *
     * 同时提供了byteArrayOf、shortArrayOf、intArrayOf、longArrayOf、charArrayOf 、floatArrayOf、doubleArrayOf 、booleanArrayOf等方法创建array
     */
    arrayFun2()


    /**
     * 数组的常用方法就是访问数组元素，包括对数组元素进行赋值和取出数组元素的值。
     * 访问数组元素都是通过在数组引用变量后紧跟一个方括号（ ［］ 〉实现的，方括号里是数组元素的索引值
     * Kotlin的方括号运算符其实是get(index）和set(index, value ）方法，
     * 因此，当程序使用［i］ 获取数组元素的值时，实际上就是调用get( index） 方法；
     * 当使用［index ］对数组元素赋值时，实际上就是调用set(in dex , value）方法。
     */
    arrayFun3()

    /**
     * array有一个size属性，来获取array的长度
     */
    arrayFun4()

    /**
     * array有一个indices属性，获取array的索引区间（双闭区间）[0 ~ size-1]
     */
    arrayFun5()

    /**
     *array有一个lastIndex 属性， 该属性用于返回数组最后一个元素的索引值，该索引值通常等于size - 1
     */
    arrayFun6()

    /**
     * 如果程序需要同时访问数组的索引和元素，则也可使用数组的withIndex() 方法，该方法返回一个Iterable 对象，
     * 该对象的所有元素都是IndexedValue
     *
     * 复习：
     * for-in 循环可用于遍历任何可迭代对象。
     * 所谓可选代对象就是该对象包含一个iterator()方法，且该方法的返回值对象具有next()、hasNext()方法，
     */
    arrayFun7()


    /**
     * Kotlin 的数组是由Array 、XxxArray 这些类代表的，而Kotlin 为这些类提供了大量的工具方法(例如前面介绍的withIndex()方法)
     * 程序只要使用这些方法即可非常方便地操作数组。
     */
    arrayFun8()

    /**
     * 由于数组还提供了用operator 修饰的plus()、contains()方法，因此数组可通过＋、in 运算符进行运算。
     */
    arrayFun9()


    /**
     * 多维数组
     * 所谓的多维数组其实都是一维数组，只要让数组的元素又是数组，那么该数组就变成了多维数组。
     */
    arrayFun10()


}


fun arrayFun1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    //1、这种方式无须显式指定数组的长度，但需要依次列出每个数组元素。因此，这种方式其实就相当于Java 数组的静态初始化。
    // 使用这种方式创建数组时， 由于程序己经给出了每个数组元素，因此Kotlin 可以推断出数组元素的类型。所以， 不需要在arrayOf（） 函数上使用泛型来指定数组元素的类型。
    var arr1 = arrayOf<String>("a,", "b", "c")

    //2、这种方式需要显式指定数组的长度，数组元素全部被初始化为null ，可见这种方式就是Java 数组的动态初始化。
    // 使用这种方式创建数组时，由于 Kotlin 无法推断出数组元素的类型，所以需要在arrayOfNulls()函数上使用泛型来指定数组元素的类型。
    var arr2 = arrayOfNulls<String>(3)

    //3、这种方式会创建一个长度为0 的空数组。由于没有指定数组 元素，因此需要使用泛型来指定数组元素的类型。
    var arr3 = Array<String>(5) { "${it}-hello" }
    for (i in arr3) {
        println(i)
    }

    //4、这种方式需要显式指定数组的长度，并可通过Lambda 表达式来动态计算各数组元素的值。这种方式是原来Java 所不具备的。
    var arr4 = emptyArray<String>()

}


fun arrayFun2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    val arr1 = intArrayOf(1, 2, 3)
    val arr2 = shortArrayOf(1, 2, 3)
    val arr3 = booleanArrayOf(true, false, false)

    var arr4 = BooleanArray(4) {
        it % 2 == 0
    }
    for (i in arr4) {
        println(i)
    }

}


fun arrayFun3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    var arr = arrayOf("a", "b", "c")
    println("${arr[0]} -- ${arr.get(0)}")

    arr[1] = "bb"
    arr.set(2, "cc")

    println(arr.contentToString())
}


fun arrayFun4() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    val arr = arrayOfNulls<String>(4)
    arr[0] = "a"
    arr[1] = "b"
    for (index in 0 until arr.size) {
        println(arr[index])
    }
}


fun arrayFun5() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    var arr = arrayOf("a", "b", "c")
    for (index in arr.indices) {
        println(arr[index])
    }
    //此外，程序还可使用 、！ in 来检查某个索引是否位于数组的索引区间内
    println("${5 in arr.indices}")
}


fun arrayFun6() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    var arr = arrayOf("a", "b", "c")
    println(arr.lastIndex)
    for (index in 0..arr.lastIndex) {
        println(arr[index])
    }
}


fun arrayFun7() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    var arr = arrayOf("a", "b", "c")
    for (item in arr.withIndex()) {
        println("${item.index}==${item.value}")

    }
}


fun arrayFun8() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    var arr = arrayOf(2, 4, 5, 6)
    //1、all(predicate: (T} -> Boolean ）：使用Lambda 表达式要求所有数组元素都满足该表达式， 如果都满足，那么该方法返回true 。
    val b1 = arr.all {
        it > 4
    }
    //2、any(predicate: (T) -> Boolean ）：使用Lambda 表达式要求只要其中一个数组元素都满足该表达式，那么该方法返回true 。
    val b2 = arr.any { it > 1 }
    println("$b1 -- $b2")

    //3、asList(): 将该数组转换成List 集合。
    val asList = arr.asList()
    println(asList)

    //4、contentToString()：把数组转换成字符串，相当于Arrays 类的toString() 方法。
    println(arr.contentToString())

    //5、associate(transform: (T) -> Pair<K, V ＞） ：使用Lambda 表达式根据数组元素进行计算， 返回元素是＜K,V＞的Map 集合。
    var arr1 = arr.associate {
        //返回值是Pair<K, V>，意味着返回值是 （a to b）的形式
        it + 10 to it * it
    }
    println(arr1)

    //6、associateByTo(dest: M , keySelector: (T) - > K） ：使用Lambda 表达式根据数组元素进行计算
    // 并将计算得到的＜K, T＞对添加到可变Map 集合中，该方法返回被修改的dest 集合。
    var map = mutableMapOf(8 to 800, 9 to 900)
    arr.associateByTo(map, { it }, { it * 100 })
    println(map)

    //7、fold(initial: R, operation: (ace: R, T）
    // -将[initial、数组元素]作为参数传入operation 表达式执行计算
    // -将计算得到的结果作为下一个数组元素的initial,
    // -依此类推，直到使用最后一个数组元素计算，该方法返回使用最后一个数组元素计算得到的值。
    val fold = arr.fold(10, { initial, item -> initial + item })
    println(fold)

    //8、contentEquals(other: Array<out T ＞）： 比较两个数组是否相等。a 数组和a2 数组的长度相等，每个元素依次相等， 将输出true
    var a = arrayOf(1, 2, 3, 4)
    var b = arrayOf(2, 3, 4, 5)
    var c = arrayOf(1, 2, 3, 4)
    println("${a.contentEquals(b)} -- ${a.contentEquals(c)}")

    //9、fill( element: T , fromIndex: Int= 0 , toIndex: Int = size ） ：该方法将会把数组中从企fromIndex到toIndex(不包含) 赋值为T
    var arrF = arrayOf(1, 2, 3, 4, 5, 6, 7)
    arrF.fill(100, 1, 4)
    println(arrF.contentToString())

    //10、sort（fromIndex: Int= 0, toIndex: Int= size ）： 该方法对数组的元素按自然排序（要求所 有元素实现Comparable 接口）进行排列。
    var arrS = arrayOf(1, 5, 3, 8, 2, 6)
    arrS.sort()
    println(arrS.contentToString())

    //11、sortWith(comparator: Comparator<in T＞）： 该方法对数组的所有元素按comparator 排 序（定制排序〉进行排列
    var arrS1 = arrayOf(1, 5, 3, 8, 2, 6)
    arrS1.sortWith { o1, o2 -> o1 - o2 } //Comparator<T> --> int compare(T o1, T o2) 抽象类
    println(arrS1.contentToString())

}


fun arrayFun9() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    var a = arrayOf(1, 2, 3)
    var b = arrayOf(2, 3, 4)
    println((a + b).contentToString())

}


fun arrayFun10() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    var arr = arrayOfNulls<Array<Int>>(3)
    arr[0] = arrayOf(9, 9, 9, 9)
    arr[1] = arrayOf(8, 8, 8, 8)
    arr[1] = Array(4) { it * 100 }
    for (i in arr.indices) {
        for (j in arr[0]!!.indices) {
            print("${arr[i]?.get(j)}\t")
        }
        println()
    }

    println("-------------")

    // 二行三列
    // 100 200 300
    // 200 300 400

    var arr2 = Array(2) { i ->
        IntArray(3) { j ->
            (j + 1) * 100 + i * 100
        }
    }
    for (i in arr2.indices) {
        println(arr2[i].contentToString())
    }

    println("-------------")

    var arr3 = arrayOf(arrayOfNulls<Int>(3), arrayOf(1, 2))
    for (i in arr3.indices) {
        println(arr3[i].contentToString())
    }

}




