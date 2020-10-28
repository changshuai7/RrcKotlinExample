package part8

/**
 *
 * 八、面向对象(下)
 *
 * 8、抽象类
 *
 */
fun main() {

    /**
     * 有abstract 修饰的成员，无须使用open 修饰
     * 当使用abstract 修饰类时，表明这个类需要被继承：当使用abstract 修饰方法、属性时，表明这个方法、属性必须由子类提供实现（即重写〉。
     * 而使用final修饰的类不能被继承，使用final 修饰的方法不能被重写。因此final 和abstract 永远不能同时使用。
     * 但是，抽象类中的具体方法、属性依然有final修饰，如果程序需要重写抽象类中的具体方法、属性，则依然需要显式为这些方法、属性添加open 修饰。
     */

    /**
     * 1、抽象类必须使用abstract 修饰符来修饰，抽象成员也必须使用abstract 修饰符来修饰，抽象方法不能有方法体。
     * 2、抽象类不能被实例化，【无法调用抽象类的构造器创建抽象类的实例】。即使抽象类中不包含任何抽象成员，这个抽象类也不能创建实例。
     * 3、抽象类可以包含属性、方法（普通方法和抽象方法都可以）、构造器、初始化块、嵌套类（接口、枚举） 5 种成员。抽象类的构造器不能用于创建实例，主要用于被其子类调用。
     * 4、含有抽象成员的类（包括直接定义了一个抽象成员：或继承了一个抽象父类，但没有完全实现父类包含的抽象成员；或实现了一个接口，但没有完全实现接口包含的抽象成员三种情况）只能被定义成抽象类。
     */

    /**
     * abstract 不能用于修饰局部变量， Kotlin 没有抽象变量的说法；
     * abstract 也不能用于修饰构造器，没有抽象构造器，抽象类中定义的构造器只能是普通构造器。
     * 使用abstract 关键字修饰的方法必须被其子类重写才有意义，否则这个方法将永远不会有方法体，
     * 因此abstract 方法不能定义为private 访问权限，即private和abstract 不能同时修饰方法。
     */
    abs1()

    /**
     * 与Java 类似的是， Kotlin 也允许使用抽象成员重写非抽象成员。例如如下代码：
     */
    abs2()


    /**
     * 密封类：sealed
     * 密封类是一种特殊的抽象类，专门用于派生子类。
     * 密封类与普通抽象类的区别在于：
     * 密封类的子类是固定的。密封类的子类必须与密封类本身在同一个文件中，在其他文件中则不能为密封类派生子类，这样就限制了在其他文件中派生子类。
     *
     * 密封类经过Kotlin 编译器编译之后就得到一个抽象类的class 文件，只不过该抽象类的普通构造器都会声明为private 权限，
     * 即：密封类的所有构造器都必须是private 的，不管开发者是否使用private 修饰，系统都会为之自动添加private 修饰。
     * 最后有一点需要说明的是，密封类的直接子类必须与密封类位于同一个文件中，但密封类的间接子类（子类的子类）则无须在同一个文件中。
     *
     */
    sealed1()

}


fun abs1() {

    val c: Shape = Circle("red", "圆形", 10.0)
    println(c.callPerimeter())

}

abstract class Shape(var color: String) {

    // 抽象类不能用于创建实例，只能当作父类被其子类继承。
    // Shape 类中既包含了初始化块，也包含了构造器
    // 这些都不是在创建Shape对象时被调用的，而是在创建其子类的实例时被调用。

    init {
        println("Shape抽象类的初始化块")
    }

    abstract val type: String//类型
    abstract fun callPerimeter(): Double//面积

}

class Circle(color: String, type: String, cir: Double) : Shape(color) {

    override val type: String = type
    private val cir: Double = cir

    override fun callPerimeter(): Double {
        return Math.PI * cir * cir
    }
}


fun abs2() {

    open class Fu {
        open fun test() {}
    }

    //抽象成员重写非抽象成员
    abstract class Zi : Fu() {
        abstract override fun test()
    }
}


fun sealed1() {

    val f1: Fruit = Banner()
    val f2: Fruit = Apple()
    f1.taste()
    f2.taste()
}

sealed class Fruit {
    abstract fun taste()
}

open class Banner : Fruit() {
    override fun taste() {
        println("口感黏黏的")
    }
}

open class Apple : Fruit() {
    override fun taste() {
        println("口感脆脆的")
    }
}


