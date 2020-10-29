package part8

/**
 *
 * 八、面向对象(下)
 *
 * 8.5、嵌套类和内部类
 *
 */
fun main() {


    /**
     *
    回顾Java的内部类：
    静态类（只有内部类才能被声明为静态类，即静态内部类）
    1.只能在内部类中定义静态类
    2.静态内部类与外层类绑定，即使没有创建外层类的对象，它一样存在。
    3.静态类的方法可以是静态的方法也可以是非静态的方法，静态的方法可以在外层通过静态类调用，而非静态的方法必须要创建类的对象之后才能调用。
    4.只能引用外部类的static成员变量（也就是类变量）。
    5.如果一个内部类不是被定义成静态内部类，那么在定义成员变量或者成员方法的时候，是不能够被定义成静态的。

    总结
    1.是否能拥有静态成员
    静态内部类可以有静态成员(方法，属性)，而非静态内部类则不能有静态成员(方法，属性)。
    即：静态内部类可以有静态成员、非静态成员。非静态内部类只可以有非静态成员。
    2.访问外部类的成员
    静态内部类只能够访问外部类的静态成员,而非静态内部类则可以访问外部类的所有成员(方法，属性)。
    3.静态内部类和非静态内部类在创建时有区别
    //假设类A有静态内部类B和非静态内部类C，创建B和C的区别为：
    A a=new A();
    A.B b=new A.B();  // B是静态内部类
    A.C c=a.new C();  // C是非静态内部类

     */


    /**
     * 在Kotlin中：
     * Java 的内部类可分为两种：静态内部类（有static 修饰〉和非静态内部类（无static 修饰）。
     * 而Kotlin 则完全取消了static 修饰符。但实际上Kotlin 也需要有静态内部类和非静态内部类之分，所以Kotlin 只得为它们换了个“马甲”。
     *
     * 嵌套类（相当于静态内部类）：只要将一个类放在另一个类中定义，这个类就变成了嵌套类，相当于Java 中有static 修饰的静态内部类。
     * 内部类（非静态内部类）：使用inner 修饰的嵌套类叫内部类，相当于Java 中无static修饰的非静态内部类。
     *
     */

    /**
    定义嵌套类（内部类〉与定义外部类的语法大致相同，只是嵌套类（内部类）可使用protected 修饰，用于表示该嵌套类（内部类〉可在其外部类的子类中被访问。
    定义嵌套类（内部类）非常简单，只要把一个类放在另一个类的类内部嵌套定义即可。
    此处的“类内部”包括类中的任何位置，甚至在方法中也可以定义嵌套类（方法中定义的嵌套类被称为局部嵌套类）

    大部分时候，嵌套类（内部类）都被作为成员嵌套类（内部类）定义，而不是作为局部嵌套类。
    成员嵌套类（内部类〉是一种与属性、方法、构造器和初始化块相似的成员：局部嵌套类则不是类成员。

    嵌套类无须使用任何特殊修饰符，它相当于Java 的静态内部类：
    内部类需要使用inner修饰，它相当于Java 的非静态内部类。

    因为嵌套类（内部类）属于其外部类的成员，所以可以使用任意访问控制符如private 、internal 、protected 和public 等修饰。
     */
    innerCls1()


    /**
    当在内部类的方法内访问某个属性时，系统优先在该方法内查找是否存在该名字的局部变量，如果存在就使用该变量；
    如果不存在，则到该方法所在的内部类中查找是否存在该名字的属性,如果存在则使用该属性：
    如果不存在，则到该内部类所在的外部类中查找是否存在该名字的属性，如果存在则使用该属性：
    如果依然不存在，系统将出现编译错误，提示找不到该属
    性。

    如果外部类属性、内部类属性与内部类中方法的局部变量同名，则可通过使用this 、带标签的this 进行限定来区分
     */


    /**
    内部类的成员可以访问外部类的private 成员，但反过来就不成立了。内部类的成员只在
    内部类范围内是可知的，并不能被外部类直接使用。如果外部类需要访问内部类的成员，则必
    须显式创建内部类对象来调用访问其成员。
     */
    innerCls2()


    /**
    关于this
    在类的方法或属性中， this 代表调用该方法或属性的对象。
    在类的构造器中， this 代表该构造器即将返回的对象。
    在扩展函数或带接收者的函数字面值中， this 表示点（．）左边的“接收者”。
    如果this 没有限定符，那么它优先代表包含该this 的最内层的接收者，并且会自动向外搜索。
    如果要让this 明确引用特定的接收者，则可使用标签限定符。
     */
    innerCls3()


    /**
    嵌套类相当于Java 的静态内部类，因此嵌套类直接属于外部类的类本身
    Java 语法有一条规则：静态成员不可访问非静态成员。而Kotlin 彻底取消了static 修饰符
    因此Kotlin 类中的成员除嵌套类之外，全部都是非静态成员，因此嵌套类不可访问外部类的其他任何成员（只能访问其他嵌套类）。

    总结：【嵌套类不能访问外部类的其他成员，只能访问另一个嵌套类。】

    嵌套类唯一可访问的是外部类的其他嵌套类。
    嵌套类相当于外部类的静态成员，因此外部类的所有方法、属性、初始化块都可以使用嵌套类来定义变量、创建对象等。
    外部类依然不能直接访问嵌套类的成员，但可以使用嵌套类的对象作为调用者来访问嵌套类的成员。
     *
     */

    /**
    除此之外，Kotlin 还允许在接口中定义嵌套类，但不允许在接口中定义内部类（即不允许定义使用inner 修饰的内部类）。
    如果为接口中的嵌套类指定访问控制符，则只能指定public或private ；
    如果定义接口中的嵌套类时省略访问控制符，则该嵌套类默认是public 访问权限。
     */
    innerCls4()

    /**
    声明内部类、嵌套类：
    val | var : Out.Int（语法一致）
    在创建内部类对象之前，必须先创建其外部类对象
    因为嵌套类是属于外部类的类本身的，因此创建嵌套类对象时无须创建外部类对象
     */
    innerCls5()

    /**
    局部嵌套类
    如果把一个嵌套类放在方法或函数中定义，则这个嵌套类就是一个局部嵌套类
    局部嵌套类【仅在该方法或函数中有效】。由于局部嵌套类不能在方法或函数以外的地方使用，因此局部嵌套类也不能使用访问控制符修饰。
     */
    innerCls6()
}


