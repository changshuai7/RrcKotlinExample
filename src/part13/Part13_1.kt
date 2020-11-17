package part13

import kotlin.reflect.full.*


/**
 * 十三、Kotlin的反射

 *
 * 使用Kotlin 的反射API --> 对应的jar文件（ kotlin-reflect.jar ）
 */
fun main() {


    /**
    ================== 类引用 ==================

    Kotlin 的类引用是 KClass 对象
    Java 的类引用是 java.lang.Class 对象

    通过任意对象，引用class属性，可以获取KClass对象（ MyObj::class ）
    通过KClass 获取对应的java.lang.Class 对象，则可调用KClass 对象的java 属性。( MyObj::class.java )

     */
    reflexTest1()

    /**

    从KClass 获取类信息
     */
    reflexTest2()

    /**
    创建对象

    获取KClass 对象之后，调用该对象的 createInstance() 方法即可创建该类的实例，该方法总是调用KClass 所代表类的无参数的构造器来创建实例。
    如果需要调用有参数的构造器来创建实例，则可通过KClass 的constructors 属性来获取所有构造器，该属性返回Collection<Function＞集合对象，这意味着构造器的本质依然是一个函数。

     */
    reflexTest3()


    /**

    构造器引用
    构造器的本质是一个函数，即一个返回值为当前类实例的函数。因此程序可将构造器引用当成函数使用。
    此外， Kotlin 允许通过使用【"::"操作符并添加类名】来引用该类的主构造器。
     */
    reflexTest4()

    /**
    调用方法
    所有构造器和方法都属于KFunction 的实例，因此它们都可以通过call()方法来调用。
    所以，程序要调用指定类的方法，只要先获取方法的KFunction 实例，然后调用call（）方法即可。
    使用KFunction 调用方法时，有一点需要说明： 由于方法是面向对象的概念，因此它有一个主调者
     */
    reflexTest5()

    /**
    函数引用
    Kotlin 的函数也是一等公民， 函数也有其自身的类型。Kotlin 程序可以获取函数的引用，把函数当成参数传入另一个函数中。
    Kotlin 也通过"::"符号加函数名的形式来获取特定函数的引用。当存在多个重载函数时，
    Kotlin 可通过上下文推断出实际引用的是哪个函数： 如果Kotlin 无法通过上下文准确推断出引用哪个函数，编译器就会报错。
     */
    reflexTest6()

    // P308页

}


fun reflexTest1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    class User

    //获取KClass对象
    val kClass = User::class
    //获取Java的Class对象
    val jClass = User::class.java

}


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class MyAn1

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class MyAn2(val info: String)


@MyAn1
@MyAn2("hello world")
@Suppress("UNCHECKED_CAST")//SOURCE级别注解，反射无法获取到
class ReflexCls(val name: String, val age: Int) {

    val company: String = "rrc"

    constructor() : this("rrc", 12) {
        println("次级构造器：无参构造")
    }

    constructor(name: String, age: Int, city: String) : this(name, age) {
        println("次级构造器：有参构造")
    }

    fun f1() {
        println("f1 - 无参的方法")
    }

    fun f2(str: String) {
        println("f2 - 有参的方法 str = $str")
    }

    //嵌套类
    class InnerCls

}

//扩展方法
fun ReflexCls.f3Ex() {
    println("f3 - 扩展方法")
}

//扩展属性
val ReflexCls.fieldEx: String
    get() = "exF"


fun reflexTest2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val kClass = ReflexCls::class

    println("******************构造器******************")

    // 通过constructors 属性获取KClass 对象所对应类的全部构造器
    kClass.constructors.forEach { println(it) }
    println()

    // 通过primaryConstructor 获取KClass 对象的主构造器
    println(kClass.primaryConstructor)
    println()

    println("******************方法******************")

    // Returns all functions declared in this class, including all non-static methods declared in the class
    // and the superclasses, as well as static methods declared in the class.
    kClass.functions.forEach { println(it) }
    println()

    // Returns non-extension non-static functions declared in this class and all of its superclasses.
    kClass.memberFunctions.forEach { println(it) }
    println()

    // Returns extension functions declared in this class and all of its superclasses.
    kClass.memberExtensionFunctions.forEach { println(it) }
    println()

    //* Returns all functions declared in this class.
    //* If this is a Java class, it includes all non-static methods (both extensions and non-extensions)
    //* declared in the class and the superclasses, as well as static methods declared in the class.
    kClass.declaredFunctions.forEach { println(it) }
    println()

    // Returns non-extension non-static functions declared in this class.）
    kClass.declaredMemberFunctions.forEach { println(it) }
    println()

    // Returns extension functions declared in this class.
    kClass.declaredMemberFunctions.forEach { println(it) }
    println()

    println("******************属性******************")

    //Returns non-extension properties declared in this class and all of its superclasses.
    kClass.memberProperties.forEach { println(it) }

    //Returns extension properties declared in this class and all of its superclasses.
    kClass.memberExtensionProperties.forEach { println(it) }
    println()

    //Returns non-extension properties declared in this class.
    kClass.declaredMemberProperties.forEach { println(it) }
    println()

    //Returns extension properties declared in this class.
    kClass.declaredMemberExtensionProperties.forEach { println(it) }

    println("******************注解******************")
    kClass.annotations.forEach { println(it) }


    /**
     * 总结：
     * 1、仅带member：就是当前类+父类声明的属性、方法
     * 2、带declaredMember：就是当前类声明的属性、方法
     */

}


fun reflexTest3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val kClass = ReflexCls::class
    val createInstance = kClass.createInstance()
    println("name = ${createInstance.name},age = ${createInstance.age}")

    val functions = kClass.constructors
    for (f in functions) {
        if (f.parameters.size == 2) {
            val call = f.call("人人车", 100)
            println("name = ${call.name},age = ${call.age}")

        }
    }
}

fun reflexTest4() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")


    class Foo(val name: String) {
        var age: Int = 0

        constructor(name: String, age: Int) : this(name) {
            this.age = age
        }
    }

    fun testCons(con: (String, Int) -> Foo) {
        val con1 = con("hello", 12)
        println("name = ${con1.name},age = ${con1.age}")

    }

    testCons(::Foo)
}

fun reflexTest5() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    class Foo {

        fun test1(name: String) {
            println(" --> name = $name")
        }

        fun test1(name: String, age: Int) {
            println(" --> name = $name,age = $age")
        }
    }

    val kClass = Foo::class
    val functions = kClass.declaredFunctions
    for (con in functions) {
        // 两个参数：对象实例+params
        if (con.parameters.size == 2) {
            con.call(kClass.createInstance(), "hello")
        }
        // 三个参数：对象实例+params
        if (con.parameters.size == 3) {
            con.call(kClass.createInstance(), "hello", 100)
        }
    }

}

fun reflexTest6() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    val intList = listOf<Int>(1, 2, 3, 4, 5, 6)
    val strList = listOf<String>("a", "ab", "abc", "abcd")
    println(intList.filter(::filter))
    println(strList.filter(::filter))

}

fun filter(item: Int) = item > 2
fun filter(item: String) = item.length > 2





