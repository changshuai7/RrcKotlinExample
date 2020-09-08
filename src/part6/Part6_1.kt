package part6

import java.lang.Exception

/**
 * 【6、函数和lambda表达式】
 *
 * Kotlin 对Java 的纯粹面向对象进行了弥补，增加了函数式编程的支持，这样提高了编程的灵活性。
 * Kotlin 融合了面向过程语言和面向对象语言的特征，因此Kotlin 完全支持定义函数、调用函数。
 * Kotlin 的函数比C 语言的函数更加强大， Kotlin 支持局部函数（实际上局部函数是Lambda 表达式的基础）。
 * 对于Kotlin 开发者而言，函数是一个非常重要的知识点， 尤其是对于普通的Java 程序员来说， Kotlin 的函数可能是最需要花精力来掌握的内容。
 * 与函数紧密相关的另一个知识点是Lambda 表达式。Lambda 表达式可作为表达式、函数参数或函数返回值，因此使用Lambda 表达式可以让程序更加简洁。
 */
fun main() {

    /**
     * 一、函数的定义
     * fun 函数名（形参列表）［：返回值类型］{
     *      由单条到多条可执行语句组成的函数
     * }
     *
     */


    /**
     * 返回值类型：
     * 可以是Kotlin 语言所允许的任何数据类型
     * 如果声明了函数返回值类型，则函数体内应该有一条有效的return语句，
     * 该语句返回一个变量或一个表达式，这个变量或者表达式的类型必须与此处声明的类型匹配。
     *
     * 如果希望声明一个函数没有返回值，则有如下两种声明方式。
     * 1、省略“：返回值类型”部分。
     * 2、使用“： Unit，指定返回Unit 代表没有返回值。
     * Kotlin的Unit相当于Java的void
     */

    /**
     * 形参列表：
     * 形参列表用于定义该函数可以接受的参数
     * 形参列表由零组到多组“形参名：参数类型”组合而成，多组参数之间以英文逗号（，）隔开，形参名和形参类型之间以英文冒号隔开。
     */
    fun11()
    fun12()
    fun13()
    fun14("rrc", 12)


    /**
     * 二、但表达式函数
     * 在某些情况下，函数只是返回单个表达式，此时可以省略花括号井在等号（＝）后指定函数体即可。
     * 这种方式被称为单表达式函数。
     * 对于单表达式函数而言，编译器可以推断出函数的返回值类型，因此Kotlin 允许省略声明函数的返回值类型
     */
    println(fun21(1, 2))
    println(fun22(1, 2))

    /**
     * 三、函数的形参
     * Kotlin 函数的参数名不是无意义的， Kotlin 允许调用函数时通过名字来传入参数值。
     * 调用该函数时，
     * 1、既可使用传统的根据位置参数来调用
     * 2、也可根据命名参数来调用，并可交换参数的位置
     * 3、还可混合使用命名参数和位置参数
     *   3.1、-->如果希望调用函数时混合使用命名参数和位置参数，那么【命名参数必须位于位置参数之后】。换句话说，在命名参数之后的只能是命名参数
     *   相当于必须 分为两部分：位置参数（前），命名参数（后），否则会造成参数混乱
     */
    println("面积1为:${fun31(width = 10.0, height = 20.0)}")
    println("面积2为:${fun31(height = 20.0, width = 10.0)}")
    println("面积3为:${fun31(20.0, height = 10.0)}")

    /**
     * 四、形参默认值
     *
     * 在某些情况下，程序需要在定义函数时为一个或多个形参指定默认值
     * 这样调用函数时就可以省略该形参，而直接使用该形参的默认值。
     * 为形参指定默认值的语法格式如下：
     * [ 形参名： 形参类型＝默认值 ]
     * 形参如果有默认值，调用的时候可以不传入，这时会取默认值。
     * 形参如果没有默认值，调用的时候必须穿入，否则会报错，以为形参没有初始化
     */

    println(fun41(age = 12))//name没有指定值，会使用默认值
    println(fun41(name = "GuaZi", age = 12))
    println(fun41("GuaZi", age = 12))

    /**
     * 通过为函数形参指定默认值
     * 比如String 的
     * substring(start， end ）： 截取字符串从start 到end 之间的子串。
     * substring(stat）： 截取字符串从start 到结束处的子串。
     *
     * 可以合并为一个方法：CharSequence.substring(startIndex: Int, endIndex: Int = length): String
     */

    /**
     * 如果在定义函数之后将有默认值的参数放在普通参数的前面，并且不想为默认参数重新传入参数值，
     * 那么就只能使用命名参数为其他参数传入参数值。比如上述的fun42
     *
     * 如果将带默认值的参数定义在无默认值的参数之前，那么想为默认参数使用默认值时，【只能使用命名参数为无默认值的参数传入参数值】
     * 出于这种考虑 ==>【Kotlin 建议将带默认值的参数放在函数形参列表的后面】
     */
    println(fun42("111", "222"))
    println(fun42(name1 = "111", name2 = "111"))
    //println(fun42("111"));//报错，"111"会作为第一个参数传给name1，那么name2将无法被赋值
    println(fun42(name2 = "222"))

    println(fun43("abc"))
    println(fun43(name1 = "111", "abc"))

    /**
     * 五、个数可变形参
     * Kotlin 允许定义个数可变的参数，从而允许为函数指定数量不确定的形参。
     * 如果在定义函数时，在形参的类型前添加【vararg】修饰，则表明该形参可以接受多个参数值，多个参数值被当成数组传入（本质是数组）。
     */
    println(fun51("rrc1", "rrc2", "rrc3"))
    /**
     * kotlin允许个数可变的形参可以处于形参列表的任意位置（不要求是形参列表的最后一个参数）
     * 但Kotlin 要求一个函数最多只能带一个个数可变的形参。
     */
    //由于names可以接受个数不等的参数值，所以，如果需要给后面的age参数传入参数值，则必须使用命名参数。
    println(fun52("rrc11", "rrc22", "rrc33", age = 12))
    println(fun53(12, "rrc11", "rrc22", "rrc33"))

    /**
     * 如果我们己有一个数组，程序希望将数组的多个元素传给个数可变的参数，
     * 则可以在传入的数组参数前添加“＊”运算符
     */
    val arr = arrayOf("a", "b", "c")
    println(fun54(*arr))

    /**
     * 【总之一点】：
     * kotlin中的参数分为：命名参数 和 位置参数
     * kotlin对于参数都是按照方法内的顺序依次来填入的，填入类型错误则直接报错，而命名参数由于知道了为哪个参数赋值，所以可以跳到此位置的参数赋值。
     * 但是最终，所有参数都必须被初始化才可以。否则会报错。
     */

    /**
     * 六、函数的重载
     * 同java，核心的一点，系统在调用函数的时候，可以区分，即可以知道调用的是哪个函数就可以。否则无法重载。比如方法名、形参、都相同，但是返回值不同，不能称作是重载
     */
    println("重载函数:${fun61("rrc")}")
    println("重载函数:${fun61("rrc", 12)}")

    /**
     * 如果被重载的函数中包含了个数可变的形参,则要格外注意
     */
    println("可变参数的重载函数:${fun62()}")
    println("可变参数的重载函数:${fun62("abc", "def")}")

    // 虽然这行代码既可匹配fun62(String）函数，也可匹配fun62(varargs String）函数，但Kotlin 会尽量执行最精确的匹配
    // 因此Kotlin 会这样调用匹配fun62(String ）函数。
    println("可变参数的重载函数:${fun62("abc")}")

    //注意：大部分时候并不推荐重载形参个数可变的函数，因为这样做确实没有太大的意，而且容易导致错误


    /**
     * 七、局部函数
     * 前面所看到的函数都是在全局范围内定义的，它们都是全局函数。
     * Kotlin 还支持在函数体内部定义函数，这种被放在函数体内定义的函数称为局部函数。
     * 在默认情况下，局部函数对外部是隐藏的，局部函数只能在其封闭（ enclosing ）函数内有效，其封闭函数也可以返回局部函数，以便程序在其他作用域中使用局部函数。
     */


    fun71()

    /**
     * 如果封闭函数没有局部函数返回，那么局部函数将只能在封闭函数内部调用，如上面程序所示。
     * 另外，还会出现一种情况，如果封闭函数将局部函数返回，且程序使用变量保存了封闭函数的返回值，那么这些局部函数的作用域就会被扩大，
     * 因此程序完全可以自由地调用它们，就像它们都是全局函数一样。
     */
    fun72()

    /**
     * 八、高阶函数
     * Kotlin 不是纯粹的面向对象语言， Kotlin 的函数也是一等公民，因此函数本身也具有自己的类型。
     * 函数类型就像前面介绍的数据类型一样，既可用于定义变量，也可用作函数的形参类型，还可作为函数的返回值类型。
     */
    /**
     * Kotlin 的每个函数都有特定的类型，函数类型由函数的【形参列表】、【-＞】 、【返回值类型】组成。例如如下函数
     * fun myFun(name:String,age:Int):Boolean {}
     * 那么这个函数的类型为：(String,Int)->Boolean
     *
     * fun myFun(name:String,age:Int) {}
     * 那么这个函数的类型为：(String,Int)->Unit
     */

    fun81()

    /**
     * 定义了函数类型的变量之后，接下来程序即可将函数赋值给该变量，通过符号::来操作
     */
    fun82()

    /**
     * 九、使用函数作为形参类型
     */
    fun91()


}




