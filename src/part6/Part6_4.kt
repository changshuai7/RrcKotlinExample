package part6

/**
 * 【内联函数】
 */
fun main() {
    /**
     *
     * 一、引入
     * 先简单介绍一下高阶函数（为函数传入函数或Lambda 表达式作为参数）的调用过程。
     * 调用Lambda 表达式或函数的过程是： 程序要将执行顺序转移到被调用表达式或函数所在的内存地址，当被调用表达式或函数执行完后，再返回到原函数执行的地方。
     * 在上面这个转移过程中，系统要处理如下事情。
     * 1、为被调用的表达式或函数创建一个对象。
     * 2、为被调用的表达式或函数所捕获的变量创建一个副本。
     * 3、在跳转到被调用的表达式或函数所在的地址之前，要先保护现场并记录执行地址：从被调用的表达式或函数地址返回时，要先恢复现场，并按原来保存的地址继续执行。也就是通常所说的压栈和出栈
     *
     * 从上面介绍不难看出，函数调用会产生一定的时间和空间开销
     * 如果被调用的表达式或函数的代码量本身不大，而且该表达式或函数经常被调用，那么这个时间和空间开销的损耗就很不划算。
     *
     * 为了避免产生函数调用的过程，我们可以考虑直接把被调用的表达式或函数的代码“嵌入”原来的执行流中
     * 一一简单来说，就是编译器负责去“复制、粘贴”： 复制被调用的表达式或函数的代码，然后粘贴到原来的执行代码中。
     * 为了让编译器帮我们干这个复制、粘贴的活，可通过内联函数来实现。
     */

    /**
     * 二、使用inline 关键字修饰带函数形参的函数即内联函数
     */
    inLineFun1()

    /**
     * 三、禁止部分内联
     * 使用inline 修饰函数之后，所有传入该函数的Lambda 表达式或函数都会被内联化；
     * 如果我们又希望该函数中某一个或某几个函数类型的形参不会被内联化，则可考虑使用noinline 修饰它们。
     * 简单来说， noinline 用于显式阻止某一个或某几个形参内联化。例如如下程序。
     */
    inLineFun2()


    /**
     * 四、非局部返回
     */
    fun3()


}




fun inLineFun1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val arr = arrayOf(1, 2, 3, 4, 5)
    mapArr(arr) { it * 2 }
    //上述代码相当于如下：lambda函数直接复制到inline函数中
    //编译器实际上会将Lambda 表达式的代码复制、粘贴到mapArr() 函数中。
    /*
    fun mapArr(arr: Array<Int>, fn: (Int) -> Int): Array<Int> {
       val resultArr = Array<Int>(arr.size) { 0 }

       for (index in arr.indices) {
           resultArr[index] = arr[index] * 2
       }
       return resultArr
   }
   */

    /**
     * 从上面的粗代码可以看出，此时根本就不存在函数调用
     * 自然也就不需要额外生成函数对象了，也不会产生捕获，也不需要处理函数调用的压械和出校开销。
     */

    /**
     * 介绍到此处，可能有人会想，既然内联函数这么好，那么我们干脆全部使用内联函数好了。
     * 下面的问题是：内联函数的缺点在哪里呢？到底哪些情况应该使用内联函数，哪些情况不应该使用内联函数呢？
     *
     * 通过前面的介绍我们知道，内联函数的本质是将被调用的Lambda 表达式或函数的代码复制、粘贴到原来的执行函数中
     * 因此如果被调用的Lambda 表达式或函数的代码量非常大，且该Lambda 表达式或函数多次被调用一一注意每调用一次，
     * 该Lambda 表达式或函数的代码就会被复制一次， 因此势必带来程序代码量的急剧增加。
     *
     * 由此可见，内联函数并不总是带来好处的，【内联函数是以目标代码的增加为代价来节省时间开销的】
     *
     * 因此是否应该使用内联函数的答案是：如果被调用的Lambda 表达式或函数包含大量的执行代码，那么就不应该使用内联函数：
     * 如果被调用的Lambda 表达式或函数只包含非常简单的执行代码（尤其是单表达式），那么就应该使用内联函数。
     */
}

inline fun mapArr(arr: Array<Int>, fn: (Int) -> Int): Array<Int> {
    val resultArr = Array<Int>(arr.size) { 0 }

    for (index in arr.indices) {
        resultArr[index] = fn(arr[index])
    }
    return resultArr
}

fun inLineFun2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    in1({ it + 1 }, { it + 2 })//fn2会被取消内联
}

inline fun in1(fn1: (Int) -> Int, noinline fn2: (Int) -> Int): Int {
    return 0
}

fun fun3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
}



