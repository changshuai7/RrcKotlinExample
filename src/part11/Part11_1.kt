package part11

import kotlin.reflect.KClass


/**
十一、注解
Kotlin 的注解与Java 的注解完全相同，也是代码里的特殊标记
这些标记可以在编译、类加载、运行时被读取，并执行相应的处理。
通过使用注解，程序开发人员可以在不改变原有逻辑的情况下，在源文件中嵌入一些补充信息。
代码分析工具、开发工具和部署工具都可以通过这些补充信息进行验证或者进行部署。
通过在程序中添加注解来为程序提供一些额外的补充信息，这些补充信息通常是某种配置文件
以前往往使用XML 来完成，现在很多地方会采用注解来代替XML 。
而Kotlin 也提供了一些API 来读取、使用程序中的注解信息，

 */
fun main() {

    /**
    ====注解定义====

    1、Kotlin 使用annotation class 关键字（就像使用enum class 定义枚举类一样）定义注解
    2、Kotlin 不允许为注解定义注解体，也就是说，注解后面不能有花括号。
    3、定义了该注解之后，就可以在程序的任何地方使用该注解。通常可用于修饰程序中的类、方法、属性、接口等定义。通常会把注解放在所有修饰符之前
    4、如果要用注解来修饰主构造器，就像前面所介绍的，程序必须为主构造器添加constructor关键字
     */
    annotation1()

    /**
    ====注解的属性和构造器====

    1、注解还可以带属性， 由于注解没有注解体，因此注解的属性只能在注解声明部分指定。实际上，相当于在注解的主构造器中指定注解的属性。
    2、注解的属性值只能在使用时指定； 井且一旦为注解的属性指定了属性值，以后就绝对不会改变其属性值，因此注解的属性只能定义为只读属性。
    3、使用annotation class 定义的注解其实就相当于定义了一个注解接口，这个注解接口继承了kotlin .Annotation 接口。
    4、需要说明的是，注解的属性不能使用可空类型（不能在类型后添加“？”），这是因为JVM本身不允许使用null 作为注解的属性值。
    5、注解的属性可支持如下类型。
    --》对应于Java 基本类型的类（如Int、Long 等）
    --》字符串
    --》类（如Foo::class )
    --》枚举
    --》其他注解
    --》上面各种类型的数组
     */
    annotation2()

    /**
    ====注解的默认值====

    可以在定义注解的属性时使用等号（＝）为其指定初始值（默认值）（就像定义类时在主构造器中为类的属性指定初始值一样）
    注解的初始值只能是【编译时常量】
     */
    annotation3()

    /**
    ====注解的分类====

    根据注解是否可以包含属性，可以把注解分为如下两类。
    1、标记注解： 没有定义属性的注解被称为标记注解。这种注解仅利用自身的存在与否来提供信息，如前面所介绍的＠TestAnt 等注解。
    2、元数据注解： 包含属性的注解被称为元数据注解。因此它们可以接受更多的配置信息（以属性值的方式进行设置） 。如前面所介绍的＠TestAnt1、＠TestAnt2 等注解。
     */

    /**
    与Java 类似的是，如果注解的属性名为value ，则为value 属性指定属性值时可省略属性名
     */
    annotation4()

    /**
    如果将一个注解作为另一个注解的属性值，那么在使用注解时不需要以＠作为前缀。
     */
    annotation5()

    /**
    如果需要将一个Class作为注解的属性，请使用Kotlin 类（ KClass),
    Kotlin 编译器会自动将其转换为Java 的Class，以便Java 代码能够正常看到该注解和参数。
     */
    annotation6()


    /**
     *使用vararg修饰的属性【注意：不是varargs】，不管它的属性名是什么，系统为该属性指定属性值时，都不需要指定属性名，直接在注解中指定多个属性值即可
     */
    annotation7()


}


//定义一个最简单的注解
annotation class TestAnt

@TestAnt
fun annotation1() {

    @TestAnt
    class User @TestAnt constructor(@TestAnt val name: String, val age: Int) {
        @TestAnt
        fun test() {
            println("test")
        }
    }
}

//定义一个带属性的注解
annotation class TestAnt1(val name: String, val age: Int)

fun annotation2() {

    //使用带属性的注解时，需要为属性指定属性值
    @TestAnt1("hello", 12)
    class User

    @TestAnt1(name = "hello", age = 12)
    class User1

    @TestAnt1(age = 12, name = "hello")
    class User2
}

//定义一个带属性的注解,并设定了默认值
annotation class TestAnt2(val name: String = "rrc", val age: Int = 100)

fun annotation3() {

    //使用带属性的注解
    //因为它的属性有默认值，所以可以不为该属性指定值
    @TestAnt2
    class User
}

annotation class TestAnt3(val value: String = "rrc")

fun annotation4() {

    //为value 属性指定属性值时无须带属性名
    @TestAnt3("hello")
    class User
}


annotation class TestAnt4(val value: String)
annotation class TestAnt5(val name: String, val an: TestAnt4)

fun annotation5() {
    @TestAnt5("hello", TestAnt4("hello"))
    class User
}

annotation class TestAnt6(val cls1: KClass<*>, val cls2: KClass<out Number>)

fun annotation6() {
    @TestAnt6(Integer::class, Double::class)
    class User
}

annotation class TestAnt7(vararg val info: String)

fun annotation7() {
    @TestAnt7("a", "b", "c")
    class User
}



