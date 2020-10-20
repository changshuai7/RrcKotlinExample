package part7


/**
 *
 * 七、面向对象
 *
 * Kotlin 也支持面向对象的三大特征： 封装、继承和多态。
 *
 */
fun main() {


    /**
     * Kotlin 定义类的简单语法如下：
     *
     * ---------------------------
     * ［修饰符J class 类名［ constructor 主构造器］｛
     *      零个到多个次构造器定义． ．．
     *      零个到多个属性．．．
     *      零个到多个方法． ．．
     * }
     *
     * ---------------------------
     *
     * 1、修饰符可以是public 、internal 、private （只能出现其中之一）、final、open 、abstract （也只能出现其中之一〉或者完全省略修饰符
     * open : 是Kotlin 新引入的修饰符， open 是final 的反义词，用于修饰一个类、方法或属性，表明类可派生子类，方法或属性可被重写
     *
     * 2、Kotlin 的类定义由类名、类头（指定其泛型声明、主构造器等）和用花括号包围的类体构成。类头和类体都是可选的。
     * 3、对于一个类定义而言，可以包含三种最常见的成员：构造器、属性和方法，这三种成员都可以定义零个或多个
     * 如果三种成员都只定义零个，就是定义了一个空类。空类没有类体，可以省略花括号。
     * 4、Kotlin创建对象无需使用new关键字
     */
    object1()


    /**
     * 构造器是一个类创建对象的根本途径
     * 一个Kotlin 类可以有O～1 个主构造器 和 0～N 个次构造器
     * 主构造器是类头的一部分，它跟在类名（和泛型声明）后
     *
     *
     */
    object2()


    /**
     * 定义属性的语法格式如下：
     * （修饰符） var|val 属性名：类型［＝默认值］
     *      [<getter>]
     *      [<setter>]
     *
     *
     * 1、修饰符：修饰符可以省略，也可以是 public | protected | internal | private 、final | open |abstract,
     * public | protected | internal | private只能出现其中之一， final | open |abstract 也只能出现其中之一。
     * 对于属性使用public 、protected 、internal 修饰和不使用访问控制符，效果是一样的。如果使用private 修饰该属性，该属性将作为幕后属性（后续会介绍）使用。
     * 2、var|val ：使用var 声明读写属性，使用val 声明只读属性（不能修改） 。
     * 3、属性名：属性名只要是一个合法的标识符即
     * 4、类型：类型可以是Kotlin 允许的任何数据类型。如果Kotlin 可以从初始值或getter法的返回值推断出属性的类型，那么此处可省略属性的类型。
     * 5、默认值：定义属性时还可以指定一个可选的默认值。要么在此处指定初始值，要么在构造器或初始化块中指定初始值。
     * 6、getter、setter： 用于为该属性编写自定义的getter 、setter 方法。如果不指定， Kotlin会为读写属性提供默认的getter、setter 方法：为只读属性提供默认的getter 方法（只读属性不允许定义setter 方法）。
     *
     * 顶层函数的语法与类中方法的语法的主要区别就在于修饰符：顶层函数不能使用protected 、abstract 和final 修饰符，
     * 但类中的方法可使用public | protected | internal | private 、final | abstract| open 这些修饰符。
     *
     *
     *
     * 定义构造器的语法格式如下：
     * ［修饰符］ constructor （形参列表）｛
     *      //由零条到多条可执行语句组成的构造器执行体
     *}
     *
     * 对定义构造器的语法格式详细说明如下。
     * 1、修饰符： 修饰符可以省略，也可以是public 、protected 、internal 、private 其中之一。
     * 2、形参列表： 其格式和定义方法的形参列表的格式完全相同。
     * 值得指出的是，构造器既不能定义返回值类型，也不能使用Unit 声明构造器没有返回值。
     */
    object3()


    /**
     * 构造器->对象的创建
     */
    object4()

    /**
     * 方法：Kotlin 的方法与函数其实是统一的，不仅定义函数和方法的语法相同，而且定义在类中的方法依然可独立出来。
     * 也就是说，即使我们将方法定义在类里面，这个方法也依然可以转换为函数。
     */
    object5()

    /**
     * 中缀表达式
     * Kotlin 的方法还可使用infix 修饰，这样该方法就可通过中缀表示法调用，就像这些方法是双目运算符一样。
     * 需要指出的是， infix 方法只能有一个参数一一原因很简单，因为双目运算符的后面只能带一个参数。
     */
    object6()

    /**
     * componentN 方法与解构
     * Kotlin允许将一个对象的N 个属性“解构”给多个变量，写法如下：
     * 个属性“解构”给多个变量，写法如下：
     *
     * var (name, pass) = user
     *
     * 上面这行代码相当于将user 对象的两个属性分别赋值给name 、pass 两个变量，这两个变量的类型会根据user 对象的属性类型来推断。
     * 如果希望将对象解构给多个变量，那么必须为该对象的类定义componentN()方法。
     * 程序希望将对象解构给几个变量，就需要为该类定义几个componentN()方法，且该方法需要使用operator 修饰
     *
     * 在某些时候，程序希望解构对象后面几个componentN()方法的返回值、忽略前面几个componentN()方法的返回值，此时可通过下画线( _ )来占位
     */
    object7()


    /**
     * 数据类型和返回多个值的函数
     *
     * Kotlin 本身并不支持定义返回多个值的函数或方法，但通过上面所介绍的对象解构
     * 我们同样可让Kotlin 函数返回多个值一一本质是让Kotlin 返回一个支持解构的对象。
     * 为了简化解构的实现， Kotlin 提供了一种特殊的类：数据类。数据类专门用于封装数据
     *
     *
     * 数据类除使用data 修饰之外，还要满足如下要求。
     * 》主构造器至少需要有一个参数。
     * 》主构造器的所有参数需要用val 或var 声明为属性。
     * 》数据类不能用abstract 、open 、sealed 修饰，也不能定义成内部类。
     * 》在Kotlin 1.1 之前，数据类只能实现接口：现在数据类也可继承其他类。
     *
     *
     * 定义数据类之后，系统自动为数据类生成如下内容。
     * 》生成equals()/hashCode()方法。
     * 》自动重写toString()方法，返回形如"User(name=John, age=42）"的字符串。
     * 》为每个属性自动生成operator 修饰的componentN()方法。
     * 》生成copy()方法， 用于完成对象复制。
     *
     *
     * Kotlin 标准库提供了Pair 和Triple 两个数据类
     * 正如它们的名字所暗示的Pair 数据类可包含两个任意类型的属性； Triple 可包含三个任意类型的属性。
     * 在某些希望快速开发的场景下，我们也可直接使用Kotlin 提供的Pair 和Triple 两个数据类。
     */

    object8()

    /**
     * lambda的解构
     *
     * Kotlin允许对Lambda 表达式使用解构
     * 如果Lambda 表达式的参数是支持解构的类型（如Pair 或Map.Entry，它们都具有operator 修饰的componentN()方法）
     * 那么即可通过将它们放在括号中引入多个新参数来代替单个参数。例如，如下两种写法是一样的：
     */
    object9()


}


