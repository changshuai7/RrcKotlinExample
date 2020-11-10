package part10


fun main() {

    /**

    一、声明处型变：

    1、如果泛型只需要出现在方法的返回值声明中（不出现在形参声明中〉（构造方法除外），那么该方法就只是取出泛型对象
    因此该方法就支持泛型协变（相当于通配符上限）：
    如果一个类的所有方法都支持泛型协变，那么该类的泛型参数可使用 out 修饰。

    2、如果泛型只需要出现在方法的形参声明中（不出现在返回值声明中〉（构造方法除外），那么该方法就只是传入泛型对象
    因此该方法就支持泛型逆变（相当于通配符下限）：
    如果一个类的所有方法都支持泛型逆变，那么该类的泛型参数可使用 in 修饰。
     */
    xb1()
    xb2()

    /**
    二、使用处型变：类型投影

    声明时型变虽然方便，但它有一个限制：
    要么该类的所有方法都只用泛型声明返回值类型（此时可用out 声明型变〉：
    要么所有方法都只用泛型声明形参类型（此时可用in 声明型变）。
    如果一个类中有的方法使用泛型声明返回值类型，有的方法使用泛型声明形参类型，那么该类就不能使用声明处型变。

    如果不能使用声明处型变，则还可使用Kotlin 提供的“使用处型变”。
    所谓使用处型变，就是在使用泛型时对其使用out 或 in 修饰。
     */
    //协变
    xb3()
    xb4()
    //逆变
    xb5()
    xb6()

    /**
    三、星号投影
     */
    xb7()
}


fun xb1() {

    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    //声明了一个泛型类，使用了out修饰泛型形参
    class User<out T> {

        /**
         * 泛型类，协变类型，因此在该User类的内部， T 只能出现在方法的返回值声明中， 不能出现在方法的形参声明中。
         * 所以，如果用 T 为User 类声明属性，则只能声明为只读属性，否则， setter方法的形参类型是T ，这就不符合要求了。
         */
        //不能使用var，否则就有setter 方法 ，setter 方法会导致T 出现在方法形参中
        private val info: T

        constructor(info: T) {
            this.info = info
        }


        fun getInfo(): T {
            return this.info
        }
    }

    val u = User<String>("hello")
    println(u.getInfo())

    // 一旦声明了泛型类支持协变，程序即可安全地将User<String＞ 、User<Int> 赋值给 User <Any>
    // 只要尖括号中的类型是Any 的子类即可

    val u1: User<Any> = User<String>("world")
    println(u1.getInfo())

    val u2: User<Any> = User<Int>(12)
    println(u2.getInfo())

}

fun xb2() {

    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    /**
     * 泛型类：逆变类型
     */
    class Item<in T> {

        private var info: T

        constructor(info: T) {
            this.info = info
        }

        fun updateInfo(t: T) {
            this.info = t
            println(t)
        }
    }

    val i1: Item<String> = Item<Any>("hello")
    val i2: Item<Int> = Item<Any>(11)
    i1.updateInfo("hello world")
    i2.updateInfo(100)


    //上面程序中粗体字代码声明了一个泛型类， 且使用了in 修饰泛型形参，
    // 因此在该Item 类 的内部， T 只能出现在方法的形参声明中， 不能出现在方法的返回值声明中。
    //一旦声明了泛型类支持逆变， 程序即可安全地将Item<Any＞、Item<CharSequence＞赋值给 User＜ 如ing＞ ， 只要尖括号中的类型是String 的父类即可，


    /**
     * 总结：
     * 如果泛型T (或其他字母〉只出现在该类的方法的返回值声明中（T代表的是传出值），那么该泛型形参即可使用out 修饰T 。
     * 如果泛型T C 或其他字母〉只出现在该类的方法的形参声明中C T 代表的是传入参数） ，那么该泛型形参即可使用in 修饰T
     */

}

