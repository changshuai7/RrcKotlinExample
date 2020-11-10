package part10


/**
 * 十、泛型
 *
 */
fun main() {


    /**
     *
     * 泛型的简单实用：
     *
     * 与Java 泛型相同， Kotlin 同样提供了泛型支持。
     * 对于简单的泛型类、泛型函数的定义，Kotlin 与Java 的差别不大.
     *
     * Kotlin 泛型的特色功能是型变支持
     * Kotlin 提供了声明处型变和使用处型变两种支持，而Java 只支持使用处型变
     */
    generic1()


    /**
     * 从泛型类派生子类
     *
     * 当创建了带泛型声明的接口、父类之后，可以为该接口创建实现类，或者从该父类派生子类。
     * 需要指出的是，当使用这些接口、父类时不能再包含泛型形参
     */
    generic2()


}


fun generic1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    open class Apple<T> {
        private var info: T?

        constructor() {
            info = null
        }

        constructor(info: T) {
            this.info = info
        }
    }

    val a1: Apple<String> = Apple("红富士")
    val a2 = Apple<Int>(666)

}

fun generic2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    open class Apple<T> {
        open var info: T?

        constructor() {
            info = null
        }

        constructor(info: T) {
            this.info = info
        }
    }

    //class LvFuShi : Apple<T>()  这是错误的

    //泛型指定：在定义类、接口、方法时可以声明泛型形参，在使用类、接口、方法时应该为泛型形参传入实际的类型。
    class LvFuShi : Apple<String>()
    //泛型传递
    class HongFuShi<T> : Apple<T>()
}




