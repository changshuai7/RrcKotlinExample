@file:JvmName("RRCPart12")  //改变源文件生成的类的类名，直接生成了RRCPart12.class
@file:JvmMultifileClass  //告诉Kotlin 编译器将两个文件合并到一 个类中。
package part12

import java.io.FileOutputStream
import java.io.IOException
import kotlin.jvm.Throws


/**
 * 十二、Kotlin和Java的互调、反射
 *
 * 1、Java调Kotlin(上)
 *
 * 由于Kotlin程序编译之后本身就是完全兼容Java规范的，因此Java 调用Kotlin 也是非常方便的
 */
fun main() {

    /**

    ================== 属性编译 ==================

    Kotlin属性可编译成如下三个成员。
    1、一个private 实例变量，实例变量名与属性名相同（如果该属性具有幕后字段）
    2、一个getter 方法，方法名为属性名添加get 前缀。
    3、一个setter 方法，方法名为属性名添加set 前缀（读写属性才有setter 方法）

    但如果属性名以is 开头（属性类型并不要求一定是Boolean 类型〉， 那么该属性对应的生成规则略有差别。
    1、实例变量名、getter方法名与属性名相同。
    2、setter方法名为属性名去掉is前缀，添加set前缀。

     */
    ktCallJava1()

    /**
    ================== 包级函数 ==================

    所谓包级函数， 是指在Kotlin 中直接定义的顶级函数（不在任何类中定义）。
    但实际上，Kotlin 编译器会为整个Kotlin 源文件生成一个类（只要该源文件中包含了顶级函数、顶级变量）
    而这些顶级函数、顶级变量都会变成该类的【静态方法】、【静态变量】。

    在默认情况下， Kotlin 为包含顶级成员的源文件所生成类的类名总是文件名＋Kt 后缀： 但
    Kotlin 也允许使用＠JvmName 注解（该注解用来修饰文件本身，它是一个AnnotationRetention.SOURCE 的注解〉来改变Kotlin 编译生成的类名。
    可以放开当前类最顶的 @file:JvmName("RRCPart12") 代码，看class字节码可以观察到
     */
    ktCallJava2()

    /**
    在Java中，可以通过 RRCPart12.ktCallJava3() 来调用本方法
    参考：JavaClsTest4内的方法
     */
    ktCallJava3()

    /**
    还有一种极端情况，就是多个Kotlin 源文件要生成相同的Java 类（包名相同且类名相同，或指定了相同的＠JvmName 注解〉，这种情况默认会引发错误。
    但Kotlin 非常智能，我们可以指定Kotlin 为这些Kotlin 源文件统一生成一个Java 类，而该Java 类将会包含不同源文件中的顶级成员。
    为了实现这种效果，需要使用@JvmMultifileClass 注解。

    此处示例是将Part12_2.kt和Part12_2ex.kt合并在一起。
    在Java中，可以通过 RRCPart12.testEx() 来调用 Part12_2ex.kt 中的 testEx 方法
    参考：JavaClsTest4内的方法
     */


    /**
    ================== 实例变量 ==================

    Kotlin 允许将属性暴露成实例变量， 只要在程序中使用 ＠JvmField 修饰该属性即可
    暴露出来的属性将会和Kotlin 属性具有相同的访问权限。
    使用＠ JvmField 将属性暴露成实例变量的要求如下。
    1、该属性具有 幕后宇段。
    2、该属性必须是 非private 访问控制的。
    3、该属性不能使用 open 、override 、const 修饰。
    4、该属性不能是委托属性。

     */
    ktCallJava4()

    /**
    ================== 类变量 ==================

    在命名对象（对象声明）或伴生对象中声明的属性会在该命名对象或包含伴生对象的类中具有静态幕后宇段（类变量〉。
    但这些类变量通常是private 访问权限，程序可通过如下三种方式之一将它们暴露出来。
    1、使用 ＠JvmField 注解修饰。
    2、使用 lateinit 修饰。
    3、使用 const 修饰。
     */
    ktCallJava5()


    /**
    ================== 类方法 ==================

    Kotlin顶级函数会转换为类方法
    此外， Kotlin 还可以将 命名对象 或 伴生对象 中定义的方法转换成类方法一一如果这些方法使用＠ JvmStatic 修饰的话。
    一旦使用该注解，编译器就既会在相应对象的类中生成【类方法】，也会在对象自身中生成【实例方法】。

     */
    ktCallJava6()

    /**
    ================== 访问控制符 ==================

    Kotlin         -           Java

    private                    -private
    protected/internal/public  -public

     */

    /**
    ================== Java获取KClass ==================
    使用Kotlin 来获取Java 的Class 对象
    反过来，有时候 Java 也需要获取 Kotlin的KClass 对象
    在Java 中获取 Kotlin 的 KClass 对象，必须通过调用Class<T>.kotlin 扩展属性的等价形式来实现。

     */
    ktCallJava7()

    /**
    ================== 使用 @JvmName解决签名冲突 ==================

    有些时候，在Kotlin 中要定义两个同名函数，但Jvm平台无法区分这两个函数。
    典型的情况就是类型擦除引起的问题。
    可以使用@JvmName("xxx) 来决定同名函数冲突的问题

     */
    ktCallJava8()
    ktCallJava9()

    /**

    ================== 生成重载 ==================

    Kotlin 为方法（或函数）提供了参数默认值来避免函数重载过多的问题。
    但对于这种参数有默认值的方法（或函数），编译器默认只生成一个方法：【默认带所有参数的方法】
    为了让编译器能为带参数默认值的方法（或函数）生成多个重载的方法（或函数），可考虑使用＠JvmOverloads 注解。
    需要说明的是，＠JvmOverloads 注解也适用于构造器、静态方法等。
    但是它不适用于抽象方法，包括在接口中定义的方法。

     */
    ktCallJava10()

    /**
    ================== check异常 ==================

    Kotlin 没有checked 异常，因此Kotlin 也无须使用throws 抛出异常。这
    样即使Kotlin 的方法（或函数〉本身可能抛出checked 异常， Java 调用该方法（或函数）时编
    译器也不会检查该异常。

    如果我们希望Java 调用该函数时编译器会检查该check异常 ，则可使用＠Throws 注解修饰该函数。
     */
    ktCallJava11()

    /**
    ================== 泛型的型变 ==================
    Kotlin支持声明处型变、使用处型变
    Java只支持使用处型变（通过通配符形式，可以支持协变和逆变两种形式）
    因此，对于Kotlin 的声明处型变，必须转换成Java 的使用处型变。

    1、对于协变类型的泛型类Bar<out T＞，当它作为参数出现时， Kotlin 会自动将Bar<Base>类型的参数替换成Bar<? extends Base＞。
    2、对于逆变类型的泛型类Foo<in T＞，当它作为参数出现时， Kotlin 会自动将Foo<Sub>类型的参数替换成Foo <? super Sub ＞。
    3、不管是协变类型的泛型类Bar<out T> ，还是逆变类型的泛型类Foo<in T＞ ， 当它作为返回值出现时， Kotlin 不会生成通配符。

     */
    ktCallJava12()


}


