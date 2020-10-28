package part8

/**
 *
 * 八、面向对象(下)
 *
 * 8.1、扩展
 *
 * 扩展是一种非常灵活的动态机制，它既不需要使用继承，也不需要使用类似于装饰者的任何设计模式，即可为现有的类增加功能，因此使用非常方便。
 *
 */
fun main() {

    /**
     * Kotlin 的扩展是一个很独特的功能， Java 本身并不支持扩展，
     * 扩展方法的语法很简单，其实就是定义一个函数，在函数名前增加被扩展的类（或接口）名和点号（．）
     * 扩展方法中的this 与成员方法中的this 一样，也代表调用该方法的对象。
     */
    extend1()


    /**
     * 扩展也可以为Kotlin 系统提供的类增加方法
     */
    extend2()


    /**
     * 我们知道， Java 是一门静态语言。一个类被定义完成之后，程序无法动态地为该类增加、删除成员（ field、方法等），除非开发者重新编辑该类的源代码，并重新编译该类。
     * 但现在Kotlin 的扩展却好像可以动态地为一个类增加新的方法，而且不需要重新修改该类的源代码，这真是太神奇了！那
     * Kotlin 扩展的实际情况是怎样的呢？难道Kotlin 可以突破JVM 的限制？
     *
     * 实际上， Kotlin 的扩展并没有真正地修改所扩展的类，被扩展的类还是原来的类，没有任何改变。
     * Kotlin 扩展的本质就是定义了一个函数，当程序用对象调用扩展方法时
     * Kotlin 在编译时会执行静态解析一一就是【根据调用对象、方法名找到扩展函数，转换为函数调用】
     *
     */

    /**
     * 举个例子：
     * 为List扩展一个方法shuffle()
     * strList.shuffle()
     *
     * Kotlin 在编译时这行代码按如下步骤执行。
     * 1、检查strList 的类型，发现其类型是List<String＞ 。检查List<String＞类本身是否定义了shuffle()方法
     * 如果该类本身包含该方法，则Kotlin无须进行处理，直接编译即可。
     * 2、如果List<String＞类本身不包含shuffle()方法，则Kotlin 会查找程序是否为List<String>扩展了shuffle()方法
     * 一一也就是查找系统中是否包含了名为List<String>.shuffle()的函数（或泛型函数〉定义，
     * 如果找到该函数，则Kotlin 编译器会执行静态解析，它会将上面代码替换成执行List<String>.shuffle()函数。
     * 3、如果List<String＞不包含shuffle（）方法，也找不到名为List<String>.shuffle（）的函数（或泛型函数）定义，编译器将报错。
     *
     * 由此可见， Kotlin 的扩展并没有真正改变被扩展的类
     * Kotlin 只是耍了一个小“花招”，当Kotlin 程序调用扩展方法时， Kotlin 编译器会将这行代码静态解析为调用函数，这样JVM就可接受了。
     * 这意味着调用扩展方法是由其所在表达式的【编译时类型】决定的，而不是由它所在表达式的运行时类型决定的。
     */

    /**
     * 总结
     * 一、成员方法执行动态解析（由运行时类型决定）；扩展方法执行静态解析（由编译时类型决定）．
     *
     * 二、成员方法的优先级高于扩展方法。
     * 这这意味着，如果一个类包含了具有相同签名的成员方法和扩展方法，当程序调用这个方法时，系统总是会执行成员方法，而不会执行扩展方法
     * 但如果扩展方法和成员方法只是具有相同的方法名， 但它们的形参列表不同，这种情况就不算遮蔽，因为它们其实是两个方法， Kotlin 也可正常区分它们。
     */
    extend3()


    /**
     * 可空类型扩展方法
     * Kotlin 还允许为可空类型（带“？”后缀的类型）扩展方法。
     * 由于可空类型允许接受null值，这样使得null 值也可调用该扩展方法。
     * 从另一方面来看， 由于会导致null值调用该扩展方法，因此程序需要在扩展方法中处理null 值的情形
     */
    extend4()


    /**
     * 扩展属性
     *
     * Kotlin 也允许扩展属性，但由于Kotlin 的扩展并不能真正修改目标类，因此Kotlin 扩展的属性其实是通过添加getter、setter方法实现的，没有幕后宇段。
     * 简单来说，扩展的属性只能是计算属性！
     * 由于Kotlin 的扩展属性只能是计算属性，因此对扩展属性有如下两个限制。
     * 》扩展属性不能有初始值（没有存储属性值的幕后宇段） 。
     * 》不能用field 关键字显式访问幕后宇段。
     * 》扩展只读属性必须提供getter 方法： 扩展读写属性必须提供getter 、setter方法。
     */
    extend5()


    /**
     * 前面见到的扩展，都是以顶层函数的形式（放在包空间下）进行定义的，因此这些扩展都可直接使用（如果扩展位于不同的包中，当然也需要导包）。
     * Kotlin 还支持以类成员的方式定义扩展一一就像为类定义方法、属性那样定义扩展。
     *
     * 对于以类成员方式定义的扩展，一方面它属于被扩展的类，因此在扩展方法（属性〉中可直接调用被扩展类的成员（省略this 前缀〉：
     * 另一方面它又位于定义它所在类的类体中， 因此在扩展方法（属性）中又可直接调用它所在类的成员（省略this 前缀）。
     *
     *
     * 如果被扩展类和扩展定义所在的类包含了同名的方法
     * 此时就会导致：程序在扩展方法中调用两个类都包含的方法时，系统总是优先调用被扩展类的方法。
     * 为了让系统调用扩展定义所在类的方法，必须使用带标签的this 进行限定。
     */
    extend6()


    /**
     * 带接受者的匿名函数（说白了就是指定这个函数是谁来执行，指定执行的主体是谁）
     *
     * Kotlin 还支持为类扩展匿名函数
     * 在这种情况下，该扩展函数所属的类也是该函数的接收者。因此，这种匿名函数也被称为“带接收者的匿名函数”。
     * 与普通扩展方法不同的是：去掉被扩展类的类名和点(．)之后的函数名即可，其他部分井没有太大的区别。
     * 与普通扩展方法相似的是，带接收者的匿名函数（相当于扩展匿名函数）也允许在函数体内访问接收者对象的成员。
     *
     * 与普通函数相似的是，带接收者的匿名函数也有自身的类型，即带接收者的函数类型
     */
    extend7()


    /**
     * 如果接收者类型可通过上下文推断出来
     * 那么Kotlin 允许使用Lambda 表达式作为带接收者的匿名函数。例如如下程序。
     */
    extend8()


}