fun fun11(): String {
    return "1"
}

fun fun12(): Unit {
    //return Unit
}

fun fun13() {
    //return Unit
}

fun fun14(name: String, age: Int): Boolean {
    return false
}

fun fun21(x: Int, y: Int): Int = x + y
fun fun22(x: Int, y: Int) = x + y


fun fun31(width: Double, height: Double) = width * height

fun fun41(name: String = "Rrc", age: Int) = "${name}的年龄为${age}岁"

fun fun42(name1: String = "RrcName1", name2: String) = "name1=$name1 -- name2=$name2"

fun fun43(name1: String, name2: String = "RrcName2") = "name1=$name1 -- name2=$name2"

fun fun51(vararg names: String): String {
    val buff = StringBuffer()
    for (item in names) {
        buff.append("$item ")
    }
    return buff.toString()
}

fun fun52(vararg names: String, age: Int): String {
    val buff = StringBuffer()
    for (item in names) {
        buff.append("$item ")
    }
    return "$buff age = $age"
}

fun fun53(age: Int, vararg names: String): String {
    val buff = StringBuffer()
    for (item in names) {
        buff.append("$item ")
    }
    return "$buff age = $age"
}


fun fun54(vararg names: String): String {
    val buff = StringBuffer()
    for (item in names) {
        buff.append("$item ")
    }
    return buff.toString()
}


