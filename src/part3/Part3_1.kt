package part3


fun main() {
    /**
     * 【运算符和表达式】
     *
     * Kotlin 语言提供了一系列功能丰富的运算符，这些运算符包括所有的算术运算符，还包括比较运算符、逻辑运算符、区间运算符和位运算符等。
     * Kotlin 基本支持Java 的全部运算符（可能有些在写法上存在差异）
     * Kotlin 不支持三目运算符（因为Kotlin 可用if 表达式代替三目运算符〉。
     * 需要指出的是， Kotlin 的很多运算符其实等价于 【operator 修饰的、特定函数名的函数】，因此Kotlin 的运算符非常强大
     * 其不仅能作用于普通数值类型的变量和常量，也可作用于任何自定义的类一一只要它们提供operator 修饰的、特定函数名的函数即可。
     * 本章节也会详细介绍Kotlin 运算符的这种强大功能。
     *
     * 【a.xxx 带点的都是operator】
     * 【a xxx b 不带点的都是infix】
     */

    /**
     * Kotlin 的运算符都是以方法形式来实现的，这些运算符都具有特定的符号（如“＋”或“＊”）和固定的优先级。
     * 各种运算符对应的方法名都是固定的，我们只要为某类型提供了特定名称（比如双目＋运算符对应的方法名为plus ）的方法，成员方法或扩展方法都可，
     * 接下来即可对该类型的对象使用“＋”进行运算
     * 一一因此， Kotlin 的所有运算符的功能都是广义的，不仅能作用于数值型、字符串，而且也可作用于任意自定义的Kotlin 类。
     */

    /**
     * 单目前缀运算符
     * 对于a：
     * +a ==> a.unaryPlus()
     * -a ==> a.unaryMinus()
     * !a ==> a.not()
     */
    unaryFun()


    /**
     * 自增自减运算符
     *
     * a+1 ==> a.inc()
     * a-1 ==> a.dec()
     *
     * [a++] 的内部逻辑：
     * val temp = a
     * a = a.inc()
     * return temp
     *
     * [++a] 的内部逻辑：
     * a = a.inc()
     * return a
     *
     */
    incDecFun()

    /**
     * 双目算数运算符
     * a + b ==> a.plus(b)
     * a - b ==> a.minus(b)
     * a * b ==> a.times(b)
     * a / b ==> a.div(b)
     *
     * a % b ==> a.rem(b)
     * a .. b ==> a.rangeTo(b)
     */
    doubleOperateFun()


    /**
     * in 和 !in
     *
     * a in b ==> b.contains(a)
     * a !in b ==> !b.contains(a)
     *
     */
    inFun()

    /**
     * 索引运算符
     * a[ i ] ==> a.get(i)
     * a[ i ] = j ==> a.set(i,j)
     */
    indexFun()

    /**
     * 调用运算符
     * a() ==> a.invoke()
     * a(x) ==> a.invoke(x)
     * 这种方式，在java中是不可以的。
     */
    invokeFun()

    /**
     * 广义赋值运算符
     * a+=b ==> a.plusAssign(b)
     * a-=b ==> a.minusAssign(b)
     * a*=b ==> a.timesAssign(b)
     * a/=b ==> a.divAssign(b)
     * a%=b ==> a.remAssign(b)
     *
     * 这种广义的赋值运算符有些特殊，比如a+=b ，实际上相当于a=a+b
     * 因此在程序中进行 a+= b 运算时，往往并不需要a 有plusAssign()方法。因为有plus方法就已经足够了
     *
     * 所以，对于广义赋值操作，例如a+= b ， 编译器会先判断plusAssign()方法是否存在，如果存在，则按如下步骤执行。
     * -如果plus方法也存在，则报错（因为调用方法不明确）
     * -确保plusAssign()没有返回值，否则报错
     * -如果能通过上述两步检查，则执行a.plusAssign(b)，否则会转换为a = a +b 即执行a = a.plus(b)
     *
     *
     */
    assignFun()

    /**
     * 相等与不等
     * a==b ==> a?.equal(b)?:b ==null
     * a!=b ==> !(a?.equal(b)?:b ==null)
     *
     * 在java中，== 表示两个变量是否引用同一个对象
     * 在Kotlin中，== 等同于equal,但是==是更安全的
     *
     * java中的 ==和!= (内存地址相等与否) 被 kotlin中的===和!==所代替
     */
    equalFun()

    /**
     * 比较运算符
     * a>b ==> a.compareTo(b)>0
     * a<b ==> a.compareTo(b)<0
     * a=b ==> a.compareTo(b)=0
     *
     * a>=b ==> a.compareTo(b)>=0
     * a<=b ==> a.compareTo(b)<=0
     *
     * 该方法是Comparable 接口中定义的方法，
     * 因此原来Java 类中支持使用compareTo() 方法比较大小的对象，都可使用比较运算符进行计算。
     */
    compareToFun()

    /**
     * 位运算符
     *
     * Kotlin 支持的位运算符同样有如下7 个。
     *
     * and(bits）： 按位与。当两位同时为1 时才返回l 。
     * or(bits）： 按位或。只要有一位为l ，即可返回1 。
     * inv(bits）：按位非。单目运算符，将操作数的每个位（包括符号位〉全部取反。
     * xor(bits）： 按位异或。当两位相同时返回0 ，不同时返回1 。
     * shl(bits) ： 左移运算符。
     * shr(bits）： 右移运算符。
     * ushr(bits）： 无符号右移运算符。
     *
     * Kotlin 的位运算符只能对Int 和Long 两种数据类型起作用。
     *
     */
    bitOperation()

    /**
     * 区间运算符
     *
     *  -闭区间：a..b             ==>[a,b]
     *  -半开区间：a until b       ==>[a,b)
     *
     * 要求：b>=a
     *
     *  -反向区间 a downTo b    ==>[b,a] 反向闭区间
     *
     *  区间步长：step
     *
     */
    sectionFun()

    /**
     * 运算符重载（练习）
     *
     * Kotlin 的运算符都是靠特定名称的方法支撑的
     * 因此只要重载这些名称的方法，我们就可以为任意类添加这些运算符。
     * 重载运算符的方法需要用operator 修饰符进行标记。
     */
    reLoadFun()


}


