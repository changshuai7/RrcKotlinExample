package part8

/**
 *
 * 八、面向对象(下)
 *
 * 8.6、对象表达式和对象申明
 * Java 有一个非常实用的功能：匿名内部类， Kotlin 则彻底抛弃了这个功能。因为 Kotlin 提供了一个更加强大的语法： 对象表达式。
 *
 */
fun main() {


    /**
     * 引入：匿名内部类的替代
     * 如果对象是函数式接口（只包含一个抽象方法的接口）的实例，
     * 可使用带接口类型前缀的Lambda 表达式创建它。例如，如下代码创建了一个Runnable 实例来启动线程。
     */
    obj1()

    obj2()

}


fun obj1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val r = Runnable {
        println("runnable执行")
    }
    Thread(r).start()

    //或者更简单的写法
    Thread {
        println("runnable执行")
    }.start()

}

fun obj2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

}

