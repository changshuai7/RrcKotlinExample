package part12

import part12.javacls.JavaClsTest1
import part12.javacls.JavaClsTest2
import part12.javacls.JavaClsTest3

/**
 * 十二、Kotlin和Java的互调、反射
 * 由于Kotlin 最初设计就是兼容Java平台的，因此Java 与Kotlin 相互可以平滑调用
 *
 * 1、Kotlin调Java
 */
fun main() {

    /**
    ============== getter/setter ==============

    Kotlin 调用属性实际上就是访问getter 、setter方法
    1、因此Java 类只要提供了 getXXX 方法，Kotlin 就可将 XXX 当成只读属性；
    2、如果Java 类提供了 getXXX、setXXX 方法， Kotlin 就 XXX 可将其当成读写属性。
    3、如果Java 类提供了 isXXX、setXXX 的方法，且 isXXX 的返回值是boolean，那么 isXXX 就当成boolean类型变量的读写属性
    4、如果Java 类提供了 isXXX 的方法，且isXXX 的返回值是boolean，那么 isXXX 就当成boolean类型变量的的只读属性

    注意：其实Java 是否包含成员变量不重要，关键是getter/setter方法：
    如果只有getter 方法，那么就是只读属性；
    如果有getter和setter方法，那么就是读写属性。
     */
    reflex1()

    /**
    ============== void 和调用名为关键字的成员 ==============

    如果Java 方法的返回值是void ， 在Kotlin 中则对应于Unit 返回值类型。
    由于Kotlin的关键字比Java 多（比如Kotlin 的is 、object 、in 在Java 语言中都不是关键字）
    因此可能出现一种情况： Java 的类名、接口名、方法名等是Kotlin 的关键字。此时就需要使用反引号（就是键盘上数字1 左边的那个键）对关键宇进行转义。

     */
    reflex2()

    /**
    ============== Katlin 的己映射类型 ==============

    虽然Kotlin 并未完整地提供整套类库，但Kotlin 还是为部分Java 类提供了特殊处理，
    这部分Java 类被映射到Kotlin 类，这种映射只在编译阶段发生，在运行阶段依然使用Java 类型。

    byte    - kotlin.Byte
    short   - kotlin.Short
    int     - kotlin.Int
    long    - kotlin.Long
    char    - kotlin.Char
    float   - kotlin.Float
    double  - kotlin.Double
    boolean - kotlin.Boolean

    java.lang.Byte          - kotlin.Byte?
    java.lang.Short         - kotlin.Short?
    java.lang.Integer       - kotlin.Int?
    java.lang.Long          - kotlin.Long?
    java.lang.Character     - kotlin.Char?
    java.lang.Float         - kotlin.Float?
    java.lang.Double        - kotlin.Double?
    java.lang.Boolean       - kotlin.Boolean?

     */

    /**
    ============== Kotlin 对 Java 泛型的转换 ==============

    Kotlin 不支持Java 的泛型通配符语法，但Kotlin 提供了使用处型变来代替泛型通配符。
    Java 的通配符将会转换为Kotlin 的使用处型变（类型投影）
    此外， Java 的原始类型则转换为 Kotlin 的星号投影。


    //TODO 书中的感叹号是什么意思？
    Foo<? extends Bar>      - Foo<out Bar?>
    Foo<? super Bar>        - Foo<in Bar?>
    Foo<?>                  - Foo<out Any?>
    Foo                     - Foo<*>

    Kotlin 和Java 一样，它们都无法在运行时保留泛型信息
    因此在Kotlin 中使用is 运算符进行类型检测时只能检测星号投影（相当于Java 的原始类型〉，【不能检测泛型信息】

     */
    reflex3()


    /**
    ============== Kotlin 对 Java 数组的处理 ==============

    对 Java 数组， Kotlin数组有一个重大的改变： Kotlin 数组是不型变的。因此Array<String>不能赋值给Array<Object> 变量。
    但 Java 数组不同， Java数组是型变的，因此String[]可以直接赋值给Object[] 变量。

    Java 数组的型史可以说是一个缺陷，这个缺陷容易导致程序运行时出现异常．
    例如，如下代码编译完全没有问题，但运行时就会引发ArrayStoreException 异常。

    String[] strArr = new String[5];
    Object[] objArr = strArr;       //Java的数组支持型变（协变）
    objArr[0] = 100;                //存数据是不安全的

    Kotlin 提供了ByteArray、ShortArray 、IntArray、LongArray、CharArray、FloatArray 、DoubleArray 、BooleanArray 数组，
    这几种数组专门用于代替Java 的byte[]、short[]、int[]等基本类型的数组

    注意：IntArray可以替代int[]，但是Array<Int>不可以替代int[]
     */
    reflex4()


    /**
    ============== Kotlin 对 Java 个数可变参数的处理 ==============

    对于参数个数可变的方法， Java 可以直接传入一个数组，但Kotlin 不行。
    Kotlin 要求只能传入多个参数值，但也可通过使用 "*" 解开数组的方式来传入多个 数组元素 作为参数值。
     */
    reflex5()


    /**
    ============== Kotlin 的 check异常 ==============

    由于Kotlin 没有checked 异常，因此对于Java 中可能引发checked 异常的方法、构造器，Kotlin 则不会引发该异常
    Kotlin 既可捕获该异常，也可完全不理会该异常（无须使用throws声明抛出异常〉。
     */

    /**
    ============== Kotlin 的 Object 处理 ==============

    Java 的java.lang.Object 对应于Kotlin中的Any
    又因为Any只声明了toString()、hashCode()和equals()方法，因此需要考虑如何使用java.lang.Object 类中其他方法的问题。

     */
    reflex6()


    /**
    ============== Kotlin 的 访问静态成员 ==============

    虽然Kotlin 本身没有提供static 关键字，但Kotlin 提供了伴生对象来实现静态成员，
    因此Java 类中的静态成员都可通过伴生对象的语法来调用。
     */
    reflex7()

    /**
    ============== Kotlin 的 SAM转换 ==============
    Java 8 支持使用Lambda 表达式来作为函数式接口的实例（ 这种机制被称为SAM 转换），
    Kotlin 同样支持这个功能。

    注意：
    与Java 类似， Lambda 表达式只能创建函数式接口的对象，不能创建抽象类的对象，即使 该抽象类只有一个抽象方法也不行；
    同理， Kotlin 的Lambda 表达式也只能创建函数式接口的 对象，不能创建抽象类的对象。
     */
    reflex8()


    /**
    ============== 在Kotlin 中使用 JNI ==============

    如果要在Java 中使用JNI ，则应该使用native 修饰该方法，表明该方法将会交给平台本地的C 或C＋＋代码来实现。
    Kotlin 同样支持该机制，只不过Kotlin 不使用native 关键字，而是使用 external 关键字。
     */
    reflex9()
}


