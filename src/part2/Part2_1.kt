package part2


fun main() {

    /**
     * 【一、Kotlin的注释】
     *
     * Java语言的多行注释不支持嵌套,Kotlin语言的多行注释支持嵌套
     */

    nodes()


    /**
     * 【二、Kotlin的变量】
     *
     *常量与变量的区别是：常量一旦保存某个数据之后，该数据就不能发生改变：但变量保存的数据则可以多次发生改变，只要程序对变量重新赋值即可。
     */
    /**
     * 2.1、分隔符
     * Kotlin 语言里的分号（；）、花括号（｛｝）、方括号（［］）、圆括号（（））、空格、圆点（．）都具有特殊的分隔作用，因此被统称为分隔符。
     */
    /**
     * 2.1.1、分号
     * 与Java 不同，Kotlin 并不强制要求每条语句必须以分号结尾，当然也可以使用分号表示语句结束。
     * 但如果打算在同一行内书写多条独立的语句，则前面语句需要使用分号表示结束。
     */
    division()

    /**
     * 2.1.2、花括号
     * 花括号的作用就是定义一个代码块。一个代码块指的就是“｛”和“｝”所包含的一段代码，代码块在逻辑上是一个整体。
     * 花括号一般是成对出现的，有一个“｛” 则必然有一个“｝”，反之亦然
     */

    /**
     * 2.1.3、方括号
     * Kotlin 的方括号是一个索引运算符，其主要作用是用于访问数组元素、List集合元素和Map 集合元素。
     * 方括号通常紧跟数组变量名、List变量名或Map变量，
     * 而在方括号里指定希望访问的数组元素、List元素的索引或Map 元素的key 。
     *
     * 关于方括号分隔符的用法，后面还会有更进一步的介绍
     */

    squareBrackets()


    /**
     * 2.1.4、圆括号
     * 圆括号是一个功能非常丰富的分隔符，在定义函数、方法时必须使用圆括号来包含所有的形参声明，
     * 在调用函数时也必须使用圆括号来传入实参值。不仅如此，圆括号还可以将表达式中某个部分括成一个整体，保证这个部分优先计算。
     */

    /**
     * 2.1.5、空格
     *Kotlin 语言使用空格分隔一条语句的不同部分。Kotlin 语言是一门格式自由的语言，所以空格几乎可以出现在Kotlin 程序的任何地方，
     * 也可以出现任意多个空格，但不要使用空格把一个变量名隔开成两个， 这将导致程序出错。
     * Kotlin 语言中的空格包含空格符（ Space ）、制表符（ Tab ）和回车符（Enter ） 等。
     * 除此之外， Kotlin 源程序还会使用空格合理地缩进代码，从而提供更好的可读性。
     */

    /**
     * 2.1.6、原点
     * 圆点（．）通常用作类、结构体、枚举、实例和它的成员（包括属性和方法）之间的分隔符，表明调用某个类或某个实例的指定成员。
     * 关于圆点分隔符的用法，后面还会有更进一步的介绍， 此处不再赘述。
     */

    /**
     * 2.2、标识符
     * 所谓标识符， 就是用于给程序中的变量、类、枚举、函数等命名的名字。标识符的长度没有限制。
     * 同Java
     * Kotlin语言是区分大小写的， 因此abc 和Abe 是两个不同的标识符。
     * 在使用标识符时，需要注意如下规则。
     *  -标识符可以由字符、数字和下画线（ _ ）组成，但不能以数字开头。此处的字符并不局限于26个英文字母，可以包含中文字符、日文字符等。
     *  -标识符不能是Kotlin 的硬关键字，在特定上下文中不能使用软关键字和修饰符关键字，但可以包含关键字。
     *  -标识符不能包含空格。
     *  -标识符只能包含下画线（ _ ），不能包含＠ 、＃等特殊字符。
     */

    /**
     * 2.3、Kotlin关键字
     * 在Kotlin语言中有一些具有特殊用途的单词被称为关键宇（ keyword ），当定义标识符时，不要让标识符与关键宇相同，否则将引起错误
     *  -硬关键字： 这些关键字无论在什么情况下都不能用作标识符。
     *  -软关键字： 这些关键字可以在它们不起作用的上下文中用作标识符。
     *  -修饰符关键字： 这些关键字也可以在代码中用作标识符。
     */

    /**
     * 2.4、申明变量
     * Kotlin 是强类型的语言， Kotlin 要求所有的变量必须先声明、后使用，
     * 声明变量时必须显式或隐式指定变量的类型
     * 类型限制了一个变量能被赋的值，也限制了一个表达式可以产生的值，还限制了在这些值上可以进行的操作，井确定了这些操作的含义。
     *
     * 声明变量需要使用var 或val ，如下所示：
     * var | val 变盘名［：类型］ ［＝初始值］
     *
     * 其中使用var 声明的变量是值可变的（可被多次赋值），使用val 声明的变量是值不可变的（不能被重新赋值〉。
     * 在上面声明变量的语法格式中，程序
     *  -要么通过“：类型”的形式显式指定该变量的类型，
     *  -要么为该变量指定初始值 -- Kotlin 编译器将会根据该初始值确定变量的类型，
     *  -不能声明变量时既不指定变量类型，也不指定变量初始值。
     *  -声明变量时可以 既显式指定变量的类型，也指定该变量的初始值，但显式指定的变量类型必须与初始值的类型保持一致。
     *

     */
    declareVariables1()

    /**
     *  kotlin声明的变量：所在位置分为两种：
     *  -局部范围的变量： 这种常量允许在声明时不指定初始值，只要在第一次使用之前指定初始值即可。
     *  -类的常量属性变量： 这种常量属性既可以在声明时指定初始值，也可以在类或结构体的构造器中指定初始值。
     *  牢记：无论是var声明的变量，还是val声明的常量。必须经过初始化以后才可以使用。
     */

    declareVariables2()


    /**
     * 【三、整型】
     * 与Java 类似， Kotlin 也提供了4 种整型。
     * Byte: Byte 型整数在内存中通常占8 位，表数范围是－ 128～ 127 兼容Java 的byte 和Byte 类型。
     * Short: Short 型整数在内存中占16 位，表数范围是－32768(-2^15）～32767(2^15-1 ） 。兼容Java的short 和Short 类型。
     * Int: Int 型整数在内存中占32 位，表数范围是－2147483648(-2^31 ）～2147483647(2^31-1 ） 。兼容Java 的int 和Integer 类型。
     * Long: Long 型整数在内存中占64 位，表数范围是－2^63～2^63-1 。兼容Java 的long 和Long类型。
     *
     * 由于Int 型是Kotlin 最常用的整数类型，
     * 因此，如果声明一个常量或变量时没有指定数据类型，只是简单地指定了其初始值为整数值，那么Kotlin 会自动判断该变量的类型为Int 。
     *
     * Kotlin 的整型与Java 不同， Kotlin 的整型不是基本类型，而是引用类型（大致相当于Java的包装类），
     * Byte 、Short、Int 、Long 都继承了Number 类型，因此它们都可调用方法、访问属性。
     *
     * 程序可通过访问不同整数类型的MIN VALUE 和MAX VALUE 属性来获取对应类型的最大值和最小值
     */
    integerFun()

    /**
     * 【Kotlin的非Null】
     * 有一点需要提前说明的是， Kotlin 是null 安全的语言，因此Byte 、Short、Int 、Long 型变量都不能接受null 值，
     * 如果要存储null 值，则应该使用Byte？、Short？、Int？、Long？类型。
     *
     */
    integerFun2()

    /**
     * 由此可见， Kotlin 语言允许在己有数据类型后添加“？”，
     * 添加“？”后的数据类型相当于对原有类型进行了扩展，带“？”的数据类型可支持被赋予null 值。
     */

    /**
     * 此外，整数类型添加“？”后缀与不加后缀还有一个区别一一
     * 普通类型的整型变量将会映射成Java 的基本类型；
     * 带“？”后缀的整型变量将会映射成基本类型的包装类。
     * 举例来说， Kotlin 程序中Int 类型的变量将会映射成Java 的int 基本类型，但Int？类型的变量则会自动映射成Java的Integer 类型
     */
    integerFun3()

    /**
     * Kotlin 的整数数值有3 种表示方式。
     * 十进制： 最普通的整数数值就是十进制的整数。
     * 二进制：以Ob 或OB 开头的整数数值就是二进制的整数。
     * 十六进制：以Ox 或ox 开头的整数数值就是十六进制的整数，其中10～15 分别以a～f（此处的a～f 不区分大小写〉来表示。
     * 注意：Kotlin 不支持八进制的整数。
     */
    integerFun4()

    /**
     * 为了提高数值（包括浮点型）的可读性， Kotlin 允许为数值（包括浮点型）增加下画线作为分隔符，
     * 也可以在数值前添加额外的零。这些下画线和零并不会影响数值本身。
     */
    integerFun5()

    /**
     * 【四、浮点型】
     * 浮点型数值可以包含小数部分，浮点型比整型的表数范围更大， 可以存储比Long 型更大或更小的数。Kotlin 的浮点型有两种。
     *  -Float：表示32 位的浮点型，当精度要求不高时可以使用此种类型。
     *  -Double ：表示64 位的双精度浮点型，当程序要求存储很大或者高精度的浮点数时使（默认）
     *
     * Kotlin 的浮点数有两种表示形式。
     *  -十进制数形式：这种形式就是简单的浮点数， 例如5.12 、512.0 、0.512 等。浮点数必须包含一个小数点，否则会被当成整数类型处理。
     *  -科学计数形式：例如5.1 2e2 （即5 . 12 x 102 ） 、5. 1 2E2 （也是5 .1 2× 102 ）等。
     *  必须指出的是：只有浮点型的数值才可以使用科学计数形式表示。例如51200 是一个Int型的值，但512E2 则是浮点型的值。
     *
     * 除此之外， Kotlin 还提供了3 个特殊的浮点型数值： 正无穷大、负无穷大和非数。
     *  -使用一个正数除以0.0 将得到正无穷大数值。
     *  -使用一个负数除以0.0 将得到负无穷大数值。
     *  -0.0除以0.0 或对负数开方将得到一个非数。
     *
     *  只有浮点数、整型 除以0.0 才可以得到正无穷大数值或负无穷大数值，
     *  但如果使用整型值除以整数O 将会引起编译错误： division by zero （除0 错误）。
     */
    floatFun1();

    /**
     *
     * 【五、字符型】
     * 字符型通常用于表示单个的字符，字符型值必须使用单引号（ ' ）括起来。
     * Kotlin语言使用16 位的Unicode 字符集作为编码方式
     * 而Unicode被设计成支持世界上所有书面语言的字符，包括中文字符，因此Kotlin 程序支持各种语言的字符。
     *
     * 字符型值有如下3 种表示形式。
     *  -直接通过单个字符来指定字符型值，例如'A' 、'9' 和 '0'等。
     *  -通过转义字符表示特殊字符型值，例如'\n' 、'\t'等。
     *  -直接使用Unicode 值来表示字符型值，格式是＇＼uXXXX’，其中xxxx 代表一个十六进制的整数。
     *
     * 与Java 不同的是， Kotlin的Char 型变量不能当成整数值使用，
     * Char 型变量或表达式不能赋值给整型变量，整型变量或表达式也不能赋值给Char 型变量。
     * 简单来说， Katlin 的Char 型就是简简单单的字符型，不能直接当成整型使用。
     */
    charFun();

    /**
     * 【类型转换】
     * Byte：-128~127
     * Short：-32768 ～32767
     *
     * Kotlin 与Java 不同， Katlin 不支持取值范围小的数据类型隐式转换为取值范围大的类型。
     * 由于不同整型支持的表数范围存在差异，因此进行类型转换时必须注意选择合适的类型。
     *
     * Kotlin 为所有数值类型都提供了如下方法进行转换。
     *  -toByte()： 转换为Byte 类型。
     *  -toShort() ：转换为Short 类型。
     *  -toInt()： 转换为Int 类型。
     *  -toLong() ：转换为Long 类型。
     *  -toFloat()： 转换为Float 类型。
     *  -toDouble()： 转换为Double 类型。
     *  -toChar())： 转换为Char 类型。
     * 可以查看Number类中的方法
     * Kotlin 要求不同整型的变量或值之间必须进行显式转换
     *
     */
    switchType()

    /**
     * 虽然Kotlin 缺乏隐式转换，但Kotlin 在【表达式】中可以自动转换，
     * 这种转换是基于上下文推断出来的，而且算术运算会有重载做适当转换。
     */
    switchType2();

    /**
     * Kotlin 虽然不允许直接将Char 型值当成整数值使用，也不允许将整数值直接当成Char 型值使用，
     * 但Kotlin 依然可调用数值型的toChar() 方法将数值型变量或表达式转换为Char 类型。
     */
    switchType3()

    /**
     * 此外， Char 型值虽然不能被当成整数进行算术运算，但Kotlin 为Char 类型提供了加、减运算支持，其计算规则如下。
     *  -Char 型值加、减一个整型值： Kotlin 会先将该Char 型值对应的字符编码进行加、减该整数，然后将计算结果转换为Char 型值。
     *  -两个Char 型值进行相减： Kotlin 将用两个Char 型值对应的字符编码进行减法运算，最后返回Int 类型的值。
     *  -两个Char型值不能相加。
     */
    switchType4()


    /**
     * Kotlin 的Float 、Double 之间需要进行显式转换，
     * 浮点型与整型之间也需要进行显式转换一一其转换过程与前面介绍的整型之间的转换过程基本相似。
     */
    switchType5()

    /**
     * 通过上面的介绍不难发现，当进行类型转换时，应该尽量向表数范围大的数据类型转换，这样程序会更加安全
     * 比如前面介绍的Byte 向Short 转换、Int 向Double 转换，而反过来转换则可能导致溢出。
     * Kotlin 语言的各种数值型的表数范围由小到大的顺序为： Byte → Short→ Int→ Long→ Float → Double 。
     */

    /**
     * 表达式类型的自动提升
     * 当一个算术表达式中包含多个数值型的值时，整个算术表达式的数据类型将发生自动提升。
     * Kotlin 定义了与Java 基本相似的自动提升规则。
     *
     * -所有的Byte 、Short 类型将被提升到Int 类型。
     * -整个算术表达式的数据类型自动提升到与表达式中最高等级操作数同样的类型
     */
    switchType6()

    switchType7()

    /**
     * Boolean类型
     * Boolean 类型的值只能是true 或false
     * Boolean 类型的变量不能接受null 值， Boolean?类型的变量才能接受null 值。
     * Boolean 类型将直接映射为Java 的boolean 基本类型，但Boolean?类型将会映射成boolean 的包装类： Boolean。
     */

}


