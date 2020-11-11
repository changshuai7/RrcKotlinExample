package part12

/**
 * 十二、Kotlin和Java的互调、反射
 *
 * 1、Java调Kotlin
 *
 * 由于Kotlin程序编译之后本身就是完全兼容Java规范的，因此Java 调用Kotlin 也是非常方便的
 */
fun main() {

    ktCallJava1()

}

fun ktCallJava1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
}
