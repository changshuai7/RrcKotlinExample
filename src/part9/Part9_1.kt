package part9

import java.lang.Exception
import java.lang.RuntimeException


/**
九、异常处理

与Java 的异常处理机制相比， Kotlin抛弃了checked 异常，相当于所有异常都是runtime异常，
这意味着开发者想捕获异常就捕获，不想捕获异常也行，不需要使用throws 关键字声明抛出异常。

Throwable
|-Exception

 */
fun main() {

    /**
     * 异常，和Java保持一致
     */
    exception1()


    /**

    如果程序需要在catch 块中访问异常对象的相关信息，则可以通过访问catch 块后的异常形参来获得。
    当运行时环境决定调用某个catch 块来处理该异常对象时，会将异常对象赋给catch 块后的异常参数，程序即可通过该参数来获得异常的相关信息。
    所有的异常对象都包含了如下几个常用属性和方法。
    1、message ： 该属性返回该异常的详细描述字符串。
    2、stackTrace ： 该属性返回该异常的跟踪枝信息。
    3、printStackTrace （）： 将该异常的跟踪栈信息输出到标准错误输出。
    4、printStackTrace(PrintStream s ）： 将该异常的跟踪枝信息输出到指定输出流。

     */
    exception2()


    /**
    try语句是表达式
    与if 语句类似，Kotlin的try语句也是表达式，因此位try语句也可用于对变量赋值。
    try表达式的返回值是try块中的最后一个表达式的值，或者是被执行的catch 块中的最后一个表达式的值， finally 块中的内容不会影响表达式的结果。
    (因为finally内容相当于插入到try、catch结束代码前执行所以，try、catch的最后一行代码依然没变)
     */
    exception3()


    /**
    异常的抛出：
    如果需要在程序中自行抛出异常，则应使用throw 语句
    由于Kotlin 没有checked 异常（即使某个异常在Java 中原本是checked 异常，在Kotlin中它也不是checked 异常）
    因此Kotlin 抛出异常的语句无须放在try 块中，程序既可以显式使用try... catch 来捕获井处理该异常，也可以完全不理会该异常，把该异常交给该方法调用者处理
     */
    exception4(100)


    /**
    自定义异常
    用户自定义异常都应该继承Exception 基类，定义异常类时通常需要提供两个构造器：
    一个是无参数的构造器；另一个是带一个字符串参数的构造器，这个字符串将作为该异常对象的描述信息（也就是异常对象的message 属性的返回值） 。

     */
    exception5()


    /**
    异常的链式处理
    捕获一个异常，然后抛出另一个异常，并把原始异常信息保存下来，这是一种典型的链式处理（ 23 种设计模式之一：职责链模式），也被称为“异常链”。
    Kotlin 的Throwable 类及其子类在构造器中都可以接收一个cause 对象作为参数。这个cause 就用来表示原始异常
    这样可以把原始异常传递给新的异常，使得即使在当前位置创建并抛出了新的异常，我们也能通过这个异常链追踪到异常最初发生的位置。
     */
    //exception6()


    /**
    throw语句是表达式

    与try语吾句是表达式一样， Kotlin 的throw 语句也是表达式，但由于throw 表达式的类型比较特殊，是Nothing 类型
    因此很少将throw 语句赋值给其他变量，但我们可以在Elvis 表达式中使用throw 表达式。
     */

    println(exception7("rrc"))

    /**
    Nothing 类型没有值，而是用于标记永远无法“真正”返回的表达式
    因此程序不会获取表达式的值。当我们自己定义函数时，也可使用Nothing 来标记一个永远不会返回的函数
     */
    //exception8()


}


fun exception1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    try {
        val a = 10 / 0
    } catch (ex: Exception) {
        //ex.printStackTrace() //红字打印
    } finally {
        println("finally")

    }
}


fun exception2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    try {
        val a = 10 / 0
    } catch (ex: Exception) {

        println("message= ${ex.message}")
        println("stackTrace = ${ex.stackTrace}")

        println("\nprintStackTrace:\n")
        //ex.printStackTrace()

    }
}

fun exception3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")


    var result: Int = try {
        println("try")
        10 / 0
    } catch (e: Exception) {
        println("catch")
        -1
    } finally {
        println("finally")
        100
    }

    println(result)
}

fun exception4(a: Int) {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    when {
        a < 0 -> throw Exception("a的取值小于0")
        a in 0..10 -> throw RuntimeException("a的取值在0-10范围内")
        else -> println("满足条件")
    }
}

fun exception5() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    //抛出一个异常
    //throw MyException()
}

class MyException : Exception {

    constructor() : super()
    constructor(message: String) : super(message)

}

fun exception6() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")


    //Exception in thread "main" part9.MyException2: java.lang.ArithmeticException: / by zero
    //	at part9.Part9_1Kt.exception6(Part9_1.kt:167)
    //	at part9.Part9_1Kt.main(Part9_1.kt:72)
    //	at part9.Part9_1Kt.main(Part9_1.kt)
    //Caused by: java.lang.ArithmeticException: / by zero
    //	at part9.Part9_1Kt.exception6(Part9_1.kt:164)
    //	... 2 more

    try {
        println("try执行")
        val a = 10 / 0
    } catch (e: Exception) {
        println("catch执行")
        //这里可以让MyException2 跟踪到最原始的异常信息
        throw MyException2(e)
    }
}

class MyException2 : Exception {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(e: Throwable) : super(e)

}

fun exception7(name: String?): Int {

//    上面程序中粗体字代码将name 赋值给th 变量，由于 name 是可空类型，因此程序对name 使用了Elvis 表达式进行判断：
//    当name 不为null 时， 将其值赋给 name; 否则， 使用throw 表达式的值，
//    throw 表达式的类型是Nothing,用于表示程序无法“真正”得到该表达式的值。
//    因此，如果name 为null ， 程序将会出现异常，不会对th 变量赋值， 故可将th 变量声明为String 类型。
//    编译、运行该程序， 可看到程序因为NullPointerException 异常而结束。

    var th = name?.length ?: throw Exception("name不能为空")
    println(th)
    return th

}

fun exception8(): Nothing? {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    throw Exception("错误")
}

/**
 *
 * Unit、null、Nothing、Nothing? 有什么区别
 *
 * 1、null：同java的null，表示空值
 * 2、Unit：同java的void，表示当一个函数没有返回值的时候，我们用Unit来表示这个特征，而不是null，大多数时候我们不需要显示地返回Unit，或者生命一个函数的返回值是Unit，编译器会推断它。
 * 3、Nothing：同java的Void（void的包装），表示永远不会有返回值。比如抛出异常，则永远不会有返回值。
 * 4、Nothing？:可以为Nothing或者null
 */



