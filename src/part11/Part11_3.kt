package part11

import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions
import kotlin.reflect.full.hasAnnotation


/**
 * 十一、注解
 * 使用注解
 */
fun main() {

    /**
     *
     *
     * KAnnotatedElement（所有程序元素(KClass/KCallable/KParameter/KType)的父接口，可以通过反射获取某个程序单元对应的KAnnotatedElement对象）
     *          程序元素KAnnotatedElement的方法：annotations: List<Annotation＞
     *          程序元素KAnnotatedElement的方法：findAnnotation(): T?
     *          程序元素KAnnotatedElement的方法：hasAnnotation(): Boolean
     *      -子类 程序元素 KClass
     *      -子类 程序元素 KCallable
     *      -子类 程序元素 KParameter
     *      -子类 程序元素 KType
     *
     *  Annotation（注解，所有注解的父接口）
     *
     *
     *

    使用注解修饰类、方法、属性等成员之后，这些注解不会自己生效，必须由开发者提供相应的工具来提取并处理注解信息。

    【Annotation】: Kotlin 使用kotlin.Annotation 接口来代表程序元素前面的注解，该接口是【所有注解的父接口】。

    【KAnnotatedElement】:Kotlin 在kotlin.reflect 包下新增了 KAnnotatedElement 接口，该接口代表程序中可以接受注解的程序元素。该接口主要有如下几个实现类。
    1、KCallable ： 代表可执行的程序实体，如函数和属性。
    2、KClass ：代表Kotlin 的类、接口等类型。
    3、KParameter ： 代表函数和属性的参数。

    KAnnotatedElement 接口是所有程序元素（如KClass 、KCallable 、KParameter ）的父接口，
    所以程序通过反射获取了某个程序单元对应的KAnnotatedElement 对象（如KClass 、KCallable 、KParameter  ）之后，程序就可以调用该对象的如下属性和方法来访问注解信息。
    1、annotations: List<Annotation ＞： 该属性返回该程序单元上所有的注解。
    2、<reified T: Annotation> findAnnotation(): T ？： 根据注解类型返回该程序单元上特定类型的注解。如果该类型的注解不存在，则该方法返回null
    3、<reified T : Annotation> hasAnnotation(): Boolean ;根据注解类型判断是否含有此类型注解

    注意：
    当定义注解时使用了＠Retention(AnnotationRetention.RUNTIME)修饰，该注解才会保留到程序运行时

     */
    useAnnotation1()

    /**
    仅仅使用注解来标记程序元素对程序是不会有任何影响的，这也是注解的一条重要原则。
    为了让程序中的这些注解起作用，接下来必须为这些注解提供一个注解处理工具。

     */
    //例子1：定义一个注解，用于标记那些方法需要测试，注解处理的时候，可以通过传入泛型，来判断泛型类中哪些方法需要测试
    useAnnotation2()

    //例子2：给某个方法绑定另一个事件
    useAnnotation3()

}


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class UseAnt1(val name: String = "rrc", val age: Int = 12)

class TestAntCls1 {
    @UseAnt1("hello", 100)
    fun info() {
        println("info")

    }
}


fun useAnnotation1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    //拿到info方法的程序元素KFunction,父类其实就是KCallable
    val kFunction1 = TestAntCls1::info
    //通过KFunction（也就是KAnnotatedElement）的annotations拿到当前程序元素上的所有注解
    val annotations = kFunction1.annotations

    for (an in annotations) {
        println("$an \n")
        if (an is UseAnt1) {
            println("name = ${an.name},age = ${an.age}")
        }
    }

    val findAnnotation = TestAntCls1::info.findAnnotation<UseAnt1>()
    println("name = ${findAnnotation?.name},age = ${findAnnotation?.age}")

    val hasAnnotation = TestAntCls1::info.hasAnnotation<UseAnt1>()
    println(hasAnnotation)


}

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class NeedTest // 功能：定义一个注解，用来标记类中那些方法需要测试

class NeedTestCls {
    @NeedTest
    fun test1(str: String) {
        println("test1-${str}")
    }

    fun test2() {
        println("test2")
    }

    @NeedTest
    fun test3(str: String) {
        println("test3-${str}")
    }

    fun test4() {
        println("test4")
    }
}

fun useAnnotation2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    // 其实注解十分简单，它们就是对源代码增加的一些特殊标记
    // 这些特殊标记可通过反射获取，当程序获取到这些特殊标记后可以做l 出相应的处理（当然也可以完全忽略这些注解） 。
    checkTestable<NeedTestCls>()
}

inline fun <reified T : Any> checkTestable() {

    val cls = T::class//拿到class对象
    val functions = cls.functions//拿到所有方法
    for (f in functions) {//遍历所有方法
        val hasAnnotation = f.hasAnnotation<NeedTest>()//如果含有NeedTest注解
        if (hasAnnotation) {
            f.call(cls.createInstance<T>(), "HELLO")//方法调用，相当于java的invoke
            println("方法 ${f.name} 需要测试")
        }
    }

}


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ActionAn(val action: KClass<out ActionMethod>)
interface ActionMethod {
    fun doSth()
}

class Action1 : ActionMethod {
    override fun doSth() {
        println("---action1---")
    }

}

class Action2 : ActionMethod {
    override fun doSth() {
        println("---action2---")
    }

}

fun useAnnotation3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    processTestAction<TestActionCls>()

}

class TestActionCls {
    @ActionAn(action = Action1::class)
    fun test1(me: ActionMethod) {
        me.doSth()
    }

    @ActionAn(action = Action2::class)
    fun test2(me: ActionMethod) {
        me.doSth()
    }

}

inline fun <reified T : Any> processTestAction() {
    val functions = T::class.functions
    for (f in functions) {//遍历T内的所有方法
        if (f.hasAnnotation<ActionAn>()) {//如果存在ActionAn注解
            //找到注解
            val an = f.findAnnotation<ActionAn>()
            //找到注解的参数action
            val action = an?.action
            //f方法调用，传入注解内的action对象
            f.call(T::class.createInstance(), action?.createInstance())
        }
    }
}

