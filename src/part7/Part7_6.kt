package part7

/**
 * 七、面向对象(6)
 * 多态
 *
 * 与Java 类似， Kotlin的变量也有两个类型： 一个是编译时类型， 一个是运行时类型。
 * 编译时类型由声明该变量时使用的类型决定，运行时类型由实际赋给该变量的对象决定。
 * 如果编译时类型和运行时类型不一致，就可能出现所谓的多态（ Polymorphism ）。
 */
fun main() {

    /**
     * 多态使用，同Java
     * 与Java 类似， Kotlin的变量也有两个类型： 一个是编译时类型， 一个是运行时类型。
     * 编译时类型由声明该变量时使用的类型决定，运行时类型由实际赋给该变量的对象决定。
     * 如果编译时类型和运行时类型不一致，就可能出现所谓的多态（ Polymorphism ）。
     */
    poly1()

    /**
     * 使用is 检查类型
     * 变量只能调用其编译时类型的方法，而不能调用其运行时类型的方法，即使它实际所引用的对象确实包含该方法。
     * 如果要让这个变量调用其运行时类型的方法，就需要把它强制转换成运行时类型
     * 强制转换需要借助于强制转型运算符。Kotlin 的类型转换运算符包含as 和as？两个。
     *
     */

    /**
     * 为了保证类型转换不会出错， Kotlin 提供了类型检查运算符： is 和 !is
     * is 运算符的前一个操作数通常是一个变量，后一个操作数通常是一个类（ 也可以是接口可以把接口理解成一种特殊的类〉
     * 它用于判断前面的变量是否引用后面的类，或者其子类、实现类的实例。如果是，则返回true ，否则返回false 。
     *
     * 1、在使用is 运算符时需要注意， is主算符前面操作数的编译时类型要么与后面的类【相同】，要么与后面的类具有【父子继承关系】，否则编译时程序报错。
     *
     * 2、此外， Kotlin 的is 和!is 都非常智能，只要程序使用is 或!is 对变量进行了判断，系统就会自动将变量的类型转换为目标类型
     *
     * 3、当程序使用is 或!is 判断之后，只要Kotlin 能推断出变量属于某种类型， Kotlin 就会自动将该变量的编译时类型转换为指定类型。比如使用＆＆运算符也可以。
     *
     */
    poly2()


    /**
     * 使用as运算符
     * 除使用is 自动转型之外， Kotlin 也支持使用as 运算符进行强制转型。Kotlin 提供了两个向下转型运算符。
     * 》as ： 不安全的强制转型运算符，如果转型失败，程序将会引发ClassCastException 异常。
     * 》as ？： 安全的强制转型运算符，如果转型失败，程序不会引发异常，而是返回null 。
     *
     * 强制向下转型只能在具有继承关系的两种类型之间进行，如果是两种没有任何继承关系的类型，则无法进行类型转换，编译时就会发出警告：转换不可能成功
     * 如果试图把一个父类变量转换成子类类型，则该变量实际引用的实例必须是子类实例才行（即编译时类型为父类类型，而运行时类型是子类类型），否则就会转换失败。
     */
    poly3()


}


fun poly1() {
    open class Fu {
        open var name: String = "rrc"
        open fun me() {
            println("fu类me方法")
        }
    }

    class Zi : Fu() {
        override var name: String = "RRC"
        override fun me() {
            println("zi类me方法")
        }

        fun ziMe() {
            println("zi类方法")
        }
    }

    val f: Fu = Zi()
    println(f.name) //"RRC"
    f.me()//执行子类方法
    //f.ziMe()
}


fun poly2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    //----------
    println("-----")


    val str: Any = "abc"
    println(str is CharSequence)

    if (str is String) println("$str 的长度为${str.length}")


    //----------
    println("-----")


    fun fn(s: Any) {
        if (s !is String) return
        println("$s 的长度为 ${s.length}")
    }
    fn("常帅真帅")


    //----------
    println("-----")


    fun fn1(s: Any) {
        when (s) {
            is String -> println("$s 类型是String,长度为${s.length}")
            is Int -> println("$s 类型是Int")
            else -> println("$s 是未知类型")
        }
    }

    fn1("哈哈")

    //----------
    println("-----")


    fun fn2(s: Any): String {
        // && 前为真，则继续执行&& 后的
        if (s is String && s.length >= 2)
            return "OK"
        return "Error"
    }

    println(fn2("你好"))

    //----------
    println("-----")

    fun fn3(s: Any): String {

        // && 前为假，则继续执行&& 后的
        if (s !is String || s.length >= 2) {
            return "OK"
        }
        return "Error"
    }
    println(fn3("你好"))

}


fun poly3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val a: Any = "abc"
    // a编译时类型为Any，Any 与String 存在继承关系，可以进行转换，编译通过
    // a运行是类型是String，转换成功
    val aa = a as String

    val b: Any = 12
    // b编译时类型为Any，Any 与String 存在继承关系，可以进行转换，编译通过
    // b运行是类型是Int，转换失败:ClassCastException
    //val bb = b as String

    val c: String = "d"
    // 但String 与Number 不荐在继承关系，因此编译发出善告：转换不可能成功
    //val cc = c as Number

    /**
     * 使用as 执行的强制转型是不安全的转换，如果强制转型失败则会引发Cla ssCastException异常，
     * 此时可用as？执行安全的类型转换
     * 使用as ？进行转换时，如果转换失败则会返回null
     *
     * 由于as？转换返回的是可空的值，因此在开发中，程序经常需要对as？转换的结果进行null 判断
     */
    val e: Any = 12
    val ee = e as? String
    println("ee的长度为 = ${ee?.length}")

}