fun ktCallJava1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val cls = TestCls1()

}

class TestCls1 {
    var name: String = ""
    var isGood: Boolean = false

    //可以看到编译成Java成如下：

    //  @NotNull
    //   private String name = "";
    //   private boolean isGood;
    //
    //   @NotNull
    //   public final String getName() {
    //      return this.name;
    //   }
    //
    //   public final void setName(@NotNull String var1) {
    //      Intrinsics.checkNotNullParameter(var1, "<set-?>");
    //      this.name = var1;
    //   }
    //
    //   public final boolean isGood() {
    //      return this.isGood;
    //   }
    //
    //   public final void setGood(boolean var1) {
    //      this.isGood = var1;
    //   }
}

const val hello = "hello world"
fun ktCallJava2() {
    println(hello)
    /**
     * 比如当前类会直接编译成如下：
     * 会编译成一个Part12_2Kt.class的文件，顶级成员就成为了Part12_2Kt.class中的静态成员
     * */
    // public final class Part12_2Kt {
    //      public static final String hello = "hello world";
    //      public static final void ktCallJava1() { ... }
    //      public static final void ktCallJava2() { ... }
    // }

}

fun ktCallJava3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    println("我被调用了！")
}


fun ktCallJava4() {
    //在 JavaClsTest5 中可直接调用 TestFieldCls 的 name
    //       TestFieldCls cls = new TestFieldCls("hello");
    //       System.out.println(cls.name);

}

