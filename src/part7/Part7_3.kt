package part7

import java.util.Date as Data1
import java.sql.Date as Data2

/**
 * 七、面向对象(3)
 *
 * 封装实际上有两个方面的含义：把该隐藏的隐藏起来，把该暴露的暴露出来。
 * 封装需要通过使用包和访问权限修饰符（也称为可见性修饰符， Visibility Modifier ）来实现。
 *
 */
fun main() {

    /**
     * Kotlin中的导包：
     * Kotlin中的导包分为两种：
     * 精确导入：import foo.Bar //导入foo 包中的Bar ，以后使用Bar 时无需包名
     * 通配符导入：import foo.* //导入foo 包中的所有成员，以后使用foo 包中的所有成员时都无需包名
     *
     * 1、
     * Kotlin的import 功能比较强大， 它相当于Java 的import 和import static （静态导入〉的合体，它不仅可以导入类，还可以导入如下内容。
     * 》顶层函数及属性。
     * 》在对象声明中声明的函数和属性。
     * 》枚举常量。
     *
     * 2、
     * Kotlin中的默认导入
     * 正如前面所看到的，前面程序使用Kotlin 定义的List 、Map 等类时， 程序都没有为这些类导包， 其原因都是因为Kotlin 的默认导入。
     *
     * 3、
     * Kotlin中的导入别名
     * 如果需要在同一个源文件中导入不同包中的同名类（比如java.util.Date 和java.sql.Date ）
     * 以前Java 程序很难处理这种情况，通常有一个类总要使用全限定类名（放弃导包），但Kotlin 提供了更简单的处理方式。
     */
    encapsulation1()


    /**
     * Kotlin 提供了4 个访问控制符： private 、internal 、protected 和public ， 分别代表4 个访问控制级别。
     * 一、Kotlin 的4 个访问控制符的意义如下。
     *
     * 》private ： 与Java 的private 类似， private 成员只能在该类的内部或文件的内部被访问。
     * 》internal: internal 成员可以在该类的内部或文件的内部或者同一个模块内被访问。
     * 》protected: protected 成员可以在该类的内部或文件的内部或者其子类中被访问。
     * 》public: public 成员可以在任意地方被访问。
     *
     * 如果Kotlin 没有显式指定修饰符的话， 默认的访问控制修饰符是public 。
     *
     * 二、Kotlin 的访问控制符与Java 的区别有如下几点。
     * 》Kotlin 取消了Java 的默认访问权限（包访问权限），引入了internal 访问控制符（模块访问权限）。
     * 》Kotlin 取消了protected 的包访问权限。
     * 》Kotlin 的默认访问控制符是public 。
     *
     * 三、什么是模块？
     * 有如下几种。
     * 》一个IntelliJ IDEA 模块。
     * 》一个Maven 项目。
     * 》一个Gradle 源集。
     * 》一次＜kotlinc＞的Ant 任务执行所编译的一套文件。
     *
     */

    /**
     * 下面详述Kotlin 不同作用域中的成员可支持的访问控制符
     *
     * 一. 位于包内的顶层成员
     * 对于位于包内的顶层成员（包括顶层类、接口、函数、属性），只能使用private 、internal和public 其中之一，不能使用protected 修饰符。
     * 》使用private 修饰，这些顶层成员只能在当前文件中被访问。
     * 》使用internal 修饰，这些顶层成员只能在当前文件或当前模块中被访问。
     * 》不加修饰符或使用public 修饰，这些顶层成员可以在任意地方被访问。
     *
     * 理解：包内的顶层成员，一个kt文件最终会生成一个NameKt.class的类。protected是没有意义的，因为NameKt.class没有子类
     *
     * 直接定义在包内的顶层函数、属性，会转换成Kotlin 所生成的类（类名为文件名＋Kt 后级的类）中的静态方法和静态属性。
     *
     */
    encapsulation2()

    /**
     *
     * 二、 位于类、接口之内的成员
     * 对于位于类、接口之内的成员（包括顶层类、接口、函数、属性），能使用private 、internal 、protected 和public 其中之一。
     * 》使用private 修饰，这些成员只能在该类中被访问。
     * 》使用internal 修饰，这些成员能在该类或当前模块中被访问。
     * 》使用protected 修饰，这些成员能在该类或该类的子类中被访问。
     * 》不加修饰符或使用public 修饰，这些成员可以在任意地方被访问。
     *
     * 如果重写一个protected 成员，且没有显式指定访问权限修饰符，那么该成员依然是protected 访问权限。
     *
     */
    encapsulation3()


    /**
     * 类的主构造器有点特别，它是在类头部分声明的。
     * 如果需要为主构造器指定访问权限修饰符，则一定要使用constructor 关键字，并在该关键宇前面添加private 、internal、protected 和public 其中之一。
     * 如果不为主构造器指定访问权限修饰符，那么主构造器默认的访问权限修饰符是public 。
     * 与Java 相同的是，局部声明（包括局部变量、局部方法、局部嵌套类）的作用域仅在该方法（或函数）内有效，
     * 使用访问控制符没有任何意义，因此不能使用private 、internal 、protected 、public 修饰符。
     */
    encapsulation4()

}


fun encapsulation1() {

    var d1 = Data1()  //java.util.Date
    var d2 = Data2(100L) //java.sql.Date

}

fun encapsulation2() {

//      文件名： example.kt
//
//      package lee
//
//      private fun foo() ｛｝        // 该函数仅在example.kt 内可访问
//      internal fun test() ｛｝      // 该函数可在相同模块内被访问
//      fun info() ｛｝               // 该函数可在任意地方被访问
//
//
//      public var bar: Int= 5      // 该属性可在任意地方被访问
//          private set             // setter 仅在example.kt 内可访问
//      internal val baz = 6        // 该属性可在相同模块内被访问
//          private set             // setter 仅在example.kt 内可访问

//需要说明的是，属性的getter方法的访问权限总是与该属性保持一致。

}

fun encapsulation3() {

    //看如下的Outer、SubClass、Other类
}

open class Outer {
    private var a = 1
    protected open var b = 2
    internal var c = 3
    var d = 4

    protected class Nested {
        public val e = 5
    }
}

class SubClass : Outer() {
    // a不可以访问
    // b、c、d 可以访问
    // Nested和e可以访问

    override var b = 44 //被重写依然是protected访问权限
}

class Other(o: Outer) {
    //o.a 、o.b 不可以访问
    //o.c、o.d 可以访问
    //Outer.Nested不可以访问，Nested::e也不可以访问
}

fun encapsulation4() {

    open class A private constructor(name: String) {

    }

    //var a = A("RRC")//报错
}



