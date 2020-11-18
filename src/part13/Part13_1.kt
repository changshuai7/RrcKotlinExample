package part13

import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.*
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter
import kotlin.reflect.jvm.javaSetter


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


    在某些时候，如果要获取 Kotlin 构造器引用对应的Java 构造器对象（ Constructor ）
    则可通过调用KFunction 的扩展属性javaConstructor 来实现。
    例如如下代码：
    ::Foo. javaConstructor

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

    此外需要说明的是，如果需要引用类的成员方法或扩展方法，那么需要进行限定。例如
    String::toCharArray 才能表明引用String 的toCharArray()方法，单纯地使用 ::toCharArray()不行。
    String::toCharArray 函数引用的类型也不是简单的()->CharArray 类型，而是String.() ->CharArray 类型。

    在某些时候，如果要获取Kotlin 函数引用对应的Java 方法对象（ Method ）
    则可通过调用KFunction 的扩展属性javaMethod 来实现。
    例如如下代码：
    ::abs.javaMethod

     */
    reflexTest6()
    reflexTest7()

    /**
    访问属性值

    获取KCla ss 对象之后，也可通过KClass 对象来获取该类所包含的属性。Kotlin 为属性提供了众多的API 。

    1、  KProperty ： 代表通用的属性。它是 KCallable 的子接口。
    2、  KMutableProperty ： 代表通用的读写属性。它是 KProperty 的子接口。
    3、  KProperty0 ： 代表无需调用者的属性（静态属性）。它是 KProperty 的子接口。
    4、  KMutablePropertyO ： 代表无需调用者的读写属性（静态读写属性〉。它是 KProperty0 的子接口。
    5、  KProperty1 ： 代表需要1 个调用者的属性（成员属性）。。它是KProperty的子接口。
    6、  KMutableProperty1 ： 代表需要1 个调用者的读写属性（成员读写属性）。它是 KProperty1 的子接口
    7、  KProperty2 ：代表需要2 个调用者的属性（扩展属性） 。它是KProperty的子接口。
    8、  KMutableProperty2： 代表需要2 个调用者的读写属性（扩展读写属性） 。它是KProperty2的子接口。


    程序获取代表属性的KProperty 对象之后，可调用get()方法来获取属性的值；
    如果程序要设置属性的值，则需要获取代表属性的 KMutableProperty 即对象。
     */
    reflexTest8()


    /**
    属性引用

    Kotlin 同样提供了"::"符号加属性名的形式来获取属性引用，获取属性引用也属于前面介绍的 KProperty 及其子接口的实例。
    获取 Kotlin 只读属性的引用之后，程序可调用get() 方法来获取属性的值；
    获取 Kotlin 读写属性的引用之后，程序可调用set()方法来修改属性的值，也可调用get（）方法来获取属性的值。
     */
    reflexTest9()

    /**
    由于Kotlin 属性会对应于Java 的3 种成员，因此 KProperty 包含如下3 个扩展属性。
    1、javaField ： 获取该属性的幕后宇段（如果该属性有幕后字段的话〉。该属性返回java.lang.reflect.Field 对象。
    2、javaGetter： 获取该属性的getter方法。该属性返回java.lang.reflect.Method 对象。
    3、javaSetter ：获取该属性的setter方法（如果该属性是读写属性的话） 。该属性返回java.lang.reflect.Method 对象。
    一旦获取了Kotlin 属性的幕后字段的Field 对象、getter和setter 方法的Method 对象之后，剩下的就是Java 反射的事了。
     */
    reflexTest10()

    /**
    绑定的方法和属性引用
    前面介绍的都是通过 KClass (类本身)来获取方法或属性的引用的
    当函数或属性不在任何类中定义时，程序直接使用“：：”加函数名（或属性名）的形式来获取函数或属性的引用，
    这些函数或属性都没有绑定任何对象，因此调用函数或属性时第一个参数必须传入调用者。

    从Kotlin 1.1 开始， Kotlin 支持一种“绑定的方法或属性引用”
    这种方法或属性引用不是通过类获取的，而是【通过对象获取的】，这意味着该方法或属性己经绑定了调用者，因此程序执行这种方法或属性时无须传入调用者。
     */
    reflexTest11()

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


