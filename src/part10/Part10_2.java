package part10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * 泛型的型变
 * <p>
 * <p>
 * 个人笔记：关于协变和逆变：https://app.yinxiang.com/fx/7357ec86-d51d-4433-aeec-12c7b85ca5b5
 */
public class Part10_2 {
    public static void main(String[] args) {

        /**
         ////////////////1、Java不支持型变////////////////
         */

        // Java 的泛型是不支持型变的。通俗地说， List<String＞并不是List<Object> 的子类
        // 因此List<String＞不能直接赋值给List<Object>
        // Java 泛型不支持型变是有道理的，假如Java 泛型支持型变,则有如下代码：

        List<String> list1 = new ArrayList<>();
        // List<Object> obj = list1;//这样是错误的，假如支持型变编译可以通过，那么：
        // obj.add(100);//直接导致存入了错误类型


        /**
         ////////////////2、Java不支持型变带来的问题////////////////
         */

        // 但Java 取消型变之后，程序变得非常麻烦。比如Java 的Collection 中有一个addAll（）方法
        // 该方法负责将另一个集合中的所有元素添加到本集合内。

        Set<Number> numSet = new HashSet<>();
        Set<Integer> intSet = new HashSet<>();

        // 如果不支持型变，下面代码的方法调用者是Set<Number＞
        // 它要求其参数也必须是Collection<Number＞，而Set<Integer＞将不能作为参数传入。
        // numSet.addAll(intSet);

        // 但实际上我们知道上面代码绝对是安全的
        // 对于Set<Number＞集合，程序完全可以将Set<Integer＞的集合内的元素安全地添加进去
        // 这就是型变的需要。

        /**
         ////////////////2、Java的协变和通配符上限////////////////
         */
        numSet.addAll(intSet);

        // 为了处理型变的需要， Java 采用通配符方式进行处理，
        // Java将addAll的方法定义为：addAll(Collection<? extends E> c)
        // 此时addAll()方法的参数类型是指定上限的类型，其本质就是为了支持型变

        numSet.addAll(new HashSet<Integer>());
        numSet.addAll(new HashSet<Double>());

        // addAll()方法调用者是Set<Number＞，因此该方法的参数可以是Collection<Integer＞、Collection<Double＞等
        // 只要其泛型参数是Number 的子类即可。这种型变被称为“ covariant”（中文翻译为“协变”)
        // Java 将这种语法称为“通配符上限”。


        // 对于“通配符上限”语法而言，从该集合对象中“拿出”对象总是安全的。
        // 比如Collection<?extends Number＞集合，从其中取出的元素至少是Number 的子类。
        // 但程序不能向集合中添加元素（不能传入对象）。
        // 比如Collection<? extends Number＞集合，我们无法预测该集合实际引用的是Collection<Integer＞还是Collection<Double＞。

        /**
         ////////////////2、Java的逆变和通配符下限////////////////
         */

        //场景：
        //定义一个Predicate
        Predicate<Number> predicate = it -> it.doubleValue() > 10;

        Set<Integer> intSet1 = new HashSet<>();
        intSet1.add(1);
        intSet1.add(2);
        intSet1.add(3);
        intSet.removeIf(predicate);//使用Predicate<Number＞对Set<Integer>进行过滤

        Set<Double> doubleSet1 = new HashSet<>();
        doubleSet1.add(1.2);
        doubleSet1.add(2.3);
        doubleSet1.add(3.4);
        doubleSet1.removeIf(predicate);//使用Predicate<Number＞对Set<Double>进行过滤

        // removeIf()方法调用者是Set<Integer＞或Set<Double＞
        // 该方法的参数完全可以是Predicate <Number＞ 、Predicate<Object> 等
        // 只要泛型参数是Integer 或Double 的父类即可。
        // 这种型变被称为“ contravariance ” （中文翻译为“逆变”）
        // Java 将这种语法称为“通配符下限” 。

        // 可见， Java 的“通配符下限”的本质也是为了支持型变，这种型变被细化为逆变。

        // 对于“通配符下限”语法而言，将对象传给泛型对象是安全的。
        // 比如Predicate<? super Integer>对象，由于该Predicate<T> 中的泛型参数一定是Integer 的父类，
        // 因此程序总可以向Predicate对象传入Integer 值。但程序从泛型中取出对象是不安全的。
        // 比如Predicate<? super Integer>,我们无法预测该集合实际引用的是Predicate<Number＞还是Predicate<Object> 。

        /**
         * 简而言之，泛型存在如下规律。
         * 》通配符上限（泛型协变〉意味着从中取出（ out ）对象是安全的，但传入对象（ in ）则不可靠。
         * 》通配符下限（泛型逆变）意味着向其中传入（ in ）对象是安全的，但取出对象（ out)则不可靠。
         * Kotlin 利用上面两个规律，抛弃了泛型通配符语法，而是利用in 、out 来让泛型支持型变。
         */

    }
}
