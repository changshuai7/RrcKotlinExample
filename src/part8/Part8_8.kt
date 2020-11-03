package part8

/**
 *
 * 八、面向对象(下)
 *
 * 8.7、对象申明和单例
 *
 */
fun main() {

    /**
    对象声明的语法格式如下：
    object ObjectName[: 0 - N 个父类型］｛
    //对象表达式的类体部分
    }

    对象声明与对象表达式的语法非常相似，似乎它们之间唯一的区别是： 对象表达式在object关键字后没有名字； 而对象声明需要在object关键字后指定名字

    实际上，对象声明和对象表达式还存在如下区别。
    1、对象表达式是一个表达式，因此它可以被赋值给变量； 而对象声明不是表达式，因此它不能用于赋值。
    2、对象声明可包含嵌套类，不能包含内部类； 而对象表达式可包含内部类，不能包含嵌套类。
    3、对象声明不能定义在函数和方法内； 但对象表达式可嵌套在其他对象声明或非内部类中。

    和对象表达式相比，现对象声明不能用于赋值，对象声明本身己有名称
    因此可以使用对象声明的名称访问该对象。此外，程序还将对象声明移到main函数之外，这是因为对象声明不允许放在函数或方法内。

    【对象声明专门用于实现单例模式，对象声明所定义的对象也就是该类的唯一实例，程序可通过对象声明的名称直接访问该类的唯一实例】

     */
    objectStatement1()


    /**
     * 伴生对象和静态成员
    在类中定义的对象声明，可使用 companion 修饰，这样该对象就变成了伴生对象。
    每个类最多只能定义一个伴生对象，伴生对象相当于外部类的对象，程序可通过外部类直接调用伴生对象的成员。
     */
    objectStatement2()


    /**
    伴生对象的扩展
    伴生对象也可以被扩展。如果一个类具有伴生对象，则Kotlin允许为伴生对象扩展方法
    和属性。为伴生对象扩展的方法和属性，就相当于为伴生对象所在的外部类扩展了静态成员，
    可通过外部类的类名访问这些扩展成员。
     */
    objectStatement3()

}


fun objectStatement1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    OBJ1.info("haha")
    OBJ2.test()
}

interface ObjI {
    val name: String
    fun info(str: String)
}

abstract class AbsCls1(val s: String) {
    abstract val age: Int
    abstract fun method()
}

object OBJ1 : ObjI, AbsCls1("rrc") {
    override val name: String = "Hello"

    override fun info(str: String) {
        println(str)
    }

    override val age: Int
        get() = 10086

    override fun method() {
        println("method")
    }

    //inner class D  //不可以有内部类
    class E   //只可以有嵌套类

}

object OBJ2 {
    init {
        println("初始化块")
    }

    val name: String = "rrc"
    fun test() {
        println("test")
    }
}

fun objectStatement2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    /**
    不难发现： 这不就是Java 使用类访问静态成员的语法吗？实际上确实如此
    由于Kotlin 取消了static 关键字，因此Kotlin引入伴生对象来弥补没有静态成员的不足。
    可见，伴生对象的主要作用就是为其所在的外部类模拟静态成员。
     */

    MyCls.info("hello") //通过类名直接访问


    /**
    伴生对象的名称并不重要，因此伴生对象可以
    省略名称。省略名称之后，如果程序真的要访问伴生对象，则可通过Companion 名称进行访问。例如如下代码。
     */
    MyCls.Companion.info("hello");

    println(MyCls.Companion)

    /**
    虽然伴生对象的主要作用就是为它所在的类模拟静态成员，但只是模拟
    伴生对象的成员依然是伴生对象本身的实例成员，并不属于伴生对象所在的外部类。
     */
}

class MyCls {
    companion object /*MyOBJ*/ : ObjI, AbsCls1("rrc") {
        override val name: String
            get() = "rrc1"

        override fun info(str: String) {
            println(str)
        }

        override val age: Int = 12

        override fun method() {
            println("method")
        }
    }

}

fun MyCls.Companion.exFoo(ex: String) {
    println(ex)
}

val MyCls.Companion.exCity
    get() = "北京"


fun objectStatement3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    MyCls.exFoo("人人车")
    println(MyCls.exCity)
}