fun nodes() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    // 单行注释
    /*多行注释*/
    /**文档注释*/
    /* ABC /* 嵌套多行注释 */ DEF */
    /** ABC/**嵌套文档注释*/DEF*/
}

fun division() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    var fenName1 = "rrc"
    var fenName2 = "rrc";
    // var fenName3 = "rrc"; var fenName4 = "rrc"

    //Kotlin 允许一条语句可以跨多行。例如，如下语句都是正确的。
    var fenStr1 = "fKt"
    //表达式可以跨多行
    fenStr1 +=
        "fKit";
    //调用方法可以跨多行
    var hasFK = fenStr1
        .startsWith("f");
}

fun squareBrackets() {

    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val sqList = listOf("AA", "BB", "CC")
    println(sqList[0])//输出"AA"

    val sqMap = mapOf(12 to "A", 20 to "B")
    println(sqMap[12])//输出"A"
}


fun declareVariables1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    //一、声明变量时显式指定类型
    var b: Int

    //二、声明变盘时指定初始值，编译器会根据初始值确定该变量的类型为String
    var name = "abc"

    b = 20 // b 的类型是Int （整型〉，赋值正确
    name = "fKit.org" // name 的类型为String ，赋值正确
    //name= 12 // name 的类型为String ，但12 为Int ，赋值错误

    // 三、声明变量时既显式指定类型，也指定初始值
    // 显式指定的类型与初始值的类型一致， 声明变量正确
    var age: Int = 25
    age = 12 // age 的类型是Int ， 赋值正确
    // 声明变盘时既显式指定类型，也指定初始值
    // 显式指定的类型与初始值的类型不一致，声明变量失败
    //var sun: String = 500
    val book = "hello"
    //book = "a" //使用val 声明的变量是不可变变量，不能被重新赋值

}

