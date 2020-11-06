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
     * 首先：Kotlin中引入了lambda表达式的概念
     * lambda表达式的内存本质就是：创建一个具有一个抽象方法的接口的实例（也就是函数式接口的实例），意味着一个lambda表达式，就是一个对象。
     * 对象是引用型类型，可以作为参数传递，所以，在kotlin中，函数（包括但是不限于lambda）也就可以作为参数进行传递了。
     *
     * 所以：【如果对象是函数式接口，那么必然可以用lambda表达式来代替，无论参数是function，还是对象，因为他们本质是一样的】
     *
     *
     */


    /**
     * 引入：匿名内部类的替代
     * 如果对象是函数式接口（只包含一个抽象方法的接口）的实例（注意：1.4.10的kotlin版本以前，必须是用Java来声明的，1.4.10以后支持了Kotlin函数式接口SAM转换），
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

