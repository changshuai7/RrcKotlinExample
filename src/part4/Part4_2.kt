package part4

fun main() {

    /**
     * 【循环结构】
     * while
     * do while
     */
    whileFun()

    /**
     * for in 循环
     * Kotlin中的for in 循环和Js中的略有区别
     * 1、js中的 for in 变量需要声明，kotlin不需要
     * 2、js中的变量代表了索引（数组的index或者对象的key），kotlin中的变量代表了实际元素
     *
     * for-in 循环中的常量无须声明。for - in 循环中的常量将会在每次循环开始时自动被赋值。
     * 因此该常量无须提前声明。只要将它包含在for-in 循环的声明中，即可隐式声明该常量，无须使用var关键宇声明。
     * for-in 循环可用于遍历任何可迭代对象。所谓可选代对象就是该对象包含一个iterator()方法，且该方法的返回值对象具有next()、hasNext()方法，
     * 这三个方法都使用operator修饰。
     */
    forInFun()

    /**
     * 嵌套循环
     * 如果把一个循环放在另一个循环体内，那么就可以形成嵌套循环。
     * 嵌套循环既可以是for-in循环嵌套while 循环，也可以是while 循环嵌套d o while 循环… …
     * 即各种类型的循环都可以作为外层循环，各种类型的循环也都可以作为内层循环。
     */

    /**
     * Kotlin 语言没有提供goto 语句来控制程序的跳转，这种做法提高了程序流程控制的可读性，但降低了其灵活性。
     * 为了弥补这种不足， Kotlin 提供了continue 和break 来控制循环结构。
     * 除此之外，使用return 可以结束整个方法，当然也就结束了一次循环。
     *
     */

    /**
     * break:结束当前循环体
     */
    breakFun()

    /**
     * continue:结束本次循环，继续下次循环
     */
    continueFun()

}


fun whileFun() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val a = 5
    //while (a > 0);//分号（；）是一个空循环体

}

fun forInFun() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    for (num in 1..7) {
        print("$num ")
    }
    //for-in 循环的循环计数器相当于一个用val 声明的常量，因此程序不允许在for-in 循环中对循环计数器进行赋值
    for (num in 1..7) {
        //num = 3 //会报错
    }
    //除此之外，for-in 循环还可用于遍历数组、List 、字典等集合。
    // 关于for-in 循环遍历数组、 List 、宇典的元素相关知识后面介绍
}


fun breakFun() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    //break：如果有多层循环嵌套，则只结束所在循环。
    for (i in 0..5) {
        for (j in 6..10) {
            println("i = $i,j = $j")
            if (j >= 7) break
        }
    }
    println("-----------")
    //break: 如果想直接结束其外层循环。此时需要在 break 后紧跟一个标签，这个标签用于标识一个外层循环。
    //Kotlin 中的标签就是一个紧跟着＠的标识符。Kotlin 中的标签只有放在循环语句或switch 语句之前才起作用。例如下面代码。
    outer@ for (i in 0..5) {
        for (j in 6..10) {
            println("i = $i,j = $j")
            if (j >= 7) break@outer  //外层循环都break了，内层必然不执行了
        }
    }
    //通常紧跟break 之后的标签，必须在break 所在循环的外层循环之前定义才有意义。
}


fun continueFun() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    // 与break 类似的是， continue 后也可以紧跟一个标签，
    // 用于直接跳过标签所标识循环的当次循环的剩下语句， 重新开始下一次循环

    outer@ for (i in 0..5) {
        for (j in 6..10) {
            println("i = $i,j = $j")
            if (j >= 7) continue@outer
        }
    }
}



