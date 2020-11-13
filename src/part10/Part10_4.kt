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
    Kotlin允许在内联函数（使用inline 修饰的函数）中使用reified 修饰泛型形参
    这样即可将该泛型形参变成一个具体化的类型参数。一旦将泛型形参变成具体化的类型参数
    接下来在该函数中就可以像使用普通类型一样使用该类型参数，包括使用is 、as 等运算符。

     */
    genericFun3()
    genericFun4()

    /**
    设定类型形参的上限
    Kotlin 泛型不仅允许在使用通配符形参时设定上限，而且可以在定义类型形参时设定上限，
    用于表示传给该类型形参的实际类型要么是该上限类型，要么是该上限类型的子类
     */
    genericFun5()
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


    //着调用该函数时必须显式 传入一个Class 对象
    //注意--> 类::class 可以拿到类的class对象
    println(findData(Integer::class.java))
    println(findData(String::class.java))

    //上面两行代码确实可以实现本例的需要，但是这种方式未免太不优雅了
    // 因为我们知道泛 型形参本身就是类型参数，当程序调用该函数时完全可通过泛型形参来传入类型参数，
    // 何必还 要通过函数的参数来传入类型呢？

    println("---------")

    //此时就可考虑使用reified 修饰内联函数的泛型形参，这样就可直接在函数中使用该类型形参
    // 从而避免用户通过函数的参数来传入类型。例如，将上面的findData（） 函数改为如下findData2（）


    //从findData2（）程序中可以看出，程序此时使用reified 修饰了泛型形参T
    // 接下来程序即可在函数 体内直接使用该泛型形参T
    println(findData2<Int>())
    println(findData2<String>())


}
//需求：我们要从某个List 集合中查找第一个指定类型的元素，由于程序需要根据指定类型来查找数据，所以最容易想到的做法是，定义一个类型来作为参数

val db = listOf("a", 100, java.util.Date())
fun <T> findData(cls: Class<T>): T? {
    for (item in db) {
        @Suppress("UNCHECKED_CAST")
        //java的Class中的isInstance()方法用于检查给定对象是否是具有此Class表示的对象的实例。
        if (cls.isInstance(item)) {
            return item as? T
        }
    }
    return null
}

inline fun <reified T> findData2(): T? {
    for (item in db) {
        if (item is T) {
            return item
        }
    }
    return null
}

fun genericFun4() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    /**
    对于使用reified 修饰的具体化的类型参数，程序甚至可以在函数体内对该参数使用反射，
    就像它是一个真正的类型一样。例如如下函数：
     */

    //代码直接对泛型形参T 使用反射，通过class 属性来获取该类对应的
    //Class 对象，这也是具体化类型参数的优势。
    val cls = getCls<Int>()
    for (item in cls) {
        println(item)
    }

}

inline fun <reified T> getCls() = T::class.members

fun genericFun5() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    ///////////////////////////////////////////

    class Apple<T : Number> {
        var info: T

        constructor(t: T) {
            this.info = t
        }
    }

    var a1 = Apple<Int>(10)
    var a2 = Apple<Double>(1.1)
    //var a3 = Apple<String>('hello') //编译出错

    ///////////////////////////////////////////

    fun <T : Number> test(vararg params: T) {

        for (item in params) {
            println(item)
        }
    }
    test<Int>(1, 2, 3)
    test<Double>(1.1, 2.2, 3.3)
    //test<String>("a","b","c") //编译出错

    ///////////////////////////////////////////

    /** 前面在定义泛型时没有指定上限，其实它有一个默认的上限： Any？ */

    /**
    在一种更极端的情况下，程序需要为类型形参设定多个上限
    （至多有一个父类上限，可以有多个接口上限） ， 表明该类型形参必须是其父类的子类（是父类本身也行），
    并且实现多个上限接口。

    由于在尖括号中只能指定一个上限，所以，如果要为泛型指定多个上限，则需要使用单独的where 子句
    where子句写在方法整体的最后面（包括返回值的后面）
     */

    /**
    If a type parameter has multiple constraints, they all need to be placed in the 'where' clause
    需要注意的是：如果一个泛型具有了多个泛型上限，那么必须全部写在where后，在<T：XXX>里写上限就会无效
     */

    class User<T>(name: String) where T : Comparable<T>, T : Cloneable {

    }

    fun <T> testUser() :String where T : Number, T : Comparable<T>, T : Cloneable {
        return ""
    }


}