fun extend1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    open class User(val name: String, val age: Int) {
        fun show() {
            println("name =$name ,age = $age")
        }
    }

    //给User扩展了一个test的方法
    fun User.test() {
        //扩展方法中的this 与成员方法中的this 一样，也代表调用该方法的对象。
        println("我是扩展方法，name = ${this.name}")

    }

    class ZiUser(name: String, age: Int) : User(name, age)

    val u = ZiUser("rrc", 12)
    u.show()
    u.test()

}


fun extend2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    //对List扩展一个方法，针对Int类型的
    //扩展List 时指定了它的泛型形参为Int 。也就是说，该扩展仅对List<Int> 有效
    fun List<Int>.test1() {
    }

    //对List扩展一个方法，针对所有类型
    //Kotlin 完全支持直接进行泛型类扩展，只不过此时需要在函数上使用泛型，因此要使用泛型函数的语法。
    fun <T> List<T>.test2() {

    }
}


fun extend3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    open class Fu {
        fun foo() {
            println("父类方法")
        }
    }

    class Zi : Fu()

    fun Fu.foo() {//提示：Extension is shadowed by a member: public final fun foo(): Unit（扩展方法被成员方法遮蔽。）
        println("父类扩展方法")
    }

    fun Zi.foo() {//提示：Extension is shadowed by a member: public final fun foo(): Unit（扩展方法被成员方法遮蔽。）
        println("子类扩展方法")
    }

    val f1: Zi = Zi();
    val f2: Fu = Zi()

    f1.foo() // 打印：父类方法。删除掉Fu中的foo方法以后打印：子类扩展方法（在编译阶段，编译器只知道foo是Zi类型的,而不知道运行时类型）
    f2.foo() // 打印：父类方法。删除掉Fu中的foo方法以后打印：父类扩展方法（在编译阶段，编译器只知道foo是Fu类型的,而不知道运行时类型）
}


fun extend4() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    fun Any?.equals(other: Any?): Boolean {
        if (this == null) {
            return other == null
        }
        return this == other
    }

    println(null.equals(null))//true
    println(null.equals("abc"))//false
    println("abc".equals(null))//false

}

fun extend5() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    var u = User1("李", "小明")
    println(u.fullName)
    u.fullName = "王.小红"
    u.show()

}

class User1(var first: String, var last: String) {
    fun show() {
        println("first = $first,last = $last")
    }
}

var User1.fullName: String
    get() {
        return "${this.first}.${this.last}"
    }
    set(value) {
        val split = value.split(".")
        first = split[0]
        last = split[1]

    }


fun extend6() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    class A {
        fun fooA() {
            println("fooA")
        }

        fun f() {
            println("A的f")
        }
    }

    class B {
        fun fooB() {
            println("fooB")
        }

        fun f() {
            println("B的f")
        }

        fun A.fooEX() {//对类A扩展方法
            //在该方法内既可调用类A 的成员，也可调用类B 的成员
            fooA()//调用A的成员
            fooB()//调用B的成员

            println("\n---------------\n")

            f()//同名方法，默认调用A的f
            this.f()//同名方法，默认调用A的f
            this@B.f()
            //Kotlin 的this 比Java 的this 更强大，Kotlin的this 支持用“＠类名”形式，这种形式限制 了该this 代表哪个类的对象。
        }

        fun info() {
            val a = A()
            a.fooEX()
        }
    }

    val b = B()
    b.info()
}

fun extend7() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    var f: Int.() -> Boolean = fun Int.(): Boolean {
        return this > 0
    }

    //函数类型为：Int.()->Boolean
    println(f(10))

}

fun extend8() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    class Cls {
        val flag = true;
        fun me1() {
            println("me1")
        }

        fun me2() {
            println("me2")
        }
    }

    fun init(f: Cls.() -> Boolean) {
        val c = Cls()
        if (c.f()) {
            c.me1()
        } else {
            c.me2()
        }
    }

    //用lambda执行方法
    init { this.flag }

    //用带接受者的匿名函数执行方法
    init(fun Cls.(): Boolean {
        return this.flag
    })


    println("\n-----------------------\n")

    class HTML {
        fun head() {
            println("<head></head>")
        }

        fun body() {
            println("<body></body>")
        }
    }

    //意味着：HTML对象被动态扩展了一 个init（）方法，而且该方法的执行体是动态传入的。
    fun html(init: HTML.() -> Unit) {
        println("<Html>")
        HTML().init()
        println("</Html>")
    }

    html {
        this.head()
        this.body()
    }
    println()
    html(fun HTML.() {
        this.head()
        this.body()
    })


}


