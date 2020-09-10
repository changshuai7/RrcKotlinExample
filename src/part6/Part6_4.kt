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
     *
     * 使用内联函数，用inline修饰的函数就是内联函数
     * inline修饰符影响【函数本身】和【传给它的 Lambda 表达式】，【所有这些都将内联到调用处】
     * 即编译器会把调用这个函数的地方用这个函数的方法体进行替换，而不是创建一个函数对象并生成一个调用。
     *
     *
     * 可以查看class文件来确定和分析：
     * 高阶函数，非内联，无论传入的是匿名函数还是lambda函数，都会生成一个class的匿名类
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
     *
     * 前面己经提到，在Lambda 表达式中直接使用return不是用于返回该表达式，而是用于返回该表达式所在的函数。
     * 但要记住：在默认情况下，在Lambda 表达式中并不允许直接使用return 。
     * 这是因为如果是非内联的Lambda 表达式，该Lambda 表达式会额外生成一个函数对象
     * 因此这种表达式中的return不可能用于返回它所在的函数。
     *
     * 由于内联的Lambda 表达式会被直接复制、粘贴到调用它的函数中
     * 故此时在该Lambda表达式中可以使用return，该return就像直接写在Lambda 表达式的调用函数中一样。
     * 因此，该内联的Lambda 表达式中的return可用于返回它所在的函数，这种返回被称为非局部返回。
     *
     *
     * 你可能会问，匿名函数也会生成函数对象，为什么非内联的匿名函数可以允许return呢？
     * 原因是：在默认情况下（非内联），lambda表达式和匿名内部类虽然都会生成一个函数对象。
     * 但是匿名内部类可以的return，lambda不允许return，这其实也是kotlin内部语法来决定的。
     *
     * 总之：return 中断的一定是距离最近的一个fun，且lambda不允许中断非内联的函数
     */
    inLineFun3()

    /**
     * 五、禁用非局部返回
     *
     * 从前边已经知道，通过内联函数可以使 Lambda表达式实现非局部返回，
     * 但是，如果一个内联函数的函数类型参数被crossinline 修饰，则对应传入的 Lambda表达式将不能非局部返回了，只能局部返回了。
     */
    inLineFun4()


}


fun inLineFun1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val arr = arrayOf(1, 2, 3, 4, 5)
    val arr1 = mapArr(arr) { it * 2 }
    println(arr1.contentToString())

    //上述代码相当于如下：
    // 编译器会将lambda函数直接复制粘贴到inline函数中,同事inline函数mapArr()也会复制到被调用处
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
     * 从上面的代码可以看出，此时根本就【不存在函数调用】
     * 自然也就不需要额外生成函数对象了，也不会产生捕获，也不需要处理函数调用的压械和出校开销。
     */

    /**
     * 介绍到此处，可能有人会想，既然内联函数这么好，那么我们干脆全部使用内联函数好了。
     * 下面的问题是：内联函数的缺点在哪里呢？到底哪些情况应该使用内联函数，哪些情况不应该使用内联函数呢？
     *
     * 通过前面的介绍我们知道，内联函数的本质是将被调用的Lambda表达式或函数的代码复制、粘贴到原来的执行函数中
     * 因此如果被调用的Lambda 表达式或函数的代码量非常大，且该Lambda 表达式或函数多次被调用
     * 一一注意每调用一次，该Lambda 表达式或函数的代码就会被复制一次， 因此势必带来程序代码量的急剧增加。
     *
     * 由此可见，内联函数并不总是带来好处的，【内联函数是以目标代码的增加为代价来节省时间开销的】
     *
     * 因此是否应该使用内联函数的答案是：
     * 如果被调用的Lambda 表达式或函数包含大量的执行代码，那么就不应该使用内联函数：
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

fun inLineFun3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    val arr = arrayOf(1, 2, 3, 4, 5)

    val arr1 = mapArr(arr, fun(e: Int): Int {
        return e * 2//匿名函数只会结束该匿名函数本身
    })
    println("arr1=${arr1.contentToString()}")//[2,4,6,8,10]

    val arr2 = mapArr(arr) {
        it * 2
        return  //此处会直接结束fun3()函数
    }
    println("arr2=${arr2.contentToString()}")
}

fun inLineFun4() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    val re = in2(2) {
        it + 1//有了return以后，此行代码将无用。
        //return //非局部返回会报错
        return@in2 it + 2 //只能使用局部返回
    }
    println(re)


}

inline fun in2(i: Int, crossinline fn: (Int) -> Int): Int {
    return fn(i)
}




