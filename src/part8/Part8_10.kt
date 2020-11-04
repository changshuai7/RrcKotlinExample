package part8

import kotlin.properties.Delegates
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 *
 * 八、面向对象(下)
 *
 * 8.10、类委托和属性委托
 * 委托是Kotlin 的另一个特色功能，也是Java 原本所不具备的功能。
 * Kotlin 的委托可分为类委托和属性委托。
 *
 */
fun main() {

    /**
    类委托：
    类委托是代理模式的应用，而代理模式可作为继承的一个不错的替代。
    类委托的本质就是将本类需要实现的部分方法委托给其他对象一一相当于借用其他对象的方法作为自己的实现。

    委托对象的两种方式:
    1、第一种方式是通过构造器参数指定委托对象
    2、第二种方式是直接在类定义的by 后新建对象。
    通常会采用第一种方式， 因为这样可以让多个对象共享同一个委托对象。

     */
    entrust1()


    /**
    属性委托
    1、Kotlin也支持属性委托，属性委托可以将多个类的类似属性统一交给委托对象集中实现，这样就可避免每个类都需要单独实现这些属性。

    2、对于指定了委托对象的属性而言，由于它的实现逻辑己经交给委托对象处理，
    因此开发者不能为委托属性提供getter和setter方法，Kotlin 也不会为委托属性提供getter和setter方法的默认实现。
    同时，Kotlin也不会为委托属性提供幕后字段--这是不言而喻的，因此委托属性的所有处理细节都由委托对象负责。
    Kotlin 会委托属性生成辅助属性，该辅助属性引用了属性的委托对象。当程序调用委托属性的getter和setter方法时，实际上就是执行委托对象的getValue（）、setValue（）方法。

    3、一旦将某个对象指定为属性的委托对象，该对象就会全面接管该属性的读取（ getter ）和写入（ setter ）操作
    对于读写属性：委托对象需要继承ReadWriteProperty，提供getValue和setValue方法
    对于只读属性：委托对象需要继承ReadOnlyProperty，提供getValue方法

    如果不继承这两个对象，就需要自己写 operator 修饰的 getValue和setValue方法，格式同ReadWriteProperty和ReadOnlyProperty。
    当然getValue和setValue可以是普通方法，也可以是扩展方法

     */
    entrust2()


    /**

    延迟属性

    Kotlin 提供了一个lazy()函数，该函数接受一个Lambda 表达式作为参数，并返回一个Lazy<T＞对象。
    Lazy<T＞对象包含了一个符合只读属性委托要求的getValue()方法，因此该Lazy<T＞对象可作为只读属性的委托对象。

     */
    entrust3()


    /**
    属性监听


    Kotlin 的kotlin.properties 包下提供了一个Delegates 对象声明（单例对象〉，该对象中包含如下两个常用方法。

    1、
    public inline fun <T> observable(
    initialValue: T,  //用于为接受委托的属性指定初始值
    crossinline onChange: (property: KProperty<*>,oldValue: T,newValue: T) -> Unit)  //接收一个lambda表达式，当接受委托的属性被设置值时， 该Lambda 表达式就会被触发。
    :ReadWriteProperty<Any?, T> //该方法返回一个ReadWriteProperty对象，因此该方法的返回值可作为只读属性的委托对象

    2、
    public inline fun <T> vetoable(
    initialValue: T,
    crossinline onChange: (property: KProperty<*>, oldValue: T, newValue: T) -> Boolean)
    :ReadWriteProperty<Any?, T>

    该方法与前一个方法基本相同， 区别只是负责执行监昕的Lambda 表达式需要返回值， 当该返回值为true 时接受委托的属性的新值设置成功； 否则设置失败。

     */
    entrust4()


    /**
    Kotlin的Map提供了getValue的方法，Kotlin的MutableMap提供了getValue和setValue的方法。
    符合读写属性的委托对象的要求，这意味着MutableMap对象可作为读写对象的委托。Map对象可作为只读对象的委托

    在这种情况下，对象本身井不负责存储对象状态，而是将对象状态保存在Map 集合中。
    这么做的好处是，当程序需要与外部接口（如JSON ）通信时，程序并不需要将该对象直接暴露出来， 只要将该对象属性所委托的Map 暴露出来即可，而Map 则完整地保存了整个对象状态。

     */
    entrust5()


    /**
    局部属性委托
    Kotlin 支持为局部变量指定委托对象。这种指定了委托对象的局部变
    量被称为“局部委托属性”一一其实还是局部变量，只是对该变量的读取、赋值操作将会交给委托对象去实现。

    与前面说的委托属性保持一致，局部变量的委托对象同样要遵守一定的要求：
    对于只读属性而言，同样需要实现operator 修饰的getValue方法，对于读写属性，需要实现operator修饰的getValue和setValue方法

    唯一区别的是：该方法的第一个参数是Nothing？类型， Nothing 是Kotlin 提供的一个特殊的类，专门用于代表永不存在的对象。
    【由于局部变量不属于任何对象】 因此它的委托对象的getValue、setValue方法的第一个参数自然也就不存在了，因此Kotlin 使用Nothing ？来声明getValue()/setValue方法的第一个参数的类型。

     */
    entrust6()


    /**
    委托工厂

    除提供getValue/setValue方法的对象可作为属性的委托对象之外， 从Kotlin1.1开始，一种类似于“委托工厂”的对象也可作为委托对象。
    委托工厂需要提供如下方法。

    public operator fun provideDelegate(thisRef: T, property: KProperty<*>): D

    该方法的两个参数与委托的getValue方法的两个参数的意义相同。
    如果上面方法返回ReadWriteProperty即对象，那么该对象可作为只读属性的委托对象；
    如果返回ReadOnlyProperty对象，则该对象就可作为读写属性的委托对象。


    委托工厂通常实现：PropertyDelegateProvider<T,D>接口，此接口中的泛型
    T : 属性委托所在的类的类型
    D:ReadWriteProperty或者ReadOnlyProperty


    Katlin 的官方文档将这个功能称为"provider a delegate" 说明 providerDelegate 方法负责生成委托。
    也就是说， 包含该方法的对象相当于“委托工厂”。

    使用providerDelegate方法来生成委托的好处是
    Kotlin 会保证对象的属性被初始化时调用该方法来生成委托，这样我们即可在该方法中加入任何自定义代码，完成任何自定义逻辑。
     */
    entrust7()
}


