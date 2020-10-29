package part8

/**
 *
 * 八、面向对象(下)
 *
 * 8.4、接口
 *
 */
fun main() {


    /**
     * 定义接口的语法：
     * ［修饰符） interface 接口名：父接口1 ， 父接口2 ... {
     *      零个到多个属性定义．． ．
     *      零个到多个方法定义．．．
     *      零个到多个嵌套类、嵌套接口、嵌套枚举定义．． ．
     * }
     *
     * 1、与Java 8 相似的是， Kotlin 的接口既可包含抽象方法，也可包含非抽象方法，这是非常自由的。
     * 但接口中的属性没有幕后字段， 因此无法保存状态，所以接口中的属性要么声明为抽象属性，要么为之提供setter/getter方法(成为计算属性)。
     *
     * 2、由于接口定义的是一种规范，因此接口中不能包含构造器和初始化块定义
     *
     * 3、接口中定义的方法既可是抽象方法，也可是非抽象方法。
     * 如果一个方法没有方法体， Kotlin会自动为该方法添加abstract 修饰符；
     * 如果一个只读属性没有定义getter方法， Kotlin会自动为该属性添加abstract 修饰符；
     * 如果一个读写属性没有定义getter方法 、setter方法， Kotlin会自动为该属性添加abstract 修饰符。
     *
     * 4、Kotlin 接口与Java 接口还有一点区别：
     * Java 接口中的所有成员都会自动使用public 修饰，如果为这些成员指定访问权限，也只能指定public 访问权限；
     * 但Kotlin 接口中的成员可支持private 和public 两种访问权限。具体规则如下。
     *  4.1、对于需要被实现类重写的成员，如抽象方法、抽象属性，只能使用public 修饰。如果要添加访问控制符，则只能用public ；如果不加访问权限，则系统默认添加public 。
     *  4.2、对于不需要被实现类重写的成员，如非抽象方法、非抽象属性、嵌套类（包括嵌套抽象类〉、嵌套接口、嵌套枚举，都可使用private 或public 修饰，
     *      我们可以根据需要添加private 修饰符将这些成员限制在接口内访问：如果不加访问权限，则系统默认添加public 。
     *
     */
    interface1()


    /**
     * 接口的继承
     *
     * 接口支持多继承
     *
     */

    /**
     * 接口和抽象类的区别：
     * 相同之处：
     * 1、接口和抽象类都不能被实例化，它们都位于继承树的顶端，用于被其他类实现和继承。
     * 2、接口和抽象类都可以包含抽象成员，实现接口或继承抽象类的普通子类都必须实现这些抽象成员。
     *
     * 不同之处：
     * 1、接口中不包含构造器；但抽象类中可以包含构造器，抽象类中的构造器并不是用于创建对象的，而是让其子类调用这些构造器来完成属于抽象类的初始化操作。
     * 2、接口中不能包含初始化块；但抽象类中则完全可以包含初始化块。
     * 3、一个类最多只能有一个直接父类，包括抽象类；但一个类可以直接实现多个接口，通过实现多个接口可以弥补Kotlin 单继承的不足。
     */
    interface2()


}


fun interface1() {
}

interface OutPutInter {
    // 只读属性没有定义get方法，为抽象属性(自动添加abstract和public)，将来被实现以后可以生成幕后字段
    val name: String

    // 只读属性定义了get方法，为计算属性
    val nameLen: Int
        get() = name.length

    //读写属性没有定义get、set方法，为抽象属性(自动添加abstract和public)，将来被实现以后可以生成幕后字段
    var city: String

    //读写属性定义了get、set方法，为计算属性
    var city2: String
        get() = "北京"
        set(value) {
            this.city = value
        }

    //定义抽象方法(自动添加abstract和public)
    public abstract fun method1()

    //定义非抽象方法，默认为public
    fun method2() {
        println("method2")
    }

    //定义非抽象方法，可用private修饰
    private fun method3() {
        println("method3")
    }

}

fun interface2() {
    val d = D("rrc")
    d.test()
}

interface A {
    val name: String
    val nameLen
        get() = name.length

    fun aMe()
}

interface B {
    fun bMe()
}

interface C : A, B {
    fun test() {
        aMe()
        bMe()
        println("name的长度为$nameLen")
    }
}

class D(name: String) : C {

    override val name: String = name

    override fun aMe() {
        println("aMe")
    }

    override fun bMe() {
        println("bMe")
    }
}