fun declareVariables2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    //定义常量，没有显式指定类型，编译器根据初始值确定常量的类型
    val maxAqe = 120

    //定义常量时，既显式指定了常量的类型，也指定了常量的初始值
    val eduName: String = "rrc"

    //常量不允许重新赋值，因此下面代码是错误的
    //maxAqe = 12

    //同时定义多个变量
    //局部范围的常量，声明时不指定初始值
    val herName: String
    //没有初始化，不可以使用,因此下面代码是错误的
    //println(herName)

    //变量初始化了，可以使用,下面代码是正确的
    herName = "Tom"
    println(herName)
}


class DeclareVariablesCls {
    //声明时不指定初始值，但是在构造器中指定了初始值
    var name: String

    //声明时指定初始值
    val city: String = "北京"
    //声明时不指定初始值，也没有在构造器中指定，是错误的。
    // val age: Int

    constructor(name: String) {
        this.name = name
    }
}

fun integerFun() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    //下面代码是正确的
    var a: Int = 56
    //下面代码需要隐式地将2999999999 转换为Int 型使用，因此编译器将会报错
    //var bigValue:Int= 299999999999999
    //下面代码是正确的
    var bigValue2: Long = 299999999999999

    println(Integer.MIN_VALUE)
    println(Integer.MAX_VALUE)

    var b = 100 //默认是int型
    println(b.javaClass.toString())//输出的是int
}


