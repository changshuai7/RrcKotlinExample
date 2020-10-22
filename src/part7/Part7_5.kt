package part7


/**
 * 七、面向对象(5)
 * 继承
 */
fun main() {

    /**
     * Kotlin的子类继承父类的语法如下
     *
     *  -------------------
     *
     * 修饰符class SubClass : Superclass {
     *  // 类定义部分
     *  }
     *
     *  --------------------
     *
     *
     *  如果在定义一个Kotlin 类时并未显式指定这个类的直接父类，则这个类默认扩展Any 类。
     *  因此， Any 类是所有类的父类，要么是其直接父类，要么是其间接父类。
     *  需要说明的是， Any 类不是java.lang.Object 类， Any 类只有equals()、hashCode()、和toString()这3 个方法。
     *  还有一点需要说明的是， Kotlin 的类默认就有 final 修饰，因此Kotlin 的类默认是不能派生子类的。为了让一个类能派生子类， 需要使用open 修饰该类。
     */

    /**
     *
     * Java中：
     * 子类构造器总要调用父类构造器一次。子类构造器调用父类构造器分如下几种情况。
     * 》子类构造器执行体的第一行使用super（参数）显式调用父类构造器，系统将根据super（参数）调用中传入的实参列表调用父类对应的构造器。
     * 》子类构造器执行体的第一行代码使用this（参数）显式调用本类中重载的构造器，系统将根据this(参数) 调用中传入的实参列表调用本类中的另一个构造器。调用本类中的另一个构造器最终还是要调用父类构造器。
     * 》子类构造器执行体中既没有super （参数）调用，也没有this（参数）调用，系统将会在执行子类构造器之前， 隐式调用父类无参数的构造器。
     *
     * Kotlin 的构造器同样要遵守这个规则，只不过Kotlin 为之换了个新说法： 【委托父类构造器】。
     * 而且由于Kotlin 的构造器分为主构造器和次构造器，因此情况略微复杂一些。下面分主构造器和次构造器进行详细说明。
     *
     */

    /**
     * 一、子类的主构造器
     * 如果子类定义了主构造器，由于主构造器属于类头部分（如果不定义初始化块，它就没有执行体〉
     * 为了让主构造器能调用父类构造器，因此主构造器必须在继承父类的同时委托调用父类构造器
     */
    sub1()


    /**
     * 二、子类的次级构造器
     * 次构造器同样需要委托调用父类构造器。
     * 1、如果子类定义了主构造器，由于子类的次构造器总会委托调用子类的主构造器（直接或间接），而主构造器一定会委托调用父类构造器，因此子类的所有次构造器最终也调用了父类构造器。
     * 2、如果子类没有定义主构造器，则此时次构造器委托调用父类构造器可分为3 种方式。
     * 》子类构造器显式使用： this （参数）显式调用本类中重载的构造器，系统将根据this （参数）调用中传入的实参列表调用本类中的另一个构造器。调用本类中的另一个构造器最终还是要调用父类构造器。
     * 》子类构造器显式使用： super（参数）委托调用父类构造器，系统将根据super（参数）调用中传入的实参列表调用父类对应的构造器。
     * 》子类构造器既没有： super（参数）调用，也没有： this （参数）调用，系统将会在执行子类构造器之前，隐式调用父类无参数的构造器(不管是不是默认提供的都可，如果不存在无参构造器会报错)。
     *
     */

    /**
     *
     * Kotlin 与Java 的设计相同：所有子类构造器必须调用父类构造器一次．
     *
     * 当调用子类构造器来初始化子类对象时，父类构造器总会在子类构造器之前执行；
     * 不仅如此， 在执行父类构造器时，系统会再次上溯执行其父类构造器…… 依此类推，创建任何Kotlin对象，最先执行的总是Any 类的构造器。
     */
    sub2()

    /**
     * 重写父类方法：
     * 1、Kotlin 默认为所有方法添加final 修饰符，阻止该方法被重写， 添加open 关键字用于阻止Kotlin 自动添加final 修饰符
     * 2、Kotlin 类重写父类的方法必须添加override 修饰符,就像Java 的＠override 注解，只不过Java 的＠override 是可选的， 而Kotlin 的override 修饰符是强制的
     * 3、如果需要在子类方法中调用父类中被覆盖的方法，则可以使用super 作为调用者来调用父类中被覆盖的方法
     * 4、如果父类方法具有private 访问权限，则子类是无法重写的，如果子类中定义了一个与父类private 方法具有相同的方法名、相同的形参列表、相同的返回值类型的方法，那么这不是重写，只是在子类中重新定义了一个新方法
     *
     *
     *
     * Kotlin和Java一致。方法的重写要遵循“两同两小一大”规则
     * “两同”即方法名相同、形参列表相同
     * “两小”指的是子类方法的返回值类型应比父类方法的返回值类型更小或相等，子类方法声明抛出的异常类应比父类方法声明抛出的异常类更小或相等
     * “一大”指的是子类方法的访问权限应比父类方法的访问权限更大或相等。
     *
     */
    sub3()


    /**
     * 重写父类属性
     *
     * 重写父类的属性与重写父类的方法大致相似：父类被重写的属性必须使用open 修饰，子类重写的属性必须使用override 修饰。此外，属性重写还有如下两个限制。
     * 》重写的子类属性的类型与父类属性的类型要兼容。
     * 》重写的子类属性要提供更大的访问权限。此处包含两方面的含义：
     * ①在访问权限方面，子类属性的访问权限应比父类属性的访问权限更大或相等。（ 注意：internal和protected 无明确的权限大小 ）
     * ②只读属性可被读写属性重写，但读写属性不能被只读属性重写。
     *
     */
    sub4()

    /**
     * super限定
     *
     * 如果需要在子类方法中调用父类中被覆盖的方法或属性，则可使用super 限定
     *
     *
     * 如果在某个方法中访问名为a 的属性，但没有显式指定调用者，则系统查找a 的顺序为：
     * 查找该方法中是否有名为a 的局部变量。
     * 查找当前类中是否包含名为a 的属性。
     * 查找a 的直接父类中是否包含名为a 的属性，依次上溯查找a 的所有父类，直到Any类，如果最终不能找到名为a 的属性， 则系统出现编译错误。
     */
    sub5()


    /**
     * 强制重写
     *
     * 如果子类从多个直接超类型（接口或类）继承了同名的成员，那么Kotlin 要求子类必须重写该成员。
     * 如果需要在子类中使用super 来引用超类型中的成员，则可使用尖括号加超类型名限定的super 进行引用， 如super< Bar＞。
     */
    sub6()

}


