package part1


/**
 *
 * 【一、介绍】
 * kotlin -->jvm
 * jdk -->jvm
 * Kotlin 是JetBrains 在2011 年推出的一门全新的编程语言，这门语言最早被设计成运行在JVM上
 * 使用Kotlin 编写的程序会被编译成字节码文件，该字节码文件可直接在JVM上运行（用java 命令运行）
 * Kotlin 可以与现有的Java 语言包保持100% 的兼容性，而且Kotlin 代码比Java 代码更简洁、更富有表现力。
 * 简单来说， 一句话： Kotlin 既可利用Java的优势，又比Java 更简洁。
 * 此外， Kotlin 程序还可直接编译生成JavaScript 代码
 * Kotlin 程序既可编译成前端JavaScript TypeScript 代码，用于实现网页的DOM 操作，实现前端编程；也可编译成后端JavaScript 代码，与服务端技术（如Node.js ）交互。
 * 不得不说的一点是，目前Android 己推荐使用Kotlin 作为官方开发语言，这意味着Kotlin将会在阳android 开发中大放异彩
 */

/**
 * 【二、环境配置】
 * 同Java
 * https://github.com/JetBrains/kotlin/releases
 *
 * -java:
 * javac
 * java
 *
 * -kotlin
 * kotlinc
 * kotlin
 *
 */

/**
 * 【三、第一个Kotlin程序】
 */
fun main() {

    println("HelloWorld!")
}

class D

//接在Kotlin 程序中定义变量、函数， kotlin c 将会自动生成一个名为“文件名首字母大写＋Kt ＂的类
//所以在kotlin中绝对不可以定义"文件名首字母大写＋Kt＂的类,即不可在该包下重复定义同名的类
//如下面定义HelloWorldKt的类就是错误的。
//class Part1Kt