fun integerFun2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    //Int 型变量不支持null 值，所以下面代码是错误的
    //var a: Int = null
    //Int? 相当于支持null 值的Int 型，所以下面代码是正确的
    var b: Int? = null
}

fun integerFun3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    var pml: Int = 200; //pml 的类型是Java 的int 类型
    var pm2: Int = 200; //pm2 的类型是Java 的int 类型
    println(pml === pm2); //基本类型比较，输出true
    var obl: Int? = 300; //obl 的类型是Java 的Integer 类型
    var ob2: Int? = 300; //ob2 的类型是Java 的Integer 类型
    println(obl === ob2); //引用类型比较，输出false
    //思考：如果将obl 、ob2 的值改为 -128 ~ 127 之间的整数呢？
}

fun integerFun4() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    //以Ob 或OB 开头的整数数值是二进制的整数
    var binValue1 = 0b1010101
    var binValue2 = 0B10101110
    //以Ox 或ox 开头的整数数值是十六进制的整数
    var hexValue1 = 0x13
    var hexValue2 = 0XaF

    println("binValue1的值为${binValue1}")
    println("binValue2的值为${binValue2}")
    println("hexValue1的值为${hexValue1}")
    println("hexValue2的值为${hexValue2}")

    /**
     * 上面代码中用到了字符串模板，也就是在字符串中嵌入$｛｝的形式，
     * 在${} 内可放入变量或表达式，kotlin 会将该变量或表达式的值“嵌入”该宇符串内。
     */
}

