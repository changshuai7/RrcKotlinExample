@file:TestCom3("hello kotlin")

package part11

import part11.javaan.JavaTagTest1
import part11.javaan.JavaTagTest2
import part11.javaan.JavaTagTest3

/**
 * 十一、注解
 * Java注解和Kotlin的兼容性
 *
 * Java 注解与Kotlin完全兼容，只是在使用时略加注意即可
 */
fun main() {

    /**

    =============注解修饰特定程序单元==============
    引入：
    Kotlin 程序往往比较简沽， Kotlin 程序的一个程序单元有时候会变成Java 的多个程序单元。
    比如：
    eg1、带属性声明的主构造器会变成 Java 的成员变量定义、getter 方法、setter 方法（如果是读写属性〉、构造器参数。
    eg2、属性会变成Java 的成员变量定义、getter方法、setter方法（如果是读写属性）。


    有时候我们只想用注解修饰特定的程序单元
    比如只希望用注解修饰属性对应的幕后字段，或者只希望用注解修饰属性对应的getter方法，那该怎么办
    此时就需要为注解指定作用目标
    语法格式为：
    单个注解 --> @目标： 注解（注解属性值）
    多个注解 -->@目标： 注解1(注解属性值) 注解2(注解属性值) .. . ]  //这里使用空格隔开多个注解

    Kotlin目前支持的【目标】包含如下几个。
    -file ： 指定注解对文件本身起作用。
    -property ： 指定注解对整个属性起作用（这种目标的注解对Java 不可见，因为Java 并没有真正的属性） 。
    -field ： 指定注解对属性的幕后宇段起作用。
    -get ： 指定注解对属性的getter方法起作用。
    -set ： 指定注解对属性的setter方法起作用。
    -receiver ： 指定注解对扩展方法或扩展属性的接收者起作用。
    -param ： 指定注解对构造器的参数起作用。
    -setparam ： 指定注解对setter 方法的参数起作用。
    -delegate ： 指定注解对委托属性存储其委托实例的字段起作用。

     */
    //注解作用于get方法
    compatible1()
    //注解作用于整个文件
    compatible2()


    /**
    =============使用Java注解==============
    Kotlin完全兼容Java 注解，因此可以直接在Kotlin 程序中使用Java 注解。

    需要说明的是， Java 注解的成员变量（相当于Kotlin 注解的属性)是没有顺序的，因此只能通过属性名来设置属性值
    (Kotlin 注解的属性还可通过位置来设置属性值） 。
     */
    compatible3()
}


@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER)
annotation class TestCom1(val info: String = "rrc")
annotation class TestCom2
class ComAnTest1 {

    @get:[TestCom1("hello") TestCom2]
    val name: String = "默认值"
}

fun compatible1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")


    // 由于Kotlin 并不支持单独定义成员变量，因此Kotlin 的反射API 不支持直接操作成员变量
    // 所以这里需要用Java的反射来操作

    val cls = ComAnTest1::class.java   //.java 意思是 获取java的class类对象，而非kotlin的KClass对象
    for (me in cls.getDeclaredMethods()) {
        println("方法 ( $me ) ")
        for (an in me.getAnnotations()) {
            println("方法 ( $me ) 上的注解 $an")
        }
    }

    for (field in cls.getDeclaredFields()) {
        println("变量 ( $field ) ")
        for (an in field.getAnnotations()) {
            println("变量 ( $field ) 上的注解 $an")
        }
    }

    //结果为：
    //方法 ( public final java.lang.String part11.ComAnTest1.getName() )
    //方法 ( public final java.lang.String part11.ComAnTest1.getName() ) 上的注解 @part11.TestCom1(info=hello)
    //方法 ( public final java.lang.String part11.ComAnTest1.getName() ) 上的注解 @part11.TestCom2()
    //变量 ( private final java.lang.String part11.ComAnTest1.name )

    //由此可见，kotlin声明一个val变量，会为其创建幕后字段(对应java的变量)，以及该变量对应的get方法
}

@Target(AnnotationTarget.FILE)
annotation class TestCom3(val info: String = "默认值")

@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class TestCom4(val info: String = "默认值")

fun compatible2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    /**
    1、如果要指定注解作用于整个文件本身，则必须将注解放在package 语句（如果有package语句）之前，或者所有导包语句之前（如果没有package 语句）
    比如：TestCom3
    2、如果要指定注解作用于扩展方法或扩展属性的接收者，则使用带receiver ：的注解修饰整个扩展方法或扩展属性即可。
    比如：TestCom4
     */

}

fun @receiver:TestCom4 String.testEx() {
    println("testEx")
}

@Retention(AnnotationRetention.SOURCE)
@Repeatable
annotation class KotlinTag1(val name: String, val age: Int)

fun compatible3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    /**1、Kotlin 注解既支持使用位置来指定属性值,也可以通过属性名来指定属性值*/
    @KotlinTag1("hello", 12)
    @KotlinTag1(name = "hello", age = 12)
    @KotlinTag1("hello", age = 12)
    @KotlinTag1(age = 12, name = "hello")
    class TestCls1

    /**2、Java 注解只能通过属性名来指定属性值*/
    //@JavaTagTest1("hello",12)  //报错
    @JavaTagTest1(age = 12, name = "hello")
    class TestCls2

    /**3、如果Java 注解中的value 属性是数组类型，那么它会变成Kotlin 注解的vararg属性*/
    @JavaTagTest2("a", "b", "c")
    class TestCls3

    /**4、但如果其他名称的属性是数组类型，那么在Kotlin 中使用该注解时必须显式使用arrayOf函数来构建数组。*/
    @JavaTagTest3(info = ["a", "b", "c"])
    class TestCls4
}






