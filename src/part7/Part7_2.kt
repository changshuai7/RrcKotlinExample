package part7

/**
 * 七、面向对象（2）
 *
 * 属性和字段
 * 属性是Kotlin的一个重要特色
 * Kotlin 的属性相当于Java 的字段(field ）再加上getter和setter方法（只读属性没有setter 方法）
 * 而且开发者不需要自己实现getter和setter方法。
 *
 *
 * 可以查看Java源码：
 * 1.Tools -> Kotlin -> Show Kotlin Bytecode
 * 2.点击decompile即可反编译出对应的java代码
 */
fun main() {


    /**
     *
     * Kotlin 使用val 定义只读属性，使用var 定义读写属性，系统会为只读属性生成getter方法，会为读写属性生成getter 和setter 方法。
     * 在定义Kotlin 的普通属性时，需要程序员显式指定初始值：要么在定义时指定初始值，要么在构造器中指定初始值。
     *
     * kotlin 定义一个属性，就相当于定义一个Java 类的private 修饰的field
     * 以及public 、final 修饰的getter 和setter 方法。
     *
     * 虽然Kotlin 确实会为属性生成getter和setter方法
     * 但由于源程序中并未真正定义这些getter和setter 方法，因此【Kotlin 程序不允许直接调用Address 对象的getter和setter方法】
     * 但如果使用Java 程序来调用Address 类
     * 由于该Address 类中各属性对应的field 都用了private 修饰，因此不能用点语法直接访问这些field，所以只能用getter和setter 方法来访问属性。
     *
     *
     * 使用val 声明的是只读属性，则只有getter方法，没有setter 方法。
     * 只读属性的语法和读写属性的语法有两方面的不同：
     * ①只读属性用val 定义，读写属性用var 定义。
     * ②只读属性不允许有setter 方法。
     */
    field1()


    /**
     * 自定义getter/setter方法
     *
     * 在定义属性时可指定自定义的getter和setter方法，这些方法可加入自己的控制逻辑，其中
     * getter是一个形如get(){}的方法（当然也可使用单表达式方法体） , getter 应该是无参数、带一个返回值的方法：
     * setter 是一个形如set(value){}的方法（当然也可使用单表达式方法体）， setter应该是带一个参数、无返回值的方法。
     * 定义getter/setter方法时无须使用fun 关键字
     */
    field2()

    /**
     * 幕后字段
     *
     * 在Kotlin 中定义一个普通属性时， Kotlin 会为该属性生成一个field字段、getter和setter 方法（只读属性没有setter方法〉。
     * Kotlin 为该属性所生成的field 就被称为幕后字段（ backing field ） 。
     * 此处有一点需要强调： 如果Kotlin 类的属性有幕后宇段， 则Kotlin 要求为该属性显式指定初始值一一要么在定义时指定，要么在构造器中指定
     * 如果Kotlin 类的属性没有幕后宇段，则Kotlin 不允许为该属性指定初始值（这是理所当然的， 由于没有field ，即使指定了初始值也没地方保存） 。
     *
     * 那么Kotlin 何时会为属性生成幕后宇段呢？
     * 1、该属性使用Kotlin 自动生成的getter和setter方法或其中之一。
     * 换句话说，对于只读属性，必须重写getter方法：对于读写属性，必须重写getter 、setter方法： 否则总会为该属性生成幕后宇段。
     * 2、重写getter、setter方法时，使用field关键字显式引用了幕后字段。
     *
     * 通过上面描述可以发现， Kotlin 允许开发者在getter、setter方法中通过field 引用系统自动生成的字段（幕后宇段）。
     * 例如，有时候我们希望对用户设置的属性值进行控制，此时就可以重写setter方法，并在setter方法中加入自己的控制。
     */
    field3()

    /**
     * 幕后属性
     *
     * 在个别情况下，开发者希望自己定义field ，并为该field 提供setter 、getter 方法，就像Java所使用的方法。此时可使用Kotlin 的幕后属性。
     * 【幕后属性】就是用【private 修饰的属性， Kotlin 不会为幕后属性生成任何getter、setter 方法】
     * 因此程序不能直接访问幕后属性，必须由开发者为幕后属性提供getter、setter 方法。
     */
    field4()

    /**
     * 延迟初始化属性 p149
     */
    filed5()
}