fun integerFun5() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    val a = 1_000_000
    val b = 234_234_234 // b 实际的值为234234234
    val c = 1234_1234 // c 实际的值为12341234
    val d = 100_1.2_0001//d 实际值为1001.20001(小数点前后不可有下划线)
    println("d的值为${d},类型为${d.javaClass}")

}


fun floatFun1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    var af1 = 5.2345556f
    //下面将看到af1 的值己经发生了改变
    println("al1的值为${af1}")//float最多能有7位有效数字(7位有可能会丢失精度)，但绝对能保证的为6位
    //声明af2 是Float 类型，但25.2345 默认是Double 类型，因此下丽代码编译时报错
    //var af2: Float = 25.2345
    //f1的类型被推断为Double
    var f1 = 5.12e2
    println("f1的值为${f1}")
    var a = 0.0
    // 5 . 0 除以0 . 0 将出现正无穷大数值
    println("5.0/a的值为${5.0 / a}")
    //所有的正无穷大数值都相等，所以下面将会输出true
    println(5.0 / a == 5000 / 0.0)
    // -5.0 除以0 . 0 将出现负无穷大数值
    println("-5.0/a的值为${-5.0 / a}")
    //所有的负无穷大数值都相等，所以下面将会输出true
    println(-5.0 / a == -50000 / 0.0)
    //0.0/0.0 将出现非数
    var nan: Double = a / a;
    println("a/a的值为${nan}")
    //非数与自己都不相等，所以下面将会输出false
    println(nan == nan)

}

