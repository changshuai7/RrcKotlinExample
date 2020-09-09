package part6

/**
 * 【Lambda表达式】
 */
fun main() {

    /**
     * 上节介绍了，在函数体内部定义函数，这种被放在函数体内定义的函数称为局部函数。
     * 局部函数的函数名没有太大的意义，那么就考虑使用Lambda 表达式来简化局部函数的写法。
     */
    lambdaFun1()


    /**
     * Lambda表达式的脱离
     *
     * 作为函数参数传入的Lambda 表达式可以脱离函数独立使用
     */
    lambdaFun2()

    /**
     * Lambda表达式格式：
     * ｛（形参列表） －>
     *      零条到多条可执行语句}
     */

    /**
     * 正如前面所介绍的， Lambda 表达式的语法与局部函数非常相似
     * 因此Lambda 表达式的形参列表的语法以及执行体与局部函数几乎相同。Lambda 表达式的语法与局部函数的主要区别就是前面提到的几点。
     * 此外， Lambda 表达式还可做进一步的优化，从而使得程序更加简洁。
     */

    lambdaFun3()

    /**
     * 根据上下文推断类型
     * 完整的Lambda 表达式需要定义形参类型
     * 但是如果Kotlin 可以根据Lambda 表达式上下文推断出形参类型，那么Lambda 表达式就可以省略形参类型。
     */
    lambdaFun4()


    /**
     * Lambda 表达式不仅可以省略形参类型，而且如果只有一个形参，那么Kotlin 允许省略Lambda 表达式的形参名。
     * 如果Lambda 表达式省略了形参名，那么此时 -＞也不需要了， Lambda表达式可通过it 来代表形参。
     */
    lambdaFun5()


    /**
     * Kotlin 语言有一个约定：
     * 如果函数的最后一个参数是函数类型，而且你打算传入一个Lamb出表达式作为相应的参数，那么就允许在圆括号之外指定Lambda 表达式。
     * 在其他语言中这种用法被称为“尾随闭包（ Tail Closure ）”。
     */
    /**
     * 根据上面介绍不难发现，通常我们建议将函数类型的形参放在形初忱的最后
     * 这样方便以后传入Lambda 表达式作为参数。
     */
    lambdaFun6()

    /**
     * 个数可变的参数和lambda参数
     *
     * 虽然Kotlin 允许将个数可变的形参定义在形参列表的任意位置
     * 但如果不将个数可变的形参放在形参列表的最后，那么就只能（必须）用命名参数的形式为可变形参之后的其他形参传入参数值。
     * （否则系统无法辨识可变参数后的数据是属于可变参数的形参，还是属于后面参数的形参）
     *
     * 但之前又建议将函数类型的形参放在形参列表的最后
     *
     * 此时就产生了一个问题： 到底是将个数可变的形参放在最后，还是将函数类型的形参放在最后呢？
     *
     * Kotlin约定：如果调用函数时最后一个参数是Lambda 表达式，则可将Lambda 表达式放在圆括号外面，这样就无须使用命名参数了。
     * 因此答案是：如果一个函数既包含个数可变的形参， 也包含函数类型的形参，那么就应该将函数类型的形参放在最后。
     *
     */
    lambdaFun7()


}


fun lambdaFun1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    println(getFunc1("times")(5))
    println(getFunc2("times")(5))


}

fun getFunc1(type: String): (Int) -> Int {
    //f1.f2.f3函数名没有太大意义
    fun f1(x: Int) = x + x
    fun f2(x: Int) = x * x
    fun f3(x: Int) = x
    return when (type) {
        "plus" -> ::f1
        "times" -> ::f2
        else -> ::f3
    }
}

fun getFunc2(type: String): (Int) -> Int {
    //f1.f2.f3函数名没有太大意义，所以考虑用lambda代替
    return when (type) {
        "plus" -> { x: Int -> x + x }
        "times" -> { x: Int -> x * x }
        else -> { x: Int -> x }
    }

    /**
     * 定义Lambda 表达式与局部函数只是存在如下区别。
     * 1、Lambda 表达式总是被大括号括着。
     * 2、定义Lambda 表达式不需要fun 关键字，无须指定函数名。
     * 3、形参列表（如果有的话）在 -＞ 之前声明，参数类型可以省略。
     * 4、函数体（ Lambda 表达式执行体）放在-＞之后。
     * 5、函数的最后一个表达式自动被作为Lambda 表达式的返回值，无须使用return关键字。
     */
}

fun lambdaFun2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val lambdaList = arrayListOf<(Int) -> Int>()

    fun addItem(fn: (Int) -> Int) {
        lambdaList.add(fn)
    }

    addItem { x: Int -> x * x }
    addItem { x: Int -> x + x }

    var a = 10
    for (fn in lambdaList) {
        println(fn(a))
    }
}

fun lambdaFun3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val f1 = { x: Int -> x + 100 }
    println(f1(10))

    val result = { i: Int, arr: Array<Int> ->
        val arrResult = Array(arr.size) { 0 }
        for (index in arr.indices) {
            arrResult[index] = arr[index] * i
        }
        arrResult
    }(2, arrayOf(1, 2, 3, 4))
    //后面使用圆括号执行调用，并传入了对应的参数，这样result 变量得到的 不是Lambda 表达式，而是执行它的返回值。

    println(result.contentToString())

}


fun lambdaFun4() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    //Lambda 表达式被赋值给（Int)-> Int 类型的变量，
    // 这样Kotlin 可以很容易地推断出该Lambda 表达式的形参类型，因此Lambda 表达式可以省略形参类型
    val f1: (Int) -> Int = { x -> x + 100 }
    println(f1(10))

    val list = listOf<String>("aaa", "bb", "c")
    //predicate: (T) -> Boolean 类型可以推断，所以可以省略形参类型
    list.dropWhile({ x -> x.length > 1 })
}


fun lambdaFun5() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val f1: (Int) -> Int = { it + 100 }
    println(f1(10))

    val list = listOf<String>("aaa", "bb", "c")
    list.dropWhile({ it.length > 1 })
}


fun lambdaFun6() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    val arr1 = Array<Int>(5, { it * 2 })
    val arr2 = Array<Int>(5) { it * 2 }
    arr1.filter { it > 2 }

}

fun lambdaFun7() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    fun test(vararg names: String, age: Int = 12, fn: (String) -> String): String {
        var result = ""
        for (str in names) {
            result += str
        }
        return "name = ${fn(result)} ,age = $age"
    }

    val result = test("a", "b", "c"/*, age = 100*/) {
        "$it**"
    }
    println(result)

    /**
     * lambda建议放在最后一个参数位置，这样可以写在括号外部
     * 那么带默认值的参数 和 个数可变的参数都建议放在最后位置，那应该哪个放在最后位置呢？
     *  -如果带默认值的参数放在可变参数后，虽然带默认值的参数如果想要赋值必须用命名参数，但是不传则可以使用默认值
     *  -如果带默认值的参数放在可变参数之前，则无法使用默认值。
     *  所以：建议的位置是：可变参数--> 带默认值的参数-->lambda表达式
     */
}






