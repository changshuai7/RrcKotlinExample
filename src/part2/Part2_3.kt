package part2


fun main() {

    /**
     * 【字符串】String
     *
     * Kotlin中的String和Java中的String并非同一个类
     *
     */

    /**
     * 字符串中的每一个字符都由独立的Unicode字符组成
     * String允许通过形如s[i］ 的格式来访问字符串指定索引处的字符， 也可通过for循环遍历字符串中的每一个字符。
     */
    strFun1()

    /**
     *Kotlin的字符串有两种字面值（ Literal ），分别如下。
     * 转义字符串： 转义字符串可以有转义字符，转义字符串很像Java 字符串。
     * 原始字符串：原始字符串可以包含换行和任意文本。原始字符串需要用3 个引号引起来。即：原始字符串保留原格式
     *
     * 缩进：
     * Katlin 可以使用竖线（｜）作为边界符，通过trimMargin()方法来去除（｜）之前的缩进。
     * Katlin 可以使用自定义的字符（x）作为边界符，通过trimMargin(x)方法来去除（x）之前的缩进。
     */
    strFun2()


    /**
     * Kotlin 的String 与Java 的String 并不是同一个类，因此它们的方法略有不同
     * 但是这两个类所提供的功能大致相似。实际上， Kotlin 的String 类提供了更多的方法，
     */
    strFun3()


    /**
     * 【类型别名】
     * Kotlin 提供了类似于C 语言中的typedef 的功能： 可以为己有的类型指定另一个可读性更强的名字。
     * Kotlin 提供了typealias 来定义类型别名。typealias 语句的语法格式为：
     *
     * [typealias 类型别名 = 已有类型]
     */
    typeAliasFun()
}


fun strFun1() {

    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val str = "rrc.com"
    println(str[0])
    for (i in str) {
        print("$i ")
    }

}

fun strFun2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val str1 = "rrc.com"

    val str2 = """ 
            hello
            
        world
    """

    val str3 = """
                    |hello
        |world
    """.trimMargin()

    val str4 = """
                    哈哈hello
        哈哈world
        
    """.trimMargin("哈哈")

    println(str1)
    println(str2)
    println(str3)
    println(str4)
}


fun strFun3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    /**
     * 1、提供了一系列toXxx()方法将字符串转换成数值。
     */
    val str1 = "1.23"
    val d = str1.toDouble()
    println(d)

    /**
     * 2、将字符串首字母大写、小写的方法。
     */
    val str2 = "abc"
    val cap = str2.capitalize()//首字母大写
    println(cap)
    val deCap = cap.decapitalize()//首字母小写
    println(deCap)

    /**
     * 3、相同前缀后缀
     */
    val str3 = "rrc_fly"
    val commonPrefixWith = str3.commonPrefixWith("rrc_good")
    val commonSuffixWith = str3.commonSuffixWith("haha_fly")
    println("$commonPrefixWith === $commonSuffixWith")

}


fun typeAliasFun() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val m = M("rrc")
    m.showName()

    val list = MyList()
    list.add(m)
    println(list[0].name)

    var a: AI
    var b: BI

    val p: F<String> = {
        it.length > 1
    }
    val filter = arrayOf("a", "b", "cd").filter(p)
    println(filter)

}

/**
 * 如果类型名太长，我们可以使用较短的新名称来替代原类型名
 */
//代替长函数的类型
typealias M = MyClassWithAAndBAndC
typealias MyList = ArrayList<MyClassWithAAndBAndC>//点进去，Kotlin中有很多代码直接引用的是Java中的
//代替内部类的类型
typealias AI = A.AInner
typealias BI = B.BInner
//代替Lambda表达式类型
typealias F<T> = (T) -> Boolean

class MyClassWithAAndBAndC(var name: String) {
    fun showName() {
        println(name)
    }
}

class A {
    inner class AInner
}

class B {
    inner class BInner
}
