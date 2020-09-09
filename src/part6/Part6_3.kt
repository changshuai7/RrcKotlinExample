package part6

/**
 * 匿名函数
 */
fun main() {
    /**
     * 一、介绍
     * Lambda 表达式虽然简洁、方便，但它有一个严重的缺陷： Lambda 表达式不能指定返回值类型。
     * 大部分时候，由于Kotlin 可以推断出Lambda 表达式的返回值类型,因此即使不为Lambda表达式指定返回值类型也没有问题。
     * 但在一些特殊的场景下，如果Kotlin 无法推断出Lambda表达式的返回值类型，此时就需要显式指定返回值类型，而匿名函数即可代替Lambda 表达式。
     */
    myFun1()

    /**
     * 二、匿名函数特点
     * 1、匿名函数与普通函数基本相似，只要将普通函数的函数名去掉就变成了匿名函数。
     * 2、如果系统可以推断出匿名函数的形参类型，那么匿名函数允许省略形参类型。
     */
    myFun2()

    /**
     * 三、匿名函数和Lambda 表达式的return
     *
     * 匿名函数的return 则用于返回该函数本身
     * 而Lambda表达式的return 用于返回它所在的函数，而不是返回Lambda 表达式（因此一般不会在Lambda 表达式中使用return 。）
     */
    myFun3()

    /**
     * 四、
     */
    myFun4()
}


fun myFun1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    //普通函数
    fun f1(age: Int, name: String): String {
        return "name=$name,age=$age"
    }

    //匿名函数
    var f2 = fun(age: Int, name: String): String {
        return "name=$name,age=$age"
    }

}

fun myFun2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    // 上面代码定义了一个匿名函数。从上面代码可以看出
    // 匿名函数与普通函数基本相似，只要将普通函数的函数名去掉就变成了匿名函数。

    //与普通函数不同的是，如果系统可以推断出匿名函数的形参类型，那么匿名函数允许省略形参类型。例如如下代码
    var f3: (Int, String) -> String = fun(age, name): String {
        return "name=$name,age=$age"
    }


    val arr = arrayOf("a", "bb", "ccc")
    arr.filter(fun(e): Boolean {//代码块作为函数体
        return e.length > 1
    })
    arr.filter(fun(e) = e.length > 1)//单表达式作为函数体
    // filter（）方法时需要传入一个（lnt)->Boolean 类型的参数
    // 此时既可传 入一个Lambda 表达式，也可传入一个匿名函数，此处我们特意传入一个匿名函数作为参数。
    // 由于系统完全可以推断出该参数的类型必须是（ Int)->Boolean ，因此此处允许省略匿名函数的形参类型。
}


fun myFun3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    re1()
    println()
    re2()
}

fun re1() {
    val list = listOf<String>("a", "b", "c")
    list.forEach(fun(item) {
        print(item)
        return
    })
}

fun re2() {
    val list = listOf<String>("a", "b", "c")

    list.forEach {
        print(it)
        return
        //return@forEach //如果使用限定返回，此时return 只是返回传给forEach 方法的Lambda 表达式
    }

    //上面代码之所以可以在Lambda 表达式中直接使用return，
    // 是因为该forEach 方法使用了inline 修饰（内联函数，以后介绍）
}


fun myFun4() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
}

