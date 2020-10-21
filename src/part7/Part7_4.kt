package part7

/**
 * 七、面向对象(4)
 * 深入构造器
 * Kotlin 类必须包含一个或一个以上的构造器。
 */
fun main() {


    /**
     *
     * Kotlin 类可以定义0～1 个主构造器和O～N 个次构造器。如果主构造器没有任何注解或可见性修饰符，则可以省略constructor 关键字。
     *
     * 主构造器作为类头的一部分，可以声明形参，但它自己并没有执行体
     * 其作用主要有两点。
     * 》初始化块可以使用主构造器定义的形参。
     * 》在声明属性时可以使用主构造器定义的形参。
     *
     * 由此可见， Kotlin 的主构造器并不是传统意义上的构造器，它更像Java 的初始化块
     * 或者说是对初始化块的增强一－Java 的初始化块不能传入参数： Kotlin 通过主构造器的设计，允许为初始化块传入参数。
     *
     * 初始化块的语法如下：
     * init{
     *  // 初始化块中的可执行代码，可以使用主构造器定义的参数
     * }
     * 初始化块中的代码可以使用主构造器定义的参数，也可以包含任何可执行语句，包括定义局部变量、调用其他对象的方法，以及使用分支、循环语句等。
     *
     * 当程序通过主构造器创建对象时，系统其实就是调用该类里定义的初始化块
     * 如果一个类里定义了两个普通初始化块，则前面定义的初始化块先执行，后面定义的初始化块后执行。
     */

    /**
     * 如果程序员没有为Kotlin 类提供任何构造器（主构造器和次构造器）
     * 则系统会为这个类提供一个无参数的主构造器，这个构造器的执行体为空，不做任何事情。
     * 无论如何， Kotlin 类至少包含一个构造器。
     *
     * 一旦程序员提供了自定义的构造器，系统就不再提供默认的构造器
     * 如果希望该类保留无参数的构造器，或者希望有多个初始化过程，则可以为该类提供多个构造器。如果一个类里提供了多个构造器，那么就形成了构造器重载。
     */
    cons1()


    /**
     * 次级构造器和构造器的重载
     * Kotlin 允许使用constructor 关键宇定义N 个次构造器
     * 在Java中：Java 的初始化块（构造代码块）其实是假象，其会自动插入到每个构造器的前面执行。
     *
     * 而Kotlin 的主构造器其实属于初始化块（或者说，初始化块其实是主构造器的执行体〉，
     * 因此Kotlin 要求所有的次构造器必须委托调用主构造器，可以【直接委托】或通过别的次构造器【间接委托】。
     * 所谓“委托”，其实就是要先调用主构造器（执行初始化块中的代码），然后才执行次构造器代码。
     * 如果没有主构造器，则不存在委托，但是次级构造器在执行前，依然会执行执行初始化块，即初始化块插入到次级构造器之前执行。
     *
     * 普通构造器--> 委托主构造器（+ 初始化块） 如果没有主构造器，初始化块依然执行
     */
    cons2()

    /**
     * Java 构造器使用this(参数)来调用重载的构造器，且this （参数）调用必须作为构造器的第一条语句．
     * 使用this 调用重载的构造器时，系统会根据this 后括号里的实参来推断调用哪个构造器．
     * 由此可见Kotlin 的构造器委托其实就是Java 构造器中的this（参数）调用
     * 只不过Kotlin 要求将this （参数）调用放在构造器声明的冒号之后，而Java 要求将this（参数）调用放在构：造器的第一行
     * 它们的本质是一样的。Kotlin 为之换个“新马甲”：“委托"
     */
    cons3()


    /**
     * 主构造器申明属性
     *
     * Kotlin 允许在主构造器上声明属性，
     * 直接在参数之前使用var 或val 即可声明属性一一使用var 声明的是读写属性，使用val 声明的是只读属性。
     * 当程序调用这种方式声明的主构造器创建对象时，传给该构造器的参数将会赋值给对象的属性。
     *
     *
     *
     * 如果主构造器的所有参数都有默认值，程序能以构造参数的默认值来调用该构造器（即不需要为构造参数传入值），此时看上去就像调用无参数的构造器。
     */
    cons4()

}


fun cons1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")


    class Person(name: String) {
        //定义了一个初始化块
        init {
            println("我是初始化块1")
            println("传入的参数为${name}")
            if (name.length > 1) {
                println("name的长度超过了1")
            }
            var b = "hello"
            println("$b , world!")
        }

        //定义第二个初始化块
        init {
            println("我是初始化块2")
        }
    }

    var p = Person("RRC")

    /**
     * 主构造器的主要作用就是为初始化块定义参数，因此主构造器更像是初始化块的一部分。也可以说，【初始化块就是主构造器的执行体】
     */

}


fun cons2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    class Person {

        var name: String
        var age: Int

        init {
            println("初始化块")
        }

        constructor(name: String, age: Int) {
            this.name = name
            this.age = age
        }

        fun show() {
            println("name = $name,age = $age")
        }

    }

    val p = Person("rrc", 12)
    p.show()
}


fun cons3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    class Person(name: String) {

        var name: String
        var age = 0
        var city: String? = null

        init {
            println("我是初始化块")
            this.name = name
        }

        constructor(name: String, age: Int) : this(name) {
            //如果这里不委托this(name),则会报错：Kotlin: Primary constructor call expected
            this.age = age
        }

        constructor(name: String, age: Int, city: String) : this(name, age) {
            this.city = city
        }

        fun show() {
            println("name = $name,age =$age,city = $city")
        }
    }

    val p = Person("rrc", 12)
    p.show()
    val p1 = Person("rrc", 12, "北京")
    p1.show()

}

fun cons4() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    class Person(var name: String, var age: Int)

    val p = Person("abc", 12)
    println("p.name = ${p.name},p.age = ${p.age}")

    //-----------------------------//

    class Person1(var name: String, var age: Int = 100)
    //参数指定了默认值， 因此程序能以构造参数的默认值来调用构造器（无须传入参数值）

    val pp = Person1("rrc")
    println("p.name = ${pp.name},p.age = ${pp.age}")

    //-----------------------------//


    val pp1 = Person1(age = 200, name = "haha")
    println("p.name = ${pp1.name},p.age = ${pp1.age}")


}