fun sub1() {

//    如下：编译不通过，因为没有委托父类构造器

//    open class Fu
//    class Zi : Fu

    open class Fu(name: String) {
        var name: String

        init {
            this.name = name
        }
    }


    //子类没有显式声明主构造器
    //子类默认有一个无参的主构造器，因此要在声明继承时委托调用父类构造器
    class Zi1 : Fu("Tom") {

    }

    //子类显式声明主构造器
    //主构造楼必须在声明继承时委托调用父类构造器
    class Zi2(name: String, age: Int) : Fu(name) {

    }

    //子类没有显式声明主构造器
    //子类有一个唯一次级构造器（此时默认的无参构造器将失效），因此需要在声明时，委托父类构造器
    class Zi3 : Fu {

        constructor(name: String, age: Int) : super(name) {

        }
    }

}


fun sub2() {

    //1、主构造器
    open class Fu(name: String) {

        var name: String

        init {
            this.name = name
            println("初始化块...")
        }

        //2、次级构造器(虽然是无参构造器，依然是次级构造器)，委托主构造器
        constructor() : this("qqq") {
            println("无参构造器,name = $name")
        }

    }

    // 子类存在主构造器，则主构造器必须委托父类的构造器
    class Zi1(name: String, age: Int) : Fu(name) {

        //子类存在主构造器，则所有的次级构造器都必须直接或者间接委托主构造器
        constructor(name: String, age: Int, city: String) : this(name, age)
        constructor(name: String, age: Int, city: String, isBeautiful: Boolean) : this(name, age, city)

        //报错，没有委托主构造器
        //constructor(name: String, age: Int, city: String) : super(name)

    }

    //子类不存在主构造器
    class Zi2 : Fu {
        // 委托父类构造器
        constructor(name: String, age: Int) : super(name)

        // 委托子类的重载构造器，即间接委托了父类构造器
        constructor(name: String, age: Int, city: String) : this(name, age)

        // 既没有this。也没有super。则委托父类的无参构造器
        constructor(name: String, age: Int, city: String, post: Int)
    }

    val z = Zi2("hello", 12, "ddd", 10000) //如果删除父类的无参构造器，则会报错

}

//总之一下几点：
// 在Kotlin中：
// 1.任何类都有[0,1]个主构造器或者[0,N]个次级构造器。如果主构造器、次级构造器都为0,则系统提供一个默认无参构造器(主构造器)。但是只要存在了一个自定义的构造器（包括自定义无参构造器），则系统的无参构造器失效。
// 1.任何类的次级构造器必须委托主构造器（如果存在），即使主构造器不存在，也依然会执行主构造器的执行体：初始化块
// 2.子类继承父类
//      2.1、子类如果有主构造器，因为类的次级构造器必须委托主构造器，所以子类的主构造器必须委托父类构造器。（此时子类的次级构造器不可以直接委托父类构造器，不能绕过主构造器）
//      2.2、子类如果没有主构造器，则必须通过super直接委托父类指定的构造器或者通过this间接委托父类指定的构造器。否则没有super和this,系统会直接委托父类无参构造器（父类不存在无参构造器会报错）

//类头上，带括号的就是构造器

fun sub3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")


    open class Fu {

        protected open fun eat(food: String) {

            println("父类吃${food}")
        }

        private fun fly() {
            println("父类fly")
        }
    }

    class Zi : Fu() {

        public override fun eat(food: String) {
            super.eat(food)
            println("子类吃${food}")
        }

        fun fly() {
            println("子类fly")
        }
    }

    val zi: Zi = Zi()
    zi.eat("屎")
    zi.fly()

}


fun sub4() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")


    open class Fu {
        protected open var name: String = "fu"
        open val age = 10
        open var city = "北京"
    }

    class Zi : Fu() {
        //正确重写了父类属性，类型兼容，访问权限更大
        public override var name: String = "zi"

        //正确重写了父类属性，读写属性重写只读属性
        override var age = 200
        //重写错误，只读属性不能重写读写属性
        //override val city = "上海"
    }
}

fun sub5() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    open class Fu {
        open var name = "rrc"
        open var name1 = "rrc"
    }

    class Zi : Fu() {
        override var name: String = "RRC"
        fun show() {
            println("name=$name,super.name=${super.name}")
        }
    }
    Zi().show()
}

fun sub6() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    Zi().show()

}

open class OveFu1 {
    open fun test() {
        println("Fu1-test")
    }
}

interface OveFu2 {

    fun test() {
        println("Fu2-test")
    }

    fun test2()
}

class Zi : OveFu1(), OveFu2 {

    // 必须重写，否则会报错
    override fun test() {
        println("Zi-test")
    }
    override fun test2() {
    }

    fun show() {
        test()
        super<OveFu1>.test()
        super<OveFu2>.test()

    }
}