class TestFieldCls(name: String) {
    @JvmField
    val name = name
}


fun ktCallJava5() {

    //在 JavaClsTest5 中可直接通过类名来调用 TestFieldCls 的 city/province/constTest
}


class TestFieldCls1(val name: String) {

    //伴生对象本来 就是用来弥补Katlin 没有static 关键字的不足
    companion object {

        var age: Int = 10

        /**使用@JvmField*/
        // 相当于类变量,但是类变量转成java以后，但是用private修饰的
        // 使用了＠JvmField 修饰，这样该类变量就变成了与该属性具有相同的访问控制符： public 。
        @JvmField
        var city = "北京"

        /**使用@lateinit*/
        // 在命名对象或伴生对象中的延迟初始化属性( lateinit ）具有与该属性的setter方法相同的 访问控制符的类变量。
        // 道理很简单： Java 并不支持命名对象，因此Kotlin 的命名对象在Java 中其实表现为一个单例类，命名对象的属性就变成了类变量：
        // 又由于lateinit 属性需要等到使 用时才赋值，因此Kotlin 必须将该类变量暴露出来一一否则Java 程序将无法对该类变量赋值。
        lateinit var province: String

        /**使用const*/
        //注意：const只能定义在顶层、或者对象申明中。
        // 在Kotlin 程序中使用const 修饰的属性，不管是在顶层定义的属性，还是在对象中 定义的属性
        // 只要使用了const 修饰，它就会变成有 public static final 修饰的类变量。

        //下面的实例中
        // ①定义了一个顶层的const 属性：constTest2
        // ②还在命名对象中定义了一个const 属性：constTest3
        // ③在伴生对象中定义了一个const 属性：constTest1
        // 这些const 属性都会变成有public static final 修饰的属性。

        const val constTest1 = "hello"

    }
}

const val constTest2 = "hello"//顶层定义，相当于静态变量

object ConstObj {
    //对象申明，相当于一个单例
    const val constTest3 = "hello"
}


fun ktCallJava6() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
}

fun clsFun1() {
    println("clsFun1")
}

class TestObjFunCls {
    companion object {
        @JvmStatic
        fun clsFun2() {
            println("clsFun2")
        }
    }
}

object ObjCls {
    @JvmStatic
    fun clsFun3() {
        println("clsFun3")
    }
}

fun ktCallJava7() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    //查看JavaClsTest5中方法：
    //            Class<?> aClass = Class.forName("java.util.ArrayList");
    //            System.out.println(aClass);
    //            KClass<?> kClass = JvmClassMappingKt.getKotlinClass(aClass);
    //            System.out.println(kClass);

}

fun ktCallJava8() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    // 由于编译器编译之后会产生类型擦除，因此List<String>.listEx()/List<Int>.listEx()两个方法的签名都是 List.listEx()
    // 所以在Jvm平台上无法区分这两个方法。
    // 为了让JVM平台能区分这两个方法，程序中使用了＠ JvmName("listExInt") 来标注后一个方法
    // 因此后一个方法在JVM平台上会编译成listExInt()方法。
    val strList = arrayListOf<String>("a", "b", "c")
    val intList = listOf<Int>(1, 2, 3)
    strList.listEx()
    intList.listEx()
    //intList.listExInt() //错误

    //kotlin调用依然是按照方法名来调用扩展方法，但是在Java中就需要根据jvmName指定的名称来调用了。
    //可以查看JavaClsTest5中的方法调用：

    //        List<String> list1 = new ArrayList<String>() ;
    //        List<Integer> list2 = new ArrayList<Integer>() ;
    //        RRCPart12.listEx(list1);
    //        RRCPart12.listExInt(list2);

}