fun reflexTest7() {
    // 获取数的平方根，但该函数要做一些额外处理:
    // 如果该数是正数，则直接获取平方根；如果该数是负数，则获取该数的绝对值的平方根。
    fun abs(d: Double): Double = if (d > 0) d else -d
    fun sqrt(d: Double): Double = kotlin.math.sqrt(d)


    fun f(abs: (Double) -> Double, sqrt: (Double) -> Double): (Double) -> Double {
        return { d: Double -> sqrt(abs(d)) }
    }

    val f = f(::abs, ::sqrt)
    val result = f(0.64)
    println(result)

}

fun reflexTest8() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    data class Item(var name: String = "rrc", val age: Int = 12)

    val kClass = Item::class
    val ins = kClass.createInstance()
    //获取所有的属性
    val props = kClass.declaredMemberProperties
    props.forEach {
        when (it.name) {
            "name" -> {
                //将属性转换为一个调用者的读写属性
                val nameProp = it as KMutableProperty1<Item, Any>
                //修改属性的值
                nameProp.set(ins, "hello world")
            }
            "age" -> {
                //只读属性，只能通过get方法获取属性值
                println(it.get(ins))
            }
        }
    }
    println(ins.toString())

}


var exf = "hello"

class MyItem(var name: String = "rrc", val age: Int = 12) {

    companion object {
        var city = "abc"

    }

    fun show() {
        println("[name = $name,age = $age,city = $city]")
    }
}

fun reflexTest9() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")


    val ins = MyItem("RRC", 100)

    //获取exf 属性，属于KMutablePropertyO 的实例
    val exfProp = ::exf
    exfProp.set("hello_ex")
    println(exfProp.get())

    //获取MyItem 的name 属性，属于KMutableProperty1的实例
    val nameProp = MyItem::name
    nameProp.set(ins, "RRC_HELLO")

    //获取MyItem 的price 属性，属于KProperty1 的实例
    val ageProp = MyItem::age
    println(ageProp.get(ins))

    // 获取MyItem的Companion的City属性，属于KMutableProperty0 的实例
    val cityProp = MyItem.Companion::city
    cityProp.set("city_com")
    println(cityProp.get())

    ins.show()

}

fun reflexTest10() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    //获取exf属性，属于KMutablePropertyO 的实例
    val exfProp = ::exf
    println(exfProp.javaField)
    println(exfProp.javaGetter)
    println(exfProp.javaSetter)

    println()

    //获取MyItem 的name 属性，属于KMutableProperty1 的实例
    val nameProp = MyItem::name
    println(nameProp.javaField)
    println(nameProp.javaGetter)
    println(nameProp.javaSetter)

    println()

    //获取MyItem 的price 属性， 属于KProperty1 的实例
    val ageProp = MyItem::age
    println(ageProp.javaField)
    println(ageProp.javaGetter)

    println()

    //获取MyItem.Companion的city属性，属于KMutableProperty0 的实例
    val cityProp: KMutableProperty0<String> = MyItem.Companion::city
    println(cityProp::class.simpleName)
    println(cityProp.javaField)
    println(cityProp.javaGetter)
    println(cityProp.javaSetter)

}

fun reflexTest11() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val str = "Kotlin"
    //获取对象绑定的方法
    val f: (String, Boolean) -> Boolean = str::endsWith
    //调用绑定的方法时无须传入调用者
    println(f("lin", false)) //输出true

    //获取对象绑定的属性
    val lenProp = str::length
    //调用绑定的属性时无须传入调用者
    println(lenProp.get()) //输出6


    println("----------")

    val list = listOf<String>("a", "b", "c", "d", "e")
    //获取对象绑定的方法
    val fl = list::subList
    //调用绑定的方法无须传入调用者
    println(fl(1, 3))

    val fin = list::indices
    println(fin.get()) //输出0..4


}