fun reflex1() {

    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val cls = JavaClsTest1()

    //操作name属性
    cls.name = "rrc"
    println(cls.name)

    //操作isOk属性
    cls.isOk = true
    println(cls.isOk)

    //操作age属性
    println(cls.age)

    //操作isGood属性
    println(cls.isGood)
}

fun reflex2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val cls = JavaClsTest2()
    cls.`in`("hello")
}

fun reflex3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    val list = listOf(1, 2, 3)
    //println(list is List<String>)//is 判断不能检测泛型参数
    println(list is List<*>) //只能检测星号投影 true

}

fun reflex4() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val arr1 = arrayOf<Int>(1, 2, 3)
    val arr2 = intArrayOf(1, 2, 3)

    val cls = JavaClsTest3()
    // 注意：sum方法需要的是int[]类型
    // 因此Kotlin程序不能用Array<Int>数组来调用该方法，而应该使用IntArray 数组来调用该方法
    println(cls.sum(arr2)) // TODO cls点method1的时候，提示里的感叹号是什么意思
}

fun reflex5() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    val cls = JavaClsTest3()
    cls.test1(1, 2, 3) //传入的是vararg

    val arr1 = intArrayOf(1, 2, 3)
    val arr2 = arrayOf(1, 2, 3)
    cls.test1(*arr1) //*arr2不行
}

fun reflex6() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")


    /**
     * 1、wait()/notify()/notifyAll()
     *
     * 强转Object后调用
     *
     */
    open class User

    val u = User()
    val o = u as Object

    //可以直接调用
    //u.wait()
    //u.notify()
    //u.notifyAll()


    /**
     * 2、getClass()
     *
     * java.lang.Object 对象的getClass() 方法用于获取该对象的Java 类
     * Kotlin 的对象则有两种方式来获取该对象的Java 类 。
     */

    //获取User类的java类
    val cls1 = User::class.java
    //获取u对象的java类
    val cls2 = u::class.java
    val cls3 = u.javaClass


    /**
     *
     * 3、clone()
     * 如果要让对象重写clone()方法， 则需要让该类实现kotlin.Cloneable 接口
     */

    class User1 : Cloneable {
        override fun clone(): Any {
            return super.clone()
        }
    }

    /**
     * 4、finalize()
     * java.lang.Object 的finalize() 方法主要用于完成一些资源清理的工作
     * GC 在回收某个对象之前，JVM 会自动调用该对象的finalize() 方法。
     * 如果要重写finalize()方法， 则只要在类中实现该方法即可，不需要使用override 关键字（因为在Any 类中并没有声明finalize()方法）
     */

    class User2 {
        protected fun finalize() {

        }
    }
}

fun reflex7() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    val cls = JavaClsTest3()
    JavaClsTest3.age = 12
    println(JavaClsTest3.name)
}


fun reflex8() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    //Runnable就是函数式接口
    Thread { println("hello") }.start()

}

fun reflex9() {
    //该函数使用了external 修饰，因此该函数不能指定函数体。该函数的函数体将会由平台本 地的C 或C ＋＋代码来实现。
    external fun foo(x: Int): Double
}