fun entrust1() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val out1 = OutPut1(DefaultOutPutAble())

    val out2 = OutPut2()

    out1.info()
    out2.info()
}

interface OutPutAble {
    val type: String
    fun info()
}

class DefaultOutPutAble : OutPutAble {
    override val type: String = "default -type"

    override fun info() {
        println("default - info")
    }

}

class OutPut1(put: OutPutAble) : OutPutAble by put   //委托给构造函数传入的参数put对象来实现
class OutPut2 : OutPutAble by DefaultOutPutAble() {  //委托给DefaultOutPutAble()对象来实现
    /**
     * 当一个类重写了委托对象所包含的方法之后， Kotlin 将优先使用该类自己实现的方法。
     */
    override fun info() {
        println("output2 - info")
    }
}


fun entrust2() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val en = EntrustField()
    en.city = "天津"
    println("city = ${en.city} ,address = ${en.address}")
}


class EntrustField {
    var city: String by Prop1()
    val address: String by Prop2()
}

class Prop1 : ReadWriteProperty<EntrustField, String> {
    private var _city = "北京"

    /**
     * @param thisRef   与getValue的thisRef 参数的作用相同。
     * @param property  与getValue的property参数的作用相同
     * @param value     该参数代表目标属性新设置的属性值，该参数的类型必须具有和目标属性相同的类型或其超类型。
     */
    override fun setValue(thisRef: EntrustField, property: KProperty<*>, value: String) {
        //点进去到KProperty中，可以发现很多可供操作的属性
        this._city = value
    }

