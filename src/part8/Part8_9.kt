package part8

/**
 *
 * 八、面向对象(下)
 *
 * 8.8、枚举
 *
 */
fun main() {


    /**
    枚举类和普通类的区别
    1、枚举类可以实现一个或多个接口，使用enum 定义的枚举类默认继承kotlin.Enum 类，而不是默认继承Any 类，
    因此枚举类不能显式继承其他父类。其中Enum 类实现了kotlin.Comparable 接口。
    2、使用enum 定义的非抽象的枚举类不能使用open 修饰，因此枚举类不能派生子类。
    3、枚举类的构造器只能使用private 访问控制符，如果省略了构造器的访问控制符，则默认使用private 修饰；如果强制指定访问控制符，则只能指定private 修饰符。
    4、枚举类的所有实例必须在枚举类的第一行显式列出，否则这个枚举类永远都不能产生实例。列出枚举实例后最好用【分号结尾】。

    枚举类提供了如下方法：
    1、EnumClass.valueOf(value: String): EnumClass ：类似于Java 枚举类的valueOf（）方法，
    用于根据枚举的字符串名获取实际的枚举值。如果传入的名称参数与类中定义的任何枚举常量均不匹配， valueOf（）方法将抛出IllegalArgumentException 异常。
    2、EnumClass .values(): Array<EnumClass＞ ：类似于Java 枚举类的values()方法，用于获取该枚举的所有枚举值组成的数组。

    所有的枚举类都继承了kotlin.Enum 类，所以枚举类可以直接使用该类中所包含的属性和方法。kotlin.Enum 类中提供了如下属性和方法。
    1、name 属性： 返回此枚举实例的名称，这个名称就是定义枚举类时列出的所有枚举值之一。
    与此属性相比，大多数程序员应该优先考虑使用toString()方法，因为toString()方法可返回对用户更加友好的名称。
    2、ordinal 属性： 返回枚举值在枚举类中的索引值（就是枚举值在枚举声明中的位置，第一个枚举值的索引值为0 ） 。
    3、int compareTo(E o ）： 该方法用于与指定的枚举对象比较顺序，同一个枚举实例只能与相同类型的枚举实例进行比较。
    如果该枚举对象位于指定的枚举对象之后，则返回正整数；如果该枚举对象位于指定的枚举对象之前，则返回负整数； 否则返回0
    4、String toString （）： 返回枚举常量的名称，与name 属性相似，但toString（）方法更常用。
     */
    enumTest1()


    /**
    枚举的属性、方法、构造器
    1、由于枚举类应该设计成不可变类，因此它的属性值不允许改变，这样会更安全。Kotlin 禁止开发者对属性赋值，并推荐使用val 为枚举声明只读属性。
    2、由予枚举的属性都是只读属性，枚举必须在构造器中为这些属性指定初始值
    （或在初始化块中指定初始值， 一般不会在定义时指定初始值，因为这样会导致所有枚举值的该属性值总是相同的），因此应该为枚举类显式定义带参数的构造器。
     */
    enumTest2()


    /**
     * 枚举类实现接口
     */
    enumTest3()


    /**
     * 包含抽象方法的抽象枚举类
     *
     *
     * 注意：
     * 1、枚举类中包含抽象方法时依然不能使用abstract 修饰枚举类（因为系统自动会为它添加abstract 关键宇）
     * 但因为枚举类需要显式创建枚举值，而不是作为父类，所以在定义每个枚举值时必须为抽象成员提供实现，否则将出现编译错误。
     * 2、如下例子中，PLUS 、MINUS 、TIMES 和DIVIDE后的花括号部分实际上就是一个类体部分
     * 括号部分实际上是对象表达式（匿名内部类）的类体部分，所以这个部分的代码语法与前面介绍的对象表达式（匿名内部类）的语法大致相似。
     */
    enumTest4()
}


fun enumTest1() {

    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    for (item in English.values()) {
        println("${item.name} - ${item.toString()}")
    }


    println("-------")

    println(English.valueOf("A").toString())
    println(English.B.name) //返会String
    println(English.B.ordinal)//返回Int
    println(English.A.compareTo(English.D))//-3
}

enum class English {
    A, B, C, D;//必须以分号结尾
}


fun enumTest2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
}

enum class Gender(val cnName: String) {
    MALE("男"), FEMALE("女");//

    fun info() {
        when (this) {
            MALE -> println("这是男人")
            FEMALE -> println("这是女人")
        }
    }
}

enum class Gender2 {
    MALE("男"), FEMALE("女");

    val cnName: String

    constructor(cnName: String) {
        this.cnName = cnName
    }
}


fun enumTest3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    Gender3.FEMALE.doSth()
    Gender4.FEMALE.doSth()

}

interface GeneInterface {
    fun doSth()
}

enum class Gender3(val cnName: String) : GeneInterface {
    MALE("男"), FEMALE("女");//

    override fun doSth() {
        println("doSth")
    }
}

enum class Gender4(val cnName: String) : GeneInterface {
    MALE("男") {
        override fun doSth() {
            println("doSth -- 男")
        }
    },
    FEMALE("女") {
        override fun doSth() {
            println("doSth -- 女")
        }
    };

}

fun enumTest4() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    println(Calculator.PLUS.calc(12, 11))
}

enum class Calculator() {
    PLUS {
        override fun calc(x: Int, y: Int): Int {
            return x + y
        }
    },
    MINUS {
        override fun calc(x: Int, y: Int): Int {
            return x - y
        }
    },
    TIMES {
        override fun calc(x: Int, y: Int): Int {
            return x * y
        }
    },
    DIVIDE {
        override fun calc(x: Int, y: Int): Int {
            return x / y
        }
    };

    //计算抽象方法
    abstract fun calc(x: Int, y: Int): Int

}

