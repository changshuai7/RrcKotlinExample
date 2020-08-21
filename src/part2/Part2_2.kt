package part2

import java.lang.Exception

fun main() {
    /**
     * null 安全可以说是Kotlin语言对Java 的重大改进之一，这样可以避免Java 编程时令人恐惧的NullPointerException （简称NPE ）。
     * 但话说回来， null 安全不过是各种现代编程语言玩剩下的东西。
     *
     * 只有可空类型的变量或常量才能接受null ， 非空类型的变量或常量不能接受null 。
     * Kotlin 对可空类型进行了限制：如果不加任何处理，可空类型不允许直接调用方法、访问属性。
     * 因此，通过可空类型与非空类型的区分， Kotlin 即可在程序中避免空指针异常。
     *
     */
    notnull()

    /**
     * 先判断后使用
     * 可空类型的变量不允许直接调用方法或属性，但可以先判断该变量不为null ，然后再调用该变量的方法或属性。
     */
    notnull2()

    /**
     * 特别需要注意的是：
     * 对于非空的Boolean 类型而言，它可以接受3 个值，即true 、false 或null ，
     * 因此对Boolean?类型变量进行判断时，需要使用Boolean？变量显式与true 、false 值进行比较
     */
    notnull3()

    /**
     * 安全调用
     */
    safeCall()

    /**
     * Elvis
     * Elvis运算也是一种小技巧，其实就是if else 的简化写法
     */
    elvisCall()

    /**
     * Elvis
     * 此外，由于Kotlin的return 、throw 都属于表达式，因此它们也都可以用在“？：”运算符的右边。例如如下代码片段：
     */
    elvisCall2()

    /**
     * 强制调用
     * 强制调用是为NPE “ 爱好者”准备的一一如果读者依然喜欢Java 那种简单、粗暴的方式：
     * 不管变量是否为null ，程序都直接调用该变量的方法或属性。Kotlin 也为这种用法提供了支持，
     * 用“!!. ”即可强制调用可空变量的方法或属性， 这样强制调用可能引发NPE 。
     * 但是我们强烈不建议这样使用，而是通过源头控制 NPE
     */
    forceCall()

}


fun notnull() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")


    //其中Int? 就是可空类型，这种类型的变量可接受Int 值和null ；而Int 类型的变量则只接受Int 值，不能接受null

    //由于str 转换为Int 有可能失败，故num 有可能没有值
    //因此不能使用Int 来声明num 的类型
    var str = "fkt"

    //无法通过编译
    //var num1: Int = str.toIntOrNull()
    var num2: Int? = str.toIntOrNull()
    println(num2)

    //编译器会推断num3 的类型为Int?
    var num3 = str.toIntOrNull();
    // 可空类型无法调用javaClass，下面会报错
    //println("num3的类型为${num3.javaClass}")

    var aStr: String = "fkt"
    var bStr: String? = "fkt"
    // aStr = null //错误， aStr 不接受null
    bStr = null //正确
    //编译通过， aStr 不可能为null ，运行时不可能导致NPE
    println(aStr.length)
    //编译不能通过，不可能导致NPE
    //println(bStr.length)

}

fun notnull2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    var b: String? = "abc"
    //先判断b 不为null ，然后访问b 的length 属性
    var c = if (b != null) b.length else -1
    //先判断b 不为null ，然后访问b 的length 属性
    if (b != null) {
        var d = b.length
    }

}

fun notnull3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    var b: Boolean? = true
    if (b == true) {
        println("ok")
    }
    //编译错误
    //因为if 条件必须是Boolean 类型，而Boolean ？与Boolean 本质上是两种不同的类型，因 此编译器会报错。
//    if (b) {
//        println("ok")
//    }
}


fun safeCall() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    var b: String? = "fkt"
    println(b?.length) //输出4
    b = null
    println(b?.length) //输出null

    // 上面程序中变量b 的类型是String？，因此程序使用了“？．”安全调用来访问b 的length 属 性，
    // 当b 为null 时，程序也不会引发NPE ，而是返回null

    // Kotlin 的安全调用也完全支持链式调用
    // 比如：user?.dog?.name
    val c = b?.get(0)?.hashCode()
    println(c)

    println()

    //此外，安全调用还可与let 全局函数结合使用
    val arr = arrayOf("rrc1", "rrc2", "rrc3", null)
    for (item in arr) {
        item?.let { println(it) }
    }

//  let扩展函数的实际上是一个作用域函数，当你需要去定义一个变量在一个特定的作用域范围内，let函数的是一个不错的选择
//  let函数另一个作用就是可以避免写一些判断null的操作。

//    object.let{
//        it.dox()//在函数体内使用it替代object对象去访问其公有的属性和方法
//        ...
//    }
//
//    //另一种用途 判断object为null的操作
//    object?.let{//表示object不为null的条件下，才会去执行let函数体
//        it.dox()
//    }

//    let函数适用的场景
//
//    场景一: 最常用的场景就是使用let函数处理需要针对一个可null的对象统一做判空处理。
//    场景二: 然后就是需要去明确一个变量所处特定的作用域范围内可以使用

    //没有let
//    mVideoPlayer?.setVideoView(activity.course_video_view)
//    mVideoPlayer?.setControllerView(activity.course_video_controller_view)
//    mVideoPlayer?.setCurtainView(activity.course_video_curtain_view)

    // 有let
//    mVideoPlayer?.let {
//        it.setVideoView(activity.course_video_view)
//        it.setControllerView(activity.course_video_controller_view)
//        it.setCurtainView(activity.course_video_curtain_view)


}


fun elvisCall() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    var a: String? = "rrc"
    var b = if (a != null) a.length else -1

    a = null
    val c = a?.length ?: -1
    println("b=${b},c=${c}")


    // 上面程序中第一行粗体字代码使用传统的if 分支进行判断，当a不为null 时返回a.length , 否则返回-1
    // 第二行粗体字代码则使用“？：”运算符，该运算符就是Elvis
    // 它的含义是，如 果“？：”左边的表达式不为null ，则返回左边表达式的值，否则返回“？：”右边表达式的值。
    // 由此可见，“？ ： ”其实就是if 分支的简化写法。

}


fun elvisCall2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    val data = mapOf<String, String>("name" to "rrc", "email" to "rrc2")
    val email = data["email"] ?: throw Exception("发生异常")
    println(email)

}

fun forceCall() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    var b: String? = "abc";
    b = null
    //强制调用，引发空指针异常
    //println(b!!.length)

    val arr = arrayOf("rrc1", "rrc2", "rrc3", null)
    for (item in arr) {
        // 此时item是一个String?类型，编译器自动推断出来的
        // 如果强制调用，会引发NPE
//        item!!.let {
//            println(it)
//        }

    }

}







