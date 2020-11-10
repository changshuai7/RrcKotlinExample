package part11

/**
 * 十一、注解
 * 元注解（元注解的作用就是负责注解其他注解）
 */
fun main() {

    /**
    ========== ＠Retention ==========

    @Retention只能修饰注解定义，用于指定被修饰的注解可以保留多长时间。
    @Retention元注解包含一个AnnotationRetention 类型的value 属性，所以使用＠Retention 时必须为该value属性指定值。
    value 属性的值只能是如下3 个。
    1、AnnotationRetention.SOURCE ： 注解只保留在源代码中，编译器直接丢弃这种注解。
    2、AnnotationRetention.BINARY ： 编译器将把注解记录在class 文件中。当运行该字节码文件时， JVM 不可获取注解信息。
    3、AnnotationRetention.RUNTIME ： 编译器将把注解记录在class 文件中。当运行该字节码文件时，JVM 也可获取注解信息，程序可以通过反射获取该注解信息。这是默认值。
     */
    metaAnnotation1()


    /**
    ========== @Target ==========

    @Target 也只能修饰注解定义，用于指定被修饰的注解能修饰哪些程序单元。
    @Target 元注解包含一个类型为AnnotationTarget 数组的allowedTargets 属性，该属性的值只能是如下几个值组成的数组。

    -Annotation Target.CLASS ： 指定该策略的注解只能修饰类。
    -AnnotationTarget.ANNOTATION_ CLASS ：指定该策略的注解只能修饰注解。
    -AnnotationTarget.TYPE_PARAMETER ：指定该策略的注解只能修饰泛型形参（目前暂时还不支持〉。
    -AnnotationTarget.PROPERTY ：指定该策略的注解只能修饰属性。
    -AnnotationTarget.FIELD ： 指定该策略的注解只能修饰宇段（包括属性的幕后宇段）。
    -AnnotationTa 「get.LOCAL_VARIABLE ：指定该策略的注解只能修饰局部变量。
    -AnnotationTarget.VALUE_PARAMETER ：指定该策略的注解只能修饰函数或构造器的形参。
    -AnnotationTarget.CONSTRUCTOR ： 指定该策略的注解只能修饰构造器。
    -AnnotationTarget.FUNCTION ： 指定该策略的注解只能修饰函数和方法（不包含构造器）。
    -AnnotationTarget. PROPERTY_ GETTER ：指定该策略的注解只能修饰属性的getter方法。
    -AnnotationTarget. PROPERTY一SETTER ：指定该策略的注解只能修饰属性的se 忧er方法。
    -AnnotationTa 咱et.TYPE ： 指定该策略的注解只能修饰类型。
    -AnnotationTarget.EXPRESSION ： 指定该策略的注解只能修饰各种表达式。
    -AnnotationTarget.FILE ： 指定该策略的注解只能修饰文件。
    -AnnotationTarget.TYPEALIAS ：指定该策略的注解只能修饰类型别名。

     */
    metaAnnotation2()

    /**
    ========== @MustBeDocumented ==========

    使用＠MustBeDocumented 元注解修饰的注解将被文档工具提取到API 文档中
    如果定义注解类时使用了＠MustBeDocumented 修饰，则所有使用该元注解修饰的程序元素的API 文档中将会【包含该注解说明】（即使用处会提取该注解信息）。

     */
    metaAnnotation3()


    /**
    ========== @Repeatable ==========

    标记可重复注解
    Kotlin 允许使用多个相同的注解来修饰同一个程序单元，这种注解称为可重复注解。
    开发可重复注解需要使用＠Repeatable 修饰

    需要说明的是，由于在Java 8 之前JVM  并不支持可重复注解， Kotlin 也没有办法突破该限制
    因此可重复注解的＠Retention 策略只能指定为AnnotationRetention.SOURCE
    这意味着可重复注解只能被Kotlin 编译器读取，接下来Kotlin 编译器会直接丢弃该注解信息。

     */
    metaAnnotation4()

}


@Retention(AnnotationRetention.RUNTIME)
annotation class MetaAnt1

fun metaAnnotation1() {

    @MetaAnt1
    class User

}

@Target(AnnotationTarget.CLASS)//只能修饰类
annotation class MetaAnt2

@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD)//只能修饰类
annotation class MetaAnt3

fun metaAnnotation2() {

    @MetaAnt2
    @MetaAnt3

    class User {
        //@MetaAnt2 //报错：MetaAnt2不能修饰成员变量
        @MetaAnt3 //MetaAnt3可以修饰
        val name: String = "rrc"
    }
}

@MustBeDocumented //程序使用＠MustBeDocumented 修饰该注解， 所以 @MetaAnt4注解将被文档工具所提取。
@Target(AnnotationTarget.FUNCTION)
annotation class MetaAnt4

fun metaAnnotation3() {

    //文档工具生成API 文档时将提取＠MetaAnt4 的使用信息。
    @MetaAnt4
    fun test() {
    }

}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@Repeatable
annotation class MetaAnt5(val name: String = "rrc", val age: Int = 12)

fun metaAnnotation4() {

    @MetaAnt5(age = 100)
    @MetaAnt5(name = "hello", age = 10)
    class User

}



