package part10

/**
 * 泛型函数
 */
fun main() {

    /**
    所谓泛型函数，就是在声明函数时允许定义一个或多个泛型形参
    泛型形参要用尖括号括起来，整体放在fun 与函数名之间。泛型函数的语法格式如下

    fun <T,S> 函数名(形参列表)：返回值类型{
    //函数体
    }
     */
    genericFun1()

    /**
    泛型形参扩展函数
     */
    genericFun2()


    /**
    具体化类型参数

     */
    genericFun3()
}


fun genericFun1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    fun <T> copy(from: List<T>, to: MutableList<in T>) {
        //to 逆变容器，只能存数据，不能取数据
        for (index in from.indices) {
            to.add(from[index])
            var t = from[index]
        }

    }

    val from = listOf<String>("a", "b", "c")
    val to = mutableListOf<Any>(1, "hello")
    copy(from, to)
    println(to)


}


fun genericFun2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")


    // 为泛型形参T 扩展了一个ex的 方法
    // 由于这个T 只是一 个泛型形参，它可以代表任意类型，因此程序相当于为所有类型都扩展了一个ex 方法。
    // 所以，程序可以为Int 、Double 、String 等各种对象调用ex 方法。
    fun <T> T.ex() {
        println("hello")
    }

    val a = 1
    val b = "world"
    a.ex<Int>()
    b.ex<String>()
}


fun genericFun3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
}