fun object1() {

    //定义一个类
    open class MyCls(name: String, age: Int) {

    }

    //定义了一个空类
    class EmptyCls
}


fun object2() {

    // 主构造器是【类头】的一部分，它跟在 类名（和泛型声明〉后。
    // 主构造器就是在 类头使用constructor 关键宇定义一个无执行体的构造器。
    // 虽然主构造器不能定义执行体，但可以定义多个形参，这些形参可以在属性声明、初始化块中使用。
    class MyCls1 constructor(name: String, age: Int) {

    }

    // 如果主构造器没有任何注解或修饰符，则可以省略constructor 关键字
    class MyCls2(name: String, age: Int) {

    }

    // Kotlin 还提供了一个功能：如果程序员没有为非抽象类定义任何（主或次）构造器
    // 系统会自动提供一个无参数的【主构造器】，该构造器默认用public修饰。
    // 一旦程序员为一个类提供了构造器，系统将不再为该类提供构造器。

}


fun object3() {

    class MyCls {
        val name: String = "Tom"
    }
}


fun object4() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    class Cls(name: String, age: Int) {
        val name = name
        val age = age
        fun say() {
            println("name = $name,age = $age")
        }
    }

    val c: Cls = Cls("RRC", 5)
    c.say()
    println(c.name)

}