fun charFun() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    //直接指定单个字符作为字符值
    val aChar: Char = 'A'
    //使用转义字符来作为字符值
    val enterChar: Char = '\r'
    //使用Unicode 编码值来指定字符值
    val ch: Char = '\u9999'
    //将输出一个’ 香’ 字符
    println(ch)
    //定义一个’ 疯’ 字符值
    var zhong: Char = '疯'
    //将Char 型变量当成Int 型处理会报错
    //var iv : Int = zhong
}


fun switchType() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    var bookPrice: Byte = 79
    var itemPrice: Short = 120
    // bookPrice 是Byte 类型，但变盘a 是Short 类型，因此下面代码错误(但是在java中是可以的)
    // var a: Short = bookPrice

    //显式将bookPrice 强制转换为Short 类型
    var a: Short = bookPrice.toShort()
    var b: Byte = itemPrice.toByte()
    println("a=${a},b=${b}")

    val amount = 233
    //将Int 型变量转换为Byte 类型， 发生溢出
    val byteAmount: Byte = amount.toByte()
    println(byteAmount)

}


fun switchType2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    val bookPrice: Byte = 79
    val itemPrice: Short = 120

    //算术表达式中的bookPrice 、itemPrice 会自动提升为Int 类型
    val total = bookPrice + itemPrice;
    println(total.javaClass)//int

    //下面表达式中的bookPrice 强制转换为Long 类型，因此整个表达式类型为Long
    val total1 = bookPrice.toLong() + itemPrice.toInt();
    println(total1.javaClass)//long

    /**
     * 上面程序中使用了变量的javaClass 属性，该属性来自Any 类型（ Any 类型是Kotlin 所有类型的根父类），
     * javaClass 属性用于获取指定变量对应的Java 类型（大致相当于Java 反射中的getClass()方法1)
     */
}

fun switchType3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    //定义一个空字符串
    var result = ""
    //进行6次循环
    for (i in 0..5) {
        //生成一个97 ～ 122 之间的Int 类型整数
        val intVal = (Math.random() * 26 + 97).toInt();
        //将intValue 强制转换为Char 类型后连接到result 后面
        result = result + intVal.toChar()
    }
    //输出随机字符串
    println(result);
}

fun switchType4() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    var c1 = 'i'
    var c2 = 'k'
    println(c1 + 4)//m
    println(c1 - 4)//e
    println(c1 - c2)//2
    //println(c1 + c2)//错误
}

fun switchType5() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    var width: Float = 2.3f
    var height: Double = 4.5
    //width 必须显式强制转换为Double 之后，才能赋值给变盘a
    var a: Double = width.toDouble()
    println("a的值为${a}")

    //将height 强制转换为Float 之后再进行计算，整个表达式的类型是Float
    //因此area1 的类型也被推断为Float
    var area1 = width * height.toFloat()

    //表达式中的height 是Double 类型，它是等级最高的运算数
    //因此整个表达式的类型是Double, area2 的类型也被推断为Double
    var area2 = width * height

    val multi: Int = 5
    //因此totalHeight1 的类型也被推断为Double
    var totalHeight1 = height * multi

    //将height 强制转换为Int 类型后进行计算，整个表达式的类型是Int
    //因此totalHeight2 的类型也被推断为Int
    var totalHeight2 = height.toInt() * multi
}

fun switchType6() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    //定义一个Short 类型变量
    var sValue: Short = 5
    //表达式中的sValue 将自动提升到Int 类型， 则右边的表达式类型为Int
    //将一个Int 类型值赋给Short 类型变量将发生错误
    // sValue = sValue - 2 //表达式的类型将被提升到Int类型


    /**
     * 必须指出，表达式的类型将严格保持和表达式中最高等级操作数相同的类型。
     * 下面代码中的两个Int 类型整数进行除法运算，即使无法除尽，也将得到一个Int 类型结果上〉。
     */
    var iVal: Int = 3
    ///右边表达式中的两个操作数都是Int 类型，故右边表达式的类型为Int
    //虽然23/3 不能除尽， 但依然得到一个Int 类型整数
    val intResult = 23 / iVal
    println(intResult)//7
}

fun switchType7() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    //输出字符串Hello!a7
    println("hello" + 'a' + 7)
    //输出字符串hHello !
    println('a' + 7 + "hello")
}