fun field1() {
    class Address {
        var street: String = ""
        val city: String = ""
    }

    val ad: Address = Address()
    ad.street //只能用点语法来获取其属性

}


fun field2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    class Name(firstName: String, lastName: String) {
        var firstName: String = firstName
        var lastName: String = lastName
        var fullName: String
            get() = "$firstName.$lastName"
            set(value) {
                val split = value.split(".")
                firstName = split[0]
                lastName = split[1]

            }
    }

    val name = Name("猪", "八戒")
    println("原始的全名为=> ${name.fullName}")
    name.fullName = "常.帅"
    println("修改后的姓名=> 姓为:${name.firstName}，名为:${name.lastName}")


    /**
     * 上面方法定义了firstName 和lastName 两个属性， Kotlin 会为这两个属性分别生成一个field 、getter和setter 方法。
     * 程序中定义了fullName 属性（读写属性），并为该属性重新定义了getter和setter方法。
     * 由于fullName 井不需要真正存储状态，它的返回值其实是通过first 和last 两个属性计算出来的，
     * 因此Kotlin 也不需要为其生成对应的field。
     * 当Kotlin 不需要为该属性生成对应的field时，也就不能为该属性指定初始值，所以上面程序没有为fullName 属性指定初始值。
     *
     * 这种无须以对应field 的属性，其实就相当于Swift语言中的【计算属性】。这种属性并不真正存储任何状态
     * 它的值其实是通过某种算法计算得到的，当程序对该属性赋值时，被赋的值也会被存储到其他field 中
     *
     */


    /**
     * 如果仅需要改变getter/setter方法的可见性或者对其添加注解，但不需要改变默认的实现，
     * 那么Kotlin 允许只自定义getter/setter方法名，而不重新定义其代码实现。
     *
     */
    class Cls(name: String) {
        var name = name
            private set//将setter 方法改为private 修饰，但依然使用默认实现
        //private get // getter不可以设置私有，除非name自身私有
    }

    var c = Cls("a")
    var n = c.name
    //c.name = "b" //报错
}


fun field3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    class User(name: String, age: Int) {
        var name: String = name
            set(newName) {
                if (newName.length < 0) {
                    println("姓名非法")
                } else {
                    field = newName
                }
            }


        var age: Int = age
            set(newAge) {
                if (newAge < 0) {
                    println("年龄非法")
                } else {
                    field = newAge
                }
            }
    }


    /**
     * 【特别注意】
     * 当程序重写getter、setter方法时，不能通过点语法来对name 、age 赋值！
     * 假如在name的setter方法中使用点语法对name 赋值， 由于点语法赋值的本质是调用setter方法
     * 这样就会造成在setter方法中再次调用setter方法，从而形成无限递归。
     * 因此，上面程序重写name/age属性的setter 方法时， 实际上是通过field 引用幕后字段，从而实现对幕后字段的赋值的。
     *
     */

    // 以下代码执行报错：StackOverflowError

//    class User1(name: String, age: Int) {
//        var name = name
//            set(name) {
//                this.name = name
//            }
//        var age = age
//            set(age) {
//                this.age = age
//            }
//    }
//
//    var u = User1("Tom", 12)
//    u.name = "Jake"

}


fun field4() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    class User(name: String, age: Int) {
        //_name该属性就是一个幕后属性，
        //Kotlin 不会为该属性生成getter 、setter 方法，因此程序无法直接访问对象的＿name 属性。
        private var _name = name//幕后属性
        var name: String
            set(newName) {
                if (newName.length < 0) {
                    println("姓名非法")
                } else {
                    _name = newName
                }
            }
            get() = _name

    }

    val u = User("abc", 12)
    u.name = "ddd"
    // 这种方式比较烦琐， 通常没有必要采用如此烦琐的方式。
}

fun filed5() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

}