fun object5() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    class MyCls {
        fun eat(thing: String) {//这个方法可以被分离来使用
            println(thing)
        }
    }

    // 为了与前面介绍的获取函数的引用保持一致，这里要获取某个方法的引用同样需要在方法 名前面添加两个冒号（：：）。
    // 由于此处的方法属于特定的类，因此还需要在方法引用之前添加类名。
    val f: (MyCls, String) -> Unit = MyCls::eat
    f(MyCls(), "土豆")

}


fun object6() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    class Apple(weight: Int) {
        val weight = weight
        infix fun add(apple: Apple): Apple {
            return Apple(weight + apple.weight)
        }

        infix fun drop(apple: Apple): Apple {
            return Apple(weight - apple.weight)
        }

        fun show() {
            println("苹果的重量为：$weight")
        }
    }

    val a1 = Apple(100)
    val a2 = Apple(20)
    (a1 add a2).show()
    (a1 drop a2).show()
}


fun object7() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    class User(name: String, age: Int) {
        val name = name
        val age = age

        //定义operator 修饰的componentN 方法，用于解构
        operator fun component1(): String {
            return name
        }

        //定义operator 修饰的componentN 方法，用于解构
        operator fun component2(): Int {
            return age
        }
    }

    val u1 = User("RRC", 5)
    val (name, age: Int) = u1
    println("User的name=$name,age=$age")

    val (_, a) = u1
    println("User的age=$age")


    // ----> 联想一下Map的遍历方式

    val mapOf = mapOf("a" to 1, "b" to 2, "c" to 3)
    for ((key, value) in mapOf) {
        println("map的key=$key,value = $value")
    }
    mapOf.iterator()

    // Maps.kt --> 294行
    // Map.Entry[扩展]了两个方法：
    // public inline operator fun <K, V> Map.Entry<K, V>.component1(): K = key
    // public inline operator fun <K, V> Map.Entry<K, V>.component2(): V = value

    //原文注释如下：
    /**
     * Returns the value component of the map entry.
     *
     * This method allows to use destructuring declarations when working with maps, for example:
     * ```
     * for ((key, value) in map) {
     *     // do something with the key and the value
     * }
     * ```
     */
}


fun object8() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    //定义了一个数据类型
    data class Result(val name: String, val age: Int)

    fun getResult(type: String): Result {
        return when (type) {
            "1" -> Result("RRC", 100)
            "2" -> Result("RRA", 200)
            else -> Result("", 0)
        }
    }

    val (name, age) = getResult("1")
    println("Result为:name=$name，age=$age")
    println("data类toString结果为${getResult("2").toString()}")
    println("data类复制结果为${getResult("1").copy()}")

}

fun object9() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    mapOf("a" to 1, "b" to 2)
            .map { println("key=${it.key},value=${it.value}") }
    println("-----")
    mapOf("a" to 1, "b" to 2)
            .map { entry -> println("key=${entry.key},value=${entry.value}") }
    println("-----")
    mapOf("a" to 1, "b" to 2)
            .map { (key, value) -> println("key=${key},value=${value}") }//使用解构

    // 请注意Lambda 表达式包含两个参数和使用解构的区别：
    // {a -> ...} 一个参数
    // {a,b->...} 两个参数
    // {(a,b)->...} 一个解构对
    // {(a,b),c->...} 一个解构对加一个参数

    // Lambda 表达式的多个参数是不需要使用圆括号的
    // 只要看到在Lambda 表达式的形参列表中出现圆括号，那就是使用解构。
    // 类似的，如果希望只使用后面几个componentN()方法的返回值，则可使用下画线来代替。
    println("-----")
    mapOf("a" to 1, "b" to 2)
            .map { (_, value) -> println("key=空,value=${value}") }//使用解构

}





