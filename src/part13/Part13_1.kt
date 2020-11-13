package part13


/**
 * 十三、Kotlin的反射

 *
 * 使用Kotlin 的反射API --> 对应的jar文件（ kotlin-reflect.jar ）
 */
fun main() {


    /**
    ================== 类引用 ==================

    Kotlin 的类引用是 KClass 对象
    Java 的类引用是 java.lang.Class 对象

    通过任意对象，引用class属性，可以获取KClass对象（ MyObj::class ）
    通过KClass 获取对应的java.lang.Class 对象，则可调用KClass 对象的java 属性。( MyObj::class.java )

     */
    reflexTest1()

    /**
    从KClass 获取类信息

     */
    reflexTest2()


}


fun reflexTest1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    class User

    //获取KClass对象
    val kClass = User::class
    //获取Java的Class对象
    val jClass = User::class.java

}

fun reflexTest2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

}