fun fun61(name: String, age: Int): String {
    return "$name 的年龄为 $age"
}

fun fun61(name: String): String {
    return name
}


fun fun62(name: String): String {
    return "1 --> $name";
}

fun fun62(vararg names: String): String {
    val buff = StringBuffer()
    for (item in names) {
        buff.append("$item ")
    }
    return "2 --> $buff"
}


fun fun71() {

    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    println(getMathResult("plus", 10, 2))
    println(getMathResult("minus", 10, 2))
    println(getMathResult("times", 10, 2))
    println(getMathResult("div", 10, 2))
}

fun getMathResult(type: String, x: Int, y: Int): Int {

    fun plus(x: Int, y: Int) = x + y
    fun minus(x: Int, y: Int) = x - y
    fun times(x: Int, y: Int) = x * y
    fun div(x: Int, y: Int) = x / y

    return when (type) {
        "plus" -> plus(x, y)
        "minus" -> minus(x, y)
        "times" -> times(x, y)
        "div" -> div(x, y)
        else -> -1
    }

}


fun fun72() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val mathFunc = getMathFunc("plus")
    println(mathFunc(10, 2))
}

fun getMathFunc(type: String): (Int, Int) -> Int {
    val plus = fun(x: Int, y: Int) = x + y
    val minus = fun(x: Int, y: Int) = x - y
    val times = fun(x: Int, y: Int) = x * y
    val div = fun(x: Int, y: Int) = x / y
    return when (type) {
        "plus" -> plus
        "minus" -> minus
        "times" -> times
        "div" -> div
        else -> throw Exception("type错误")
    }
}


fun fun81() {
    val f1: (String, Int) -> String
    val f2: () -> Unit
}


fun fun82() {

    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val f1: (String, Int) -> String
    val f2: () -> Unit

    fun ff1(name: String, age: Int) = "name=$name,age=$age"
    fun ff2() {
        println("我是ff2")
    }
    //－只要被赋值 的函数ff1/ff2类型与f1/f2 的变量类型一致，程序就可以赋值成功。
    f1 = ::ff1
    f2 = ::ff2

    println(f1("rrc", 12))
    f2()
    /**
     * 当直接访问一个函数的函数引用，而不是调用函数时，【需要在函数名前添加两个冒号】，而且不能在函数后面添加圆括号
     * 旦添加圆括号，就变成了调用函数，而不是访问函数引用。
     * 除此之外，程序还可使用函数类型作为形参类型和返回值类型。
     */
}

fun fun91() {
    //p113 , 6.5.2
}