fun unaryFun() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val a = 1
    val b = -2
    val c = true
    println("${a.unaryMinus()} === ${b.unaryPlus()} === ${c.not()}")
    // 等同于如下：
    println("${-a} === ${+b} === ${!c}")

    /**
     * 以后如果在查阅API 时发现某个类有unaryPlus()、unaryMinus()、not()方法
     * 那就说明可对该类的实例使用单目前缀运算符＋、-、! 进行运算。
     */

}

fun incDecFun() {

    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    var a = 1
    var b = 2
    var inc = a.inc()
    var dec = b.dec()

    println("inc = $inc，dec = $dec")

    /**
     * 由此可见，以后如果在查阅API 时发现某个类有inc()、dec() 方法
     * 那就说明可对该类的实例使用＋＋、一进行运算。
     */
}


fun doubleOperateFun() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    var a = 6
    var b = 2
    println(
        """
            |a+b = ${a.plus(b)} 
            |a-b = ${a.minus(b)} 
            |a*b = ${a.times(b)} 
            |a/b = ${a.div(b)}
            |a%b = ${a.rem(b)}
        """.trimMargin()
    )

    println()

    println(
        """
            |a+b = ${a + b} 
            |a-b = ${a - b} 
            |a*b = ${a * b} 
            |a/b = ${a / b}
            |a%b = ${a % b}
        """.trimMargin()
    )


    /**
     * 由此可见，以后如果在查阅API 时发现某个类有带一个参数的plus()、minus()、times()、div()、rem()、rangeTo() 方法，
     * 那就说明可对该类的实例使用以上双目算术运算符。
     */
}