    /**
     * @param thisRef   该参数代表属性所属的对象，因此该参数的类型必须是属性所属对象的类型（对于扩展属性，指被扩展的类型）或其超类型。
     * @param property  该参数代表目标属性，该参数的类型必须是KProperty＜＊＞或其超类型。
     * return 该方法必须返回与目标属性相同的类型（或其子类型）。
     */
    override fun getValue(thisRef: EntrustField, property: KProperty<*>): String {
        return this._city
    }

}

class Prop2 : ReadOnlyProperty<EntrustField, String> {
    override fun getValue(thisRef: EntrustField, property: KProperty<*>): String {
        return "北京朝阳区"
    }

}

fun entrust3() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val lazyProp: String by lazy {
        println("lazy执行")
        "RRC"
    }
    println(lazyProp)
    println(lazyProp)


    /**

    将lazyProp 属性的委托对象指定为lazy（）函数的返回值： Lazy<T>对象。
    这样当程序访问lazyProp 属性时，实际上就是调用Lazy＜ T＞对象的getValue（）方法。
    Lazy<T＞的getValue（）方法的处理逻辑是：第一次调用该方法时，程序会计算Lambda 表达式，并得到其返回值
    以后程序再次调用该方法时，不再计算Lambda 表达式，而是直接使用第一次计算得到的返回值

     */

    //查看源码：
    //public actual fun <T> lazy(initializer: () -> T): Lazy<T> = SynchronizedLazyImpl(initializer)
    //public actual fun <T> lazy(mode: LazyThreadSafetyMode, initializer: () -> T): Lazy<T> =
    //    when (mode) {
    //        LazyThreadSafetyMode.SYNCHRONIZED -> SynchronizedLazyImpl(initializer)
    //        LazyThreadSafetyMode.PUBLICATION -> SafePublicationLazyImpl(initializer)
    //        LazyThreadSafetyMode.NONE -> UnsafeLazyImpl(initializer)
    //    }

    /**
    从上面代码可以看出， 前面代码使用的是第一个版本，该版本的lazy（）函数执行Lambda表达式时会提供自动添加保证线程安全的同步锁，因此开销略大。
    主上面的第一个lazy函数相当于第二个lazy（）函数的第一个参数指定为LazyThreadSafetyMode.SYNCHRONIZED
     */


    /**
    Lazy方法的第一个参数有三种：

    LazyThreadSafetyMode.SYNCHRONIZED -> 自动添加保证线程安全的同步锁
    LazyThreadSafetyMode.PUBLICATION -> 在这种模式下， lazy函数不会使用排他同步锁，多个线程可同时执行。
    LazyThreadSafetyMode.NONE -> 在这种模式下，lazy()函数不会有任何线程安全相关的操作和开销。

     */

    //如下，取消线程安全操作，可以保证最佳性能
    val lazyProp1: String by lazy(LazyThreadSafetyMode.NONE) {
        println("lazy执行")
        "RRC"
    }

}

fun entrust4() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    var prop: String by Delegates.observable("默认值") {

        /**
         * lambda 表达式的第一个参数代表被监听的属性
         *第二个参数代表修改之前的值
         *第三个参数代表被设置的新值
         */
        property, oldValue, newValue ->
        println("$property 的$oldValue 被设置成了 $newValue")
    }

    println(prop)

    prop = "hello"

    println(prop)


//    从上面的运行结果可以看出，无论我们为属性设置的新值是什么，总可以设置成功。
//    如果 需要对新值进行判断，然后再确定是否设置成功，则可使用Delegates 对象的vetoableO方法。
//    例如如下代码。

    println("----------------")
    var prop1: String by Delegates.vetoable("默认值") { property, oldValue, newValue ->
        println("$property 的$oldValue 被设置成了 $newValue")
        //此处我们要求新设置的属性值的长度必须比原属性值的长度大，才能设置成功。
        newValue.length > oldValue.length
    }
    println(prop1)
    prop1 = "哈哈" //没有设置成功
    println(prop1)
    prop1 = "哈哈哈哈哈哈哈"//设置成功
    println(prop1)
}


