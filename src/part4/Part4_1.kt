package part4



/**
 * 【第四章：流程控制】
 */
fun main() {
    /**
     * 与Java 类似， Kotlin 同样提供了两种基本的流程控制结构： 分支结构和循环结构
     *  -分支结构：if、when（when 语句可以代替Java 的switch 语句，而且功能更强大）
     *  -循环结构：while 、do while 、for-in 循环（抛弃了Java 原有的普通for循环）
     */

    /**
     * 分支结构：
     * if:
     * 唯一需要注意的是：Kotlin 的if 分支还可作为【表达式】使用。
     * 也就是说，整个if 表达式（包括else部分）最终会返回一个值，因此if 可以代替Java 中的三目运算符（三元表达式）。
     */
    ifFun()

    /**
     * 分支结构：
     * when：
     * when 分支取代了Java 原有的switch 语句
     */

    /**
     * when分支语句
     * 不难发现when 分支其实就是switch 分支的简化。when 分支的改变有以下几点。
     * 1、不再需要使用case 关键字
     * 2、case 值后的冒号改为使用箭头（ -> ）
     * 3、default 改为更有意义、更明确的else
     *
     * 注意：when分支，有隐含的break，和java逻辑类似
     */
    whenFun1()


    /**
     * when表达式
     * 如果when 分支被当作表达式，那么符合条件的分支的代码块的值就是整个表达式的值。
     * 与if 分支相同的是，【如果分支的执行体是一个代码块，那么该代码块的值就是块中最后的表达式的值】
     * 当 when 语句作为表达式使用时， when 表达式也需要有一个返回值。
     * 因此 when 表达式通常必须有else 分支，除非编译器能够检测出所有的可能情况都己经被覆盖了。
     */
    whenFun2()

    /**
     * when分支处理范围
     * in
     */
    whenFun3()

    /**
     * when分支类型判断
     * 通过使用is 、!is 运算符，我们还可以使用when 分支检查表达式是否为指定类型。例如如下代码。
     */
    whenFun4()

    /**
     * when条件分支
     * when 分支还可以【用来取代if... else if 链】
     * 此时不需要为when 分支提供任何条件表达式，每个分支条件都是一个布尔表达式
     * 当指定分支的布尔表达式为true 时执行该分支。
     */
    whenFun5()


}


fun ifFun() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    var age = 45
    var result1 = if (age > 60) "老年人" else if (age > 40) "中年人" else "年轻人"
    println(result1)

    var name = "rrc"
    var result2 = if (name.isNotEmpty()) "success" else "fail"
    println(result2)

    /**
     * 当使用if 分支作为表达式时，由于if 表达式必须要有返回值，因此if 表达式需要有else 部分。
     * if 表达式的分支依然可以是用花括号括起来的代码块，此时【代码块的最后一个表达式的值】将作为整个代码块的值。例如如下代码。
     */
    var a = 12
    var result3 =
        if (a > 60) {
            println("a大于60")
            "老年人"
        } else if (a > 40) {
            println("中年人")
            "中年人"
        } else {
            println("年轻人")
            "年轻人"
        }
    println(result3)
}

fun whenFun1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val score = 'B'

    println("|--------|")

    // 不再需要使用case 关键字
    // case 值后的冒号改为使用箭头（ -> ）
    // default 改为更有意义、更明确的else
    when (score) {
        'A' -> println("优秀")
        'B' -> println("一般")
        'C' -> println("差劲")
        else -> println("其他")
    }

    println("|--------|")

    //如果when 分支包含多条语句，则需要使用花括号将这些语句括成一个整体形成代码块。
    when (score) {
        'A' -> println("优秀")
        'B' -> {
            println("一般")
            println("真的很一般")
        }
        'C' -> println("差劲")
        else -> println("其他")
    }

    println("|--------|")

    //when 分支可以匹配多个值。
    when (score) {
        'A', 'B' -> println("优秀和一般")
        'C' -> println("差劲")
        else -> println("其他")
    }

    println("|--------|")

    //when 分支后的值不再要求是常量或字面值，可以是任意表达式
    val str = "ABCD"
    when (score) {
        str[0] -> println("优秀")
        str[0] + 1 -> println("一般")
        str[0] + 2 -> println("差劲")
        else -> println("其他")
    }
    println("|--------|")

    //when 分支不再对条件表达式的类型有任何要求，
    val d = D("rrc")
    when (d) {
        D("rrc") -> println("匹配")
        else -> println("不匹配")
    }

    // 从上面的粗体字代码可以看出，此时when 分支的条件表达式是Date对象，但这不影响 when 分支的执行，
    // 【重要】只要when 的条件表达式与某个分支的值通过“==”比较返回true（非===，而是==，即equals方法返回相同） ，程序就可以进入执行该分支的代码。

}

class D(var name: String) {
    override fun equals(other: Any?): Boolean {
        if (other is D) {
            return this.name.equals(other.name)
        }
        return super.equals(other)

    }
}


fun whenFun2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    val score = 'B'
    val result = when (score) {
        'A' -> "优秀"
        'B' -> {
            println("成绩一般")
            "一般"
        }
        else -> "其他"
    }
    println("结果是：${result}")

    //在when 表达式的每个分支的代码块的最后都定义了一个表达式作为该代码块的值，这样即可保证when 表达式可以返回有效的值

}


fun whenFun3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val age = 20
    when (age) {
        in 0..20 -> println("少年")
        in 21..30 -> println("青年")
        in 31..40 -> println("中年")
        else -> println("其他")
    }
}


fun whenFun4() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    val a = 12
    println("a的真实类型为${realType(a)}")
}

//realType这个function的返回值 是一个 表达式的返回值。
fun realType(data: Any): String = when (data) {
    is String -> "String类型"
    is Int -> {
        println("这是一个Int类型")
        "Int类型"
    }
    else -> "其他类型"
}


fun whenFun5() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    val str = "rrc.com"
    when {
        str.startsWith("rrc") -> println("str以rrc开头")  // if
        str.startsWith("abc") -> println("str以abc开头")  // else if
        else -> println("str以其他开头")                              // else
    }
}



