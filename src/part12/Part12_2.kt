@file:JvmName("RRCPart12")  //改变源文件生成的类的类名，直接生成了RRCPart12.class
@file:JvmMultifileClass  //告诉Kotlin 编译器将两个文件合并到一 个类中。
package part12

/**
 * 十二、Kotlin和Java的互调、反射
 *
 * 1、Java调Kotlin
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
     12.2.3  p294

     */

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
