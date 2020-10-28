package part8

/**
 *
 * 八、面向对象(下)
 *
 * 8.2、final
 *
 */
fun main() {


    /**
     * final 关键字可用于修饰类、属性和方法，表示它修饰的类、属性和方法不可改变。
     * Kotlin 有一个非常特别的设计：它会为非抽象类自动添加final 修饰符，也会为非抽象方法、非抽象属性等无须重写的成员自动添加final 修饰符。
     * 如果开发者希望取消Kotlin 自动添加final 修饰符，则可使用open 修饰符， open 修饰符与final 修饰符是反义词。
     * 此外， Kotlin 与Java 的一个重大区别是： 【Kotlin 的final修饰符不能修饰局部变量，因此open 自然也不能修饰局部变量。】
     *
     *
     * 在java中，“宏变量”：在编译阶段就会被替换掉。
     *
     * 但Kotlin 不允许使用final修饰局部变量，也不允许直接在Kotlin 类中定义成员变量（ Kotlin定义的是属性〉，因此Kotlin 不可能用final 定义“宏变量”。
     * Kotlin 提供了const 用来修饰可执行“宏替换”的常量，这种常量也被称为“编译时”常量，因为它在编译阶段就会被替换掉。
     *
     * “宏替换”的常量除使用const 修饰之外，还必须满足如下条件。
     * 1、位于顶层或者是对象表达式的成员。
     * 2、初始值为基本类型值（Java 的8 种基本类型）或字符串字面值。
     * 3、没有自定义的getter方法。
     */
    final1()

    /**
     * 1、final --> 修饰方法
     * 使用final 修饰的方法不可被重写。
     * 与属性设计类似的是，如果程序不为方法添加任何修饰符， Kotlin 会自动为该方法添加final 修饰。
     */
    final2()
    final3()

    /**
     * 2、final --> 修饰类
     * 使用final 修饰的类不可以有子类，与方法、属性的设计相同：如果一个类没有显式使用open 修饰符修饰，那么Kotlin 会自动为该类添加final 修饰。
     * 当子类继承父类时，将可以访问到父类内部数据，井可通过重写父类方法来改变其实现细节，这可能会导致一些不安全的因素。
     * Kotlin 出于更严格的设计哲学，为所有类自动添加final修饰，如果希望某个类可以派生子类，则需要为该类添加open 修饰。
     */


}


const val MAX_AGE = 100


// 此外，如果被赋值的表达式只是基本的算术表达式或进行字符串连接运算
// 没有访问普通 变量、常量，调用方法，那么KotLin 编译器同样会将这种const 常量当成“宏变量”处理。
const val a = 5 + 2
const val b = "abc" + "def"
const val c = 1.2 / 100
const val d = 12

// 常量的初始值时使用了另一个常量，因此编译器无法在编译时确定e的值， e不会被当成“宏变量”处理。
const val e = "hello world " + d
const val f = "hello world " + 12

fun final1() {
    // const val MAX_AGE = 100 这是错误的，必须位于顶层或者对象表达式成员

    // 常量MAX AGE 其实根本不存在
    // 当程序执行println(MAX AGE）代码时，实际替换为执行println(100)一一而且这个替换在编译阶段就完成 了，因此程序在运行阶段完全没有MAX_AGE 常量。
    println(MAX_AGE)
}


fun final2() {

    open class Fu {
        open var name = "rrc"
    }

    class Zi : Fu() {
        override var name: String = "add"
    }

}


fun final3() {
    // 对于一个private方法、属性，因为它仅在当前类中可见，其子类无法访问它，所以子类无法重写该方法、属性
    // 一一如果在子类中定义一个与父类的private 方法有相同方法名、相同形参列表、相同返回值类型的方法，则不是方法重写，只是重新定义了一个新方法。
    // 因此，即使使用final修饰一个private 访问权限的方法，也依然可以在其子类中定义与该方法具有相同方法名、相同形参列表、相同返回值类型的方法。

    // 注意：使用final 修饰的方法仅仅是不能被重写，并不是不能被重载

    open class Fu {
        /*final*/ private var name = "rrc"
    }

    class Zi : Fu() {
        private var name = "add"
    }
}