fun List<String>.listEx() {
    println("这是泛型为String 的 listEx 方法")
}

@JvmName("listExInt")
fun List<Int>.listEx() {
    println("这是泛型为Int 的 listEx 方法")
}

fun ktCallJava9() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    //@JvmName 注解也适用于属性x 和函数getX（）共

    //由于X这个属性，在jvm中会生成getX的方法，所以在类中就不能定义getX的方法
    //所以可以通过jvmName来重新命名。
    //setter方法也同理
    class User {
        var X: Int = 10
            @JvmName("getX_prop") get

        fun getX(): Int {
            return 100
        }
    }

    val u = User()
    println(u.getX())
    println(u.X)
}

fun ktCallJava10() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    //可以直接查看testReLoad方法对应的 Kotlin 的 bytecode
    //在不用JvmOverloads注解的情况下，只有一个方法。加上JvmOverloads注解后，生成了三个重载方法。

    /**
    有一点需要指出：如果一个类的所有构造器的参数都有默认值，这样就能以构造参数的默认值来调用构造器（就像调用无参数的构造器）
    因此在JVM平台上相当于生成一个public的无参数构造器，这个功能不需要＠JvmOverloads 注解。
     */
}

@JvmOverloads
fun testReLoad(name: String, age: Int, city: String = "北京", phone: Int = 10086) {
    println("name = $name,age = $age,city = $city,phone=$phone")
}

fun ktCallJava11() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    //testCheckException()
}


@Throws(IOException::class)
fun testCheckException() {

    FileOutputStream("")

//注解Throws修饰，让编译器必须检查testCheckException异常。这样在java中调用的时候，就必须申明或者捕获了
//查看JavaClsTest5中的检查异常的代码：
//        try {
//            RRCPart12.testCheckException();
//        } catch (IOException e) {
//            System.out.println("出现异常: " + e.getMessage());
//        }

}

fun ktCallJava12() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    box(Zi())

    unbox(Box(Fu()))
    unbox(Box(Zi()))
}

open class Fu
open class Zi : Fu()
open class Box<out T>(val info: T) //定义一个声明处型变的协变类

//使用Box<T>作为返回值类型
//编译成Java：public static final Box<Zi> box(Zi)
//解释：协变类当它作为返回值出现时， Kotlin 不会生成通配符。
fun box(value: Zi): Box<Zi> {
    return Box(value)
}

//使用Box<T>作为参数类型
//变异成Java：public static final Fu unbox(Box<? extends Fu>)
//解释：编译器自动使用了运行处协变来代替。所以作为参数的时候，可以传入Box<Fu>或者Box<Zi>都行。
fun unbox(fu: Box<Fu>): Fu {
    return fu.info
}

/**
除Kotlin 默认的转换规则之外， Kotlin 还可使用注解控制是否生成通配符。
Kotlin 提供了如下两个注解:
1、＠JvmWildcard ： 该注解可指定在编译器默认不生成通配符的地方强制生成通配符。
2、＠JvmSuppressWildcards ： 该注解可指定在编译器默认生成通配符的地方强制不生成通配符。

注意：
@JvmSuppressWildcards 注解不仅可修饰单个泛型形参，还可修饰整个声明（如函数或类），从而阻止编译器为整个类或函数中的声明处型变生成通配符（使用处协变）。
 */

//对返回值强制生成通配符
//public static final Box<? extends Zi> box1(Zi)
fun box1(value: Zi): Box<@JvmWildcard Zi> {
    return Box(value)
}

//强制对参数不生成通配符
//public static final Fu unbox1(Box<Fu>)
fun unbox1(fu: Box<@JvmSuppressWildcards Fu>): Fu {
    return fu.info
}
