fun innerCls1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val user = User("rrc", 12)
    user.show()

    println("----------------")

    val woman = user.Woman("上海")
    woman.eat()
}


class User(val name: String, val age: Int) {

    inner class Woman(private val city: String) {

        fun eat() {
            println("eat")
            //访问内部类的成员
            println("woman的city=$city")
            //访问外部类的成员
            println("user的name = $name,age = $age")
            //访问外部类的private修饰的方法
            beauty()
        }
    }

    private fun beauty() {
        println("beauty执行")

    }


    fun show() {
        val woman = Woman("北京")
        woman.eat()
    }

}


fun innerCls2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val outerCls = OuterCls()
    val innerCls = outerCls.InnerCls()
    innerCls.test()
}


class OuterCls {
    private val a = "外部类的属性"

    inner class InnerCls {
        private val a = "内部类的属性"
        fun test() {
            val a = "局部变量"
            println(a)                  //局部变量
            println(this.a)             //内部类的属性
            println(this@InnerCls.a)    //内部类的属性
            println(this@OuterCls.a)    //外部类的属性

            /**
             * 外部类和内部类其实都有隐式标签，因此程序也可通兰带标签的this 明确指定访问内部类的属性
             */
        }
    }
}

fun innerCls3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val a = AA()
    val b = a.BB()
    b.testB()


}

class AA { // 隐式标签@AA

    inner class BB {// 隐式标签@BB

        //对Int扩展foo方法
        fun Int.foo() {// 隐式标签@foo
            println("" +
                    "\n-${this@AA}" +     //AA的this
                    "\n-${this@BB}" +     //BB的this
                    "\n-${this}" +          //不带标签的this ，默认代表该方法所属对象： Int 对象
                    "\n-${this@foo}"      //显式指定时@foo标签，与this代表的对象相同
            )

            //对String扩展一个待接收者的匿名函数
            val strFn = lambda@ fun String.() {
                println("" +
                        "\n-${this}" +          //不带标签的this ，默认代表该方法所属对象： String 对象
                        "\n-${this@lambda}"   //显式指定＠lambda 标签，与this代表的对象相同
                )

            }

            "Hello".strFn()

            //自定义一个lambda表达式，没有接受者
            val funLit = {

                //该this 所在的Lambda 表达式没有接收者，因此当前范围没有this
                //系统会继续向该Lambda 表达式所在范围搜索this
                //故此处this 将代表foo （）方法的接收者： Int 对象
                println("" +
                        "\n-$this" +
                        "\n-${this@foo}"//显式指定＠foo标签，与this代表的对象相同
                )

            }

            funLit()
        }

        fun testB() {
            2.foo()
        }
    }
}


fun innerCls4() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    val c = OutCls1()
    c.test()

    val cin = OutCls1.InnerCls2()
    cin.show()
}

class OutCls1 {
    val name = "rrc"
    fun test() {
        println("外部类的test方法")

        // 外部类不能访问嵌套类的成员
        // println(aaa)  //报错

        //可以通过创建对象访问嵌套类的成员
        println(InnerCls1().aaa)
    }

    //没有inner 修饰符，是嵌套类（相当于Java 的静态内部类）
    class InnerCls1 {
        val aaa = 1
    }

    //没有inner 修饰符，是嵌套类（相当于Java 的静态内部类）
    class InnerCls2 {
        val b = 2
        fun show() {
            //访问另一个嵌套类是允许的
            val in1 = InnerCls1()

            //test()            //报错
            //println(name)     //报错
        }

    }
}

fun innerCls5() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    val a: OutCls2.InnerCls1 = OutCls2.InnerCls1()//创建嵌套类
    val b: OutCls2.InnerCls2 = OutCls2().InnerCls2()//创建内部类

}

class OutCls2 {

    class InnerCls1()
    inner class InnerCls2()
}

fun innerCls6() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")
    val l = LocalNestedClass()
}

// 可以看到生成了三个class 文件：
// LocalNestedClass.class、LocalNestedClass$1NestedBase.class、LocalNestedClass$1NestedSub.class
// 这表明局部嵌套类的class 文件总是遵循
// 如下命名格式： OuterClass$NNestedClass.class 。

//注意到局部嵌套类的class 文件的文件名比嵌套类、内部类的class 文件的文件名多了一个数字
//这是因为同一个类中不可能有两个同名的嵌套类、内部类
//而同一个类中则可能有两个以上同名的局部嵌套类（处于不同的方法中），
//所以Kotlin 为局部嵌套类的class 文件名增加了一个数字，用于区分。
class LocalNestedClass {
    fun foo() {
        open class NestedBase
        class NestedSub : NestedBase()
    }
}





