fun entrust5() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")


    /**
     * 程序可以将Item 对象的所有只读属性都委托给一个Map 对象，
     * 其实道理很简单： Item 对象的每个属性名就相当于Map 的key ，属性值就相当于key 对应的value 。
     * 指定Map 对象作为Item 对象的委托之后，接下来程序只要把Item 的Map 暴露出来，程序即可通过该Map 来获取Item 对象的状态
     *
     */
    val map1 = mapOf<String, Any>("name" to "rrc", "age" to 20)
    val item = Item(map1)
    println("name = ${item.name},age = ${item.age}")


    /**
     * 如果对象包含的属性是读写属性，则需要使用MutableMap 作为委托对象
     * 读写属性委托给MutableMap 对象，这样当程序设置Item 对象的属性值时，实际上是交给MutableMap 负责处理的，
     * 相当于为MutableMap设置了key-value 对。因此程序后面可通过MutableMap 获取到为Item 设置的属性值。
     * 反过来，如果程序为被委托的MutableMap 对象设置了key-value 对，实际上就是修改了Item 对象的状态，
     */

    val map2 = mutableMapOf<String, Any>("name" to "rrc", "age" to 20)
    val item2 = Item2(map2)
    println("name = ${item2.name},age = ${item2.age}")
    item2.age = 1000
    println(map2["age"])

    map2["name"] = "cccc"
    println("name = ${item2.name},age = ${item2.age}")


}

class Item(map: Map<String, Any>) {
    val name: String by map
    val age: Int by map
}

class Item2(map: MutableMap<String, Any>) {
    val name: String by map
    var age: Int by map
}


fun entrust6() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val name: String by PropLocal1()
    var age: Int by PropLocal2()

    age = 111
    println("name = $name,age = $age")

}

class PropLocal1 : ReadOnlyProperty<Nothing?, String> {
    override fun getValue(thisRef: Nothing?, property: KProperty<*>): String {
        return "rrc"
    }

}

class PropLocal2 : ReadWriteProperty<Nothing?, Int> {
    private var age = 0
    override fun setValue(thisRef: Nothing?, property: KProperty<*>, value: Int) {
        this.age = value
    }

    override fun getValue(thisRef: Nothing?, property: KProperty<*>): Int {
        return age
    }
}

fun entrust7() {
    println("\n==========${Thread.currentThread().stackTrace[1].methodName}===========")

    val c = Cls()
    println(c.name)
    c.name = "HelloWorld"
    println(c.name)
}


class Cls {
    var name: String by DelegateFactory()
}


class DelegateFactory : PropertyDelegateProvider<Cls, ReadWriteProperty<Cls, String>> {
    override fun provideDelegate(thisRef: Cls, property: KProperty<*>): ReadWriteProperty<Cls, String> {

        println("Delegate工厂")

        // 这里可以完成很多检查的工作  扩展性更高

        return PropTest()
    }
}

class PropTest : ReadWriteProperty<Cls, String> {
    private var name: String = "默认值"
    override fun setValue(thisRef: Cls, property: KProperty<*>, value: String) {
        this.name = value
    }

    override fun getValue(thisRef: Cls, property: KProperty<*>): String {
        return this.name
    }

}

/**
 *
 * 总结：
 *
 * 1、读写属性委托：实现接口 ReadWriteProperty<T,D>
 * 2、只读属性委托：实现接口 ReadOnlyProperty<T,D>
 *      T为委托属性所在类的类型，如果是局部委托，则T为Nothing?
 *      D为委托属性的类型
 * 3、委托工厂：实现接口 PropertyDelegateProvider<T,K>
 *      T为委托属性所在类的类型，如果是局部委托，则T为Nothing?
 *      D为读写/只读属性委托的类型。一般为ReadWriteProperty<T,D>或者ReadOnlyProperty<T,D>
 *
 * 注意：实现委托可以自定义，只要满足getValue、setValue、provideDelegate的格式，不必非要去实现接口，自定义的类也是可以的。
 *
 */