fun inFun() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    var a = "a"
    var str = "abc"

    println("${a in str},${a !in str},${str.contains(a)}")

    var arr = arrayOf("a", "b", "c")
    var item = "a"
    println("${item in arr},${arr.contains(item)}")

    /**
     * 由此可见，以后如果在查阅API 时发现某个类有带一个参数的contains()方法，
     * 那就说明可对该类的实例使用in 、!in 运算符。
     */
}


fun indexFun() {

    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")


    val str = "rrc.com"
    println("${str[1]} , ${str.get(1)}")

    var list = mutableListOf<String>("a", "b", "c")
    println("${list[1]},${list.get(1)}")

    list[1] = "bb"
    list.set(2, "cc")
    println(list)

}


fun invokeFun() {

    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    var str = "abc"
    val cls = Class.forName("java.lang.String")
    val method = cls.getMethod("length")
    val invoke1 = method.invoke(str)
    val invoke2 = method(str)
    println("$invoke1 , $invoke2")
}

fun assignFun() {

    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    var d1 = AssignClsTest("小红")
    var d2 = AssignClsTest("小兰")
    d1 += d2
    println(d1.name)

}

class AssignClsTest(var name: String) {

    operator fun plusAssign(cls: AssignClsTest): Unit {
        name = "plusAssign -- $name:${cls.name}"
    }

//    operator fun plus(cls: AssignClsTest): AssignClsTest {
//        return AssignClsTest("plus -- $name:${cls.name}")
//    }

}


fun equalFun() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val s1 = String(StringBuffer("abc"))
    val s2 = String(StringBuffer("abc"))

    println(
        """
        ${s1 == s2}
        ${s1.equals(s2)}
        ${s1 === s2}
        """.trimIndent()
    )
    println(null.equals(null))
}

fun compareToFun() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    var p1 = Person("abcdef")
    var p2 = Person("a")
    println("${p1 > p2} , ${p1.compareTo(p2) > 0}")

}

class Person(var name: String) : Comparable<Person> {
    override fun compareTo(other: Person): Int {
        return this.name.length - other.name.length
    }
}


fun bitOperation() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    println(5 and 9) //将输出1
    println(5 or 9) //将输出13
    println((-5).inv()) //将输出4
    println(5 xor 9) //将输出12
}

fun sectionFun() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    println("闭区间")
    val se1 = 2..6
    for (i in se1) {
        print("$i  ")//2 3 4 5 6
    }

    println("半开区间")
    val se2 = 2 until 6
    for (i in se2) {
        print("$i  ")//2 3 4 5
    }

    println("until用于长度-1")
    //until 可以很好得用于for循环( 0 到长度-1 )
    var list = listOf<String>("a", "b", "c")
    for (index in 0 until list.size) {
        print("${list[index]}  ")
    }

    println("反向区间")
    val se3 = 6 downTo 2
    for (i in se3) {
        print("$i  ")//6 5 4 3 2
    }

    println("区间步长")
    // 所有区间的默认步长都是1
    // 而通过step 运算符（其实是一个infix 函数）可以显式指定区间的步长
    var se4 = 6 downTo 2 step 2
    for (i in se4) {
        print("$i  ")//6  4  2
    }

}


fun reLoadFun() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    var d1 = Data(1, 2)
    println(-d1)
    var d2 = Data(1, 2)
    println(d2++)
    println(++d2)

    println(Data(1, 2) + Data(3, 4))
    println(Data(2, 6) - Data(4, 5))


}

class Data(var x: Int, var y: Int) {

    // +a
    operator fun unaryPlus(): Data {
        return Data(+x, +y)
    }

    // -a
    operator fun unaryMinus(): Data {
        return Data(-x, -y)
    }

    //++
    operator fun inc(): Data {
        return Data(x + 1, y + 1)
    }

    //--
    operator fun dec(): Data {
        return Data(x - 1, y - 1)
    }

    //+
    operator fun plus(d: Data): Data {
        return Data(x + d.x, y + d.y)
    }

    //-
    operator fun minus(d: Data): Data {
        return Data(x - d.x, y - d.y)
    }

    override fun toString(): String {
        return "[x=${x},y=${y}]"
    }

}