//协变：在方法中使用
fun xb3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    // 程序传入该方法的参数的可以是Array<Int> 、Array<String＞等 各种类型
    // 只要尖括号中的类型是Any 的子类即可，
    // 果将from数声明为Array<out Any＞类型，那么就意味着只能安全地从该from 参数代表的数组中取元素，而不能将元素添加到from数组中
    // 道理很明显： 我们 无法预测实际传给from 参数的是Array<Int> 还是Array<String＞。
    fun copy(from: Array<out Any>, to: Array<Any>) {
        val len = if (from.size > to.size) to.size else from.size
        for (i in 0 until len) {
            to[i] = from[i]
        }
    }

    val from: Array<Int> = arrayOf(1, 2, 3, 4, 5)
    val to: Array<Any> = Array(5) { 0 }
    copy(from, to)
    println(from.contentToString())
}

//协变：在变量中使用
fun xb4() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    var numArr1: Array<Number> = arrayOf(1, 2, 3, 4, 5)
    var numArr2: Array<out Number> = arrayOf(1, 2, 3, 4, 5)

    //numArr2.set(0,100)//协变类型，只可以从容器中读取数据，不可以向容器存数据

    var intArr: Array<Int> = arrayOf(0, 0, 0)
    //numArr1 = intArr//报错,因为没有申明型变，Array<Int>不能使用Array<Number>来接收。
    numArr2 = intArr//正确。Array<Int>可以使用Array<out Number>来接收。

}


//逆变：在方法中使用
fun xb5() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    // 该dest 参数的类型是Array<in String＞，这 就是使用处逆变。
    // 也就是说，程序传入该dest的参数的可以是Array<CharSequence＞、Array<Any>等各种类型，只要尖括号中的类型是String 的父类即可
    fun fill(dest: Array<in String>, value: String) {
        if (dest.isNotEmpty()) {
            dest[0] = value
        }
    }

    val arr: Array<Any> = arrayOf("a", "b", "c", 1)

    fill(arr, "hello")//可以存取数据
    println(arr.contentToString())


}

//逆变：在变量中使用
fun xb6() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    var numArr1: Array<Number> = arrayOf(1, 2, 3, 4, 5)
    var numArr2: Array<in Number> = arrayOf(1, 2, 3, 4, 5)
    numArr2[0] = 100

    var anyArr: Array<Any> = arrayOf(0, 0, 0)
    //numArr1 = anyArr//报错,因为没有申明型变，Array<Any>不能使用Array<Number>来接收。
    numArr2 = anyArr ////正确。Array<Any>可以使用Array<in Number>来接收。
    //val any: Number = numArr2[0]//不可以读取

}

fun xb7() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    // 星号投影是为了处理Java 的原始类型，比如如下Java 代码：
    // ArrayList list = new ArrayList()

    // 虽然Java 的List 、ArrayList 都有泛型声明，但程序并没有为它们传入类型参数
    // 这在Java 程序中是允许的。这种用法被称为“原始类型”。
    //但在Kotlin 中要写成如下形式。
    var list: ArrayList<*> = arrayListOf(1, 2, 3)


    //关于星号投影：
    /**

    假如定义了支持声明时型变的Foo<out T>类，该泛型支持声明时协变，因此其中T 是一个具有上限的协变类型参数
    Foo＜＊＞等价于Foo<out Any？＞。这意味着当T 未知时，我们可以安全地从Foo ＜＊＞读取Any？类型的值。

    假如定义了支持声明时型变的Foo<in T>类，该泛型支持声明时逆变，因此其中T 是一个逆变类型参数
    Foo＜＊＞等价于Foo<in Nothing＞。这意味着当T 未知时，我们不能以任何安全的方式向Foo ＜＊＞写入值。

    假如定义了不支持声明时型变的Foo<T＞类，该泛型不支持型变。
    这意味着当T 未知时， Foo＜＊＞在读取值时等价于Foo<out Any？＞，在写入值时等价于Foo<in Nothing>
    即不能以任何安全的方式向Foo ＜＊＞写入值

     */

    /**
    如果泛型类型具有多个类型参数，那么每个类型参数都可以单独指星号投影。
    假如声明了支持两个泛型参数的Foo<in T out U＞类型，则关于星号投影的解释以下。
    》对于Foo<*, String＞ ，其实相当于Foo<in Nothing, String＞。
    》对于Foo<Int，*> ，其实相当于Foo<Int, out Any？＞ 。
    》对于Foo＜*，*> ，其实相当于Foo<in Nothing, out Any？＞。
     */

}



