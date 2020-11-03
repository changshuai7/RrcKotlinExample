package part8

/**
 *
 * 八、面向对象(下)
 *
 * 8.7、对象表达式
 * Kotlin 提供了比匿名内部类更加强大的语法：对象表达式。
 * 它们的主要区别在于：匿名内部类只能指定一个父类型（接口或父类），但对象表达式可指定0～N 个父类型（接口或父类）。
 *
 */
fun main() {


    /**
    对象表达式的语法：

    ---------------------

    object[: 0 ～N 个父类型］ {

    //对象表达式的类体部分

    }

    ---------------------


    对象表达式的本质就是增强版的匿名内部类，因此编译器处理对象表达式时也会像匿名内部类一样生成对应的class 文件。

    关于对象表达式还有如下规则。
    1、对象表达式不能是抽象类，因为系统在创建对象表达式时会立即创建对象。因此不允许将对象表达式定义成抽象类。
    2、对象表达式不能定义构造器。但对象表达式可以定义初始化块，可以通过初始化块来完成构造器需要完成的事情。
    3、对象表达式可以包含内部类（有inner 修饰的内部类），不能包含嵌套类。

    掌握对象表达式非常简单，只要牢一点：Kotlin 的对象表达式就是匿名内部类的增强版，它支持指定 0－ N 个父类型．

     */

    objExpression1()


    /**

    但Katlin 的对象表达式不同， Katlin 的对象表达式可分为两种情形。
    1、①对象表达式在方法（或函数）的局部范围内，②或使用private 修饰的对象表达式：Kotlin编译器可识别该对象表达式的真实类型，
    就像上面的代码所示：程序为ob2 增加了方法和属性，在函数的局部范围内， Katlin 编译器完全可以识别ob2 的真实类型，因此ob2 可调用对象表达式增加的属性和方法。
    2、非private 修饰的对象表达式与Java 的匿名内部类相似，
    编译器只会把对象表达式当成它所继承的父类或所实现的接口处理。如果它没有父类型，系统当它是Any 类型。

     */
    objExpression2()


    /**
     * Kotlin 的对象表达式可访问或修改其作用域内的局部变量
     */
    objExpression3()

    /**
    【总结起来】： Kotlin 的对象表达式比Java 的匿名内部类增强了三个方面。
    1、对象表达式可指定多个父类型。
    2、Kotlin 编译器能更准确地识别局部范围内 private 对象表达式的类型。
    3、对象表达式可访问或修改其所在范围内的局部变量。
     */



}


fun objExpression1() {

    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    //指定父类型（接口）的对象表达式
    val obj1 = object : FuIf, FuAbs("人人车") {

        //重写父类或者接口中的抽象成成员/方法
        override val ifName: String = "ifNameValue"

        override fun ifInfo(str: String) {
            println("ifInfo-$str")
        }

        override val absName: String
            get() = "absNameValue"

        override fun absInfo(str: String) {
            println("absInfo-$str")
        }

    }

    // ob2 对象没有继承任何父类型， 程序为该对象（相当于匿名内部类）定义了name 属性和test 方法
    // 接下来程序居然可以直接调用该对象的name 属性和test方法一一这对Java 的匿名内部类来说是不可想象的：
    // Java 为 匿名内部类增加的方法几乎无法直接访问，因为编译器只会把匿名内部类当成它所继承的父类 或所实现的接口处理。
    val obj2 = object {
        //初始化块
        init {
            println("构造代码块")
        }

        //属性
        val name: String
            get() = "HelloWorld"

        //方法
        fun test() {
            println("test")
        }

        //只能包含内部类，不能包含嵌套类
        inner class Inn
    }


    obj1.ifInfo("hello")
    obj1.absInfo("hello")
    println("${obj1.ifName} - ${obj1.absName}")

    println("${obj2.name}-")


}

interface FuIf {
    val ifName: String
    fun ifInfo(str: String)
}

abstract class FuAbs(val name: String) {
    abstract val absName: String
    abstract fun absInfo(str: String)
}

fun objExpression2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    val obj = ObjectExprType()
    obj.test()

    println(aa.name)

    //bb非private修饰，编译器无法识别其类型，bb当成Any来处理
    //println(bb.name) //报错，不可访问

    //println(ff1().name)//报错，不可访问

    println(ff2().name)

}

class ObjectExprType {
    private val a = object {
        val name = "a-name"
    }
    internal val b = object {
        val name = "b-name"
    }

    private fun f1() = object {
        val name = "f1-name"
    }

    fun f2() = object {
        val name = "f2-name"
    }

    fun foo() {
        val d = object {
            val name = "d-name"
        }
        println(d.name)
    }


    fun test() {
        // a是private修饰的表达式，编译器可以识别其类型
        println(a.name)

        // b是非private修饰的表达式，编译器无法识别其类型，且b没有父类，则只能当成Any来处理
        //println(b.name)//报错：不可访问

        // f1()是private修饰的方法，编译器可以识别其类型
        println(f1().name)

        // f2()是非private修饰的方法，编译器无法识别其类型，f2()接收的对象表达式没有父类，则只能当成Any来处理
        //println(f2().name) //报错，不可访问

        //d是局部变量，编译器可以识别其类型，可以访问
        foo()

    }
}

private val aa = object {
    val name = "aa-name"
}
val bb = object {
    val name = "bb-name"
}

fun ff1() = object {
    val name = "ff1-name"
}

private fun ff2() = object {
    val name = "cc-name"
}


fun objExpression3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    var name = "abc"
    val obj = object {
        fun changeName(newName: String) {
            name = newName
        }
    }
    obj.changeName("rrc")
    println(name)
